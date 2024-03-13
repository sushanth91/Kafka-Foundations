package com.appsdeveloperblog.ws.productsMicroservice.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.appsdeveloperblog.ws.productsMicroservice.event.ProductCreatedEvent;
import com.appsdeveloperblog.ws.productsMicroservice.model.CreateProductRestModel;

@Service
public class ProductServiceImpl implements ProductService {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

	public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String createProduct(CreateProductRestModel productRestModel) {

		String productId = UUID.randomUUID().toString();

		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(),
				productRestModel.getPrice(), productRestModel.getQuantity());
		CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent);
		
		future.whenComplete((result, exception)->{
			if(exception !=null) {
				LOGGER.error("****** failed to send message : "+ exception.getMessage());	
			}else {
				LOGGER.info("******* Message Sent Succesfully : "+ result.getRecordMetadata());	
			}
			
		});
		LOGGER.info("***** Returning ProductID");
		return productId;
	}

}
