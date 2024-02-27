package com.gmail.ejikemesharon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sharon");
    private EntityManager entityManager;

    public ProductRepository() {
        entityManager = emf.createEntityManager();
    }

    public Product save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product;
    }

    public Optional<Product> findById(Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().commit();
        return product != null ? Optional.of(product) : Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll () {
        return entityManager.createQuery("from Product").getResultList();
    }

    public Product update(Product product) {
        entityManager.getTransaction().begin();
        product = entityManager.merge(product);
        entityManager.getTransaction().commit();
        return product;
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
