package com.ubs.teste.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductModel {
    private String product;
    private Integer quantity;
    private String price;
    private String type;
    private String industry;
    private String origin;

    @Override
    public String toString() {
        return "Product{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", industry='" + industry + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public Double getPrice() {
        return Double.valueOf(price.replace("$", ""));
    }
}
