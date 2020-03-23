package com.ubs.teste.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Products", uniqueConstraints = {@UniqueConstraint(columnNames = {"product", "quantity", "price", "type", "industry", "origin", "fileName"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private Integer quantity;
    private Double price;
    private String type;
    private String industry;
    private String origin;
    private String fileName;

    public Product(String product, Integer quantity, Double price, String type, String industry, String origin, String fileName) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.industry = industry;
        this.origin = origin;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", industry='" + industry + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
