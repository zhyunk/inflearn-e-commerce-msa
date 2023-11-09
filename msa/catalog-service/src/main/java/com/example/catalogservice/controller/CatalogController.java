package com.example.catalogservice.controller;

import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CatalogController {
    private final Environment env;
    private final ModelMapper mapper;
    private final CatalogService catalogService;


    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Catalog Service !! Port is [ %s ]", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        List<ResponseCatalog> result = new ArrayList<>();

        catalogService
                .getAllCatalogs()
                .forEach(people -> result.add(mapper.map(people, ResponseCatalog.class)));

        return ResponseEntity.ok(result);
    }

    @GetMapping("/catalog/{id}")
    public ResponseEntity<ResponseCatalog> getCatalog(@PathVariable("id") String productId) {

        return ResponseEntity.ok(catalogService.getCatalogById(productId));
    }

}
