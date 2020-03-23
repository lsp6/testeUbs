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
    @Column(nullable = false)
    private String product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String industry;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
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
