package com.prashant.thakur.emailnotification.handler;

import com.prashant.thakur.model.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "product-created-topic")
// it is used to mark class or method target for incoming message topic
public class ProductCreatedEventHandler {
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @KafkaHandler // used within kafka listener class for defining multiple method for handling different type of messages
  public void handle(ProductCreatedEvent productCreatedEvent) {
    LOGGER.info("Received event {}", productCreatedEvent.getTitle());
  }


}
