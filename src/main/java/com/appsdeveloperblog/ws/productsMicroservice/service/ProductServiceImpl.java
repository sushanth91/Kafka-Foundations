package com.appsdeveloperblog.ws.productsMicroservice.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.appsdeveloperblog.ws.productsMicroservice.event.ProductCreatedEvent;
import com.appsdeveloperblog.ws.productsMicroservice.model.CreateProductRestModel;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

	public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String createProduct(CreateProductRestModel productRestModel) throws Exception {

		String productId = UUID.randomUUID().toString();

		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(),
				productRestModel.getPrice(), productRestModel.getQuantity());

		LOGGER.info("Before publishing a product created event : ");

		SendResult<String, ProductCreatedEvent> result = kafkaTemplate
				.send("product-created-events-topic", productId, productCreatedEvent).get();

		LOGGER.info("Partition : {}", result.getRecordMetadata().partition());
		LOGGER.info("Topic : {}", result.getRecordMetadata().topic());
		LOGGER.info("Offset : {}", result.getRecordMetadata().offset());

		LOGGER.info("***** Returning ProductID : " + productId);
		return productId;
	}

}
