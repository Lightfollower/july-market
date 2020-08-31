package com.geekbrains.july.market.configs;

import com.geekbrains.july.market.products.GetProductRequest;
import com.geekbrains.july.market.products.GetProductResponse;
import com.geekbrains.july.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";

    private ProductsService productsService;

    @Autowired
    public ProductEndpoint(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        System.out.println(request.getTitle());
        GetProductResponse response = new GetProductResponse();
        System.out.println(response);
        response.setProduct(productsService.findOneProductSOAPByTitle(request.getTitle()));
        System.out.println(response.getProduct().getTitle());
        return response;
    }
}
