package com.prashant.thakur.products.service;

import com.prashant.thakur.products.model.CreateProductRestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import com.prashant.thakur.model.ProductCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate; // specify key and value pair

    //this classs comes from spring framework and it is designed to send messages or to publish event to kafka topic
    //this class is small wrapper around kafka producer
    //nicely intefrated with spring feature like dependency injection and automatic configuration
    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel createProductRestModel) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();


        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder().productId(productId).title(createProductRestModel.getTitle()).price(createProductRestModel.getPrice()).quantity(createProductRestModel.getQuantity()).build();
        //Async way-> error will happen then also we will it will complete and we will come to know it through logs
        //sendMessageToKafkaAsyncWay(productId, productCreatedEvent);
        sendMessageToKafkaSyncWay(productId,productCreatedEvent);
        return productId;
    }

    private SendResult<String, ProductCreatedEvent> sendMessageToKafkaSyncWay(String productId, ProductCreatedEvent productCreatedEvent) throws ExecutionException, InterruptedException {
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-topic", productId, productCreatedEvent).get();
        LOGGER.info("Topic :: {} Offset :: {} Partition :: {}",result.getRecordMetadata().topic(), result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
        return result;
    }
    private void sendMessageToKafkaAsyncWay(String productId, ProductCreatedEvent productCreatedEvent) {
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-topic", productId, productCreatedEvent); // send message to kafka asynchronoulsy without acknowledgment
        future.whenComplete((stringProductCreatedEventSendResult, throwable) -> {
            if (throwable != null) {
                LOGGER.info("failed to send message");
            } else {
                LOGGER.info("message sent successfully {}", stringProductCreatedEventSendResult.getRecordMetadata());
            }
        });
        //        //making synchronous -> if we want to want for async operation to complete before returning from this method join thread
        //        future.join();// this method will block current method and return result when available
    }
}
