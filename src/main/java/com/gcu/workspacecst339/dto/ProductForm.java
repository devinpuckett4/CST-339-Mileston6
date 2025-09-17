package com.gcu.workspacecst339.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductForm {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be > 0")
    private BigDecimal price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}



