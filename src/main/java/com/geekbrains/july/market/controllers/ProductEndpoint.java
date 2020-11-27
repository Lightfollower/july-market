package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.productsSOAPServiceFiles.GetProductRequest;
import com.geekbrains.july.market.productsSOAPServiceFiles.GetProductResponse;
import com.geekbrains.july.market.entities.dtos.ProductSOAPDto;
import com.geekbrains.july.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        List<ProductSOAPDto> products = new ArrayList<>();
        response.setProduct(productService.findAllSOAP());
        return response;
    }
}
