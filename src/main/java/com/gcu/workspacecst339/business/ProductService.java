package com.gcu.workspacecst339.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.workspacecst339.data.ProductRepository;
import com.gcu.workspacecst339.model.Product;

@Service
public class ProductService {
    private final ProductRepository products;

    public ProductService(ProductRepository products) {
        this.products = products;
    }

    public List<Product> listAll() {
        List<Product> out = new ArrayList<>();
        products.findAll().forEach(out::add);
        return out;
    }
}