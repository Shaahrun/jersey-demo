package com.gmail.ejikemesharon;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Path("products")
public class ProductResource {

    private ProductService productService;
    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getproductList(@NotBlank(message = "Consumer key is required")
                                            @QueryParam(value = "Consumer key") String consumerKey) {
        log.info("Consumer: {}", consumerKey);
        return productService.findAll();
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam(value = "productId") Long productId) {
        return productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"))
    }

}
