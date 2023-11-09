package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Catalog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatalogRepository extends CrudRepository<Catalog, Long> {
    Optional<Catalog> findByProductId(String productId);
}