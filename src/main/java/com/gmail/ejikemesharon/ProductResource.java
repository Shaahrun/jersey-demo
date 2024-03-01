package com.gmail.ejikemesharon;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
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
        return productService.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("productId " + productId + " not found"));
    }

    @POST
    public String createProduct(@Valid Product product) {
        productService.save(product);
        return "Product added";
    }

    @PUT
    @Path("{productId}")
    public String updateProduct(@PathParam(value = "productId") Long productId, @Valid Product product) {
        return productService.findById(productId).map(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            productService.update(p);
            return "Product updated";
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

    @DELETE
    @Path("{productId}")
    public String deleteProduct(@PathParam(value = "productId") Long productId) {
        return productService.findById(productId).map(p -> {
            productService.deleteById(productId);
            return "Product deleted";
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

}
