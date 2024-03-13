package com.appsdeveloperblog.ws.productsMicroservice.service;

import com.appsdeveloperblog.ws.productsMicroservice.model.CreateProductRestModel;

public interface ProductService {
	public String createProduct(CreateProductRestModel productRestModel);

}
