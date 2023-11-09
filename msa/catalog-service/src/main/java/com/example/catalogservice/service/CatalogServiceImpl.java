package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {
    private final ModelMapper mapper;
    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<Catalog> getAllCatalogs() {

        return catalogRepository.findAll();
    }

    @Override
    public ResponseCatalog getCatalogById(String productId) {
        Optional<Catalog> optional = catalogRepository.findByProductId(productId);

        if (optional.isEmpty())
            throw new RuntimeException("Not Found Product");

        return mapper.map(optional.get(), ResponseCatalog.class);
    }

}
