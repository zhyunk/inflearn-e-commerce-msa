package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.vo.ResponseCatalog;

public interface CatalogService {

    Iterable<Catalog> getAllCatalogs();
    ResponseCatalog getCatalogById(String productId);

}
