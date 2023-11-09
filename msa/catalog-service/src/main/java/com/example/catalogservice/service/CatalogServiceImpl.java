package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {
    private final ModelMapper mapper;
    private final CatalogRepository catalogRepository;

    @Override
    public List<ResponseCatalog> getAllCatalogs() {

        return Stream.of(catalogRepository.findAll().iterator())
                .filter(Iterator::hasNext)
                .map(catalogIterator -> mapper.map(catalogIterator.next(), ResponseCatalog.class))
                .toList();
    }

    @Override
    public ResponseCatalog getCatalogById(String productId) {
        Optional<Catalog> optional = catalogRepository.findByProductId(productId);

        if (optional.isEmpty())
            throw new RuntimeException("Not Found Product");

        return mapper.map(optional.get(), ResponseCatalog.class);
    }

}
