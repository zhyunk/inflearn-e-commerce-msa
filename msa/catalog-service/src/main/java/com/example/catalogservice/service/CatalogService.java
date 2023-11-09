package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.vo.ResponseCatalog;

import java.util.List;

public interface CatalogService {

    List<ResponseCatalog> getAllCatalogs();
    ResponseCatalog getCatalogById(String productId);

}
