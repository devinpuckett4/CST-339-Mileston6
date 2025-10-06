package com.gcu.workspacecst339.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCTS") // H2 stores unquoted identifiers UPPERCASE
public class Product {

    @Id
    @Column("ID")
    private Long id;

    @Column("NAME")
    private String name;

    @Column("DESCRIPTION")
    private String description;

    @Column("PRICE")
    private BigDecimal price;

    @Column("QUANTITY")
    private Integer quantity;

    @Column("CREATED_AT")
    private LocalDateTime createdAt;

    public Product() {}

    public Product(Long id, String name, String description, BigDecimal price, Integer quantity, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}