package com.ubs.teste.controller.model;

public class SaleCalculationResult {
    private String establishment;
    private Integer qt;
    private Double total;
    private Double avgPrice;

    public SaleCalculationResult() {
        this.qt = 0;
        this.avgPrice = 0.0;
        this.total = 0.0;
    }

    public void addTotal(Integer qt, Double price) {
        this.total += price * qt;
    }

    public void addQt(Integer qt) {
        this.qt += qt;
    }

    public void calculateAvg() {
        avgPrice = this.total / this.qt;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getEstablishment() {
        return establishment;
    }

    public Integer getQt() {
        return qt;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public Double getTotal() {
        return total;
    }
}