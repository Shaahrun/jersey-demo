package com.gmail.ejikemesharon;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Product update(Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
