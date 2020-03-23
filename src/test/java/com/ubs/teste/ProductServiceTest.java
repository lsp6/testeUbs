package com.ubs.teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ubs.teste.controller.model.SaleCalculationResult;
import com.ubs.teste.entity.Product;
import com.ubs.teste.repository.ProductRepository;
import com.ubs.teste.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void calculate2Products() {
        setMock2Products();
        List<SaleCalculationResult> saleCalculationResults = productService.calculateSale("EMMS", 2);
        SaleCalculationResult sale = saleCalculationResults.get(0);
        Assertions.assertEquals(55, sale.getQt());
        Assertions.assertEquals(((0.24 * 39) + (16 * 2.63)) / (39 + 16), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 39) + (16 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 1", sale.getEstablishment());

        sale = saleCalculationResults.get(1);
        Assertions.assertEquals(54, sale.getQt());
        Assertions.assertEquals(((0.24 * 39) + (15 * 2.63)) / (39 + 15), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 39) + (15 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 2", sale.getEstablishment());
    }

    @Test
    public void calculate2Products3Establishments() {
        setMock2Products();
        List<SaleCalculationResult> saleCalculationResults = productService.calculateSale("EMMS", 3);
        SaleCalculationResult sale = saleCalculationResults.get(0);
        Assertions.assertEquals(26 + 11, sale.getQt());
        Assertions.assertEquals(((0.24 * 26) + (11 * 2.63)) / (26 + 11), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 26) + (11 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 1", sale.getEstablishment());

        sale = saleCalculationResults.get(1);
        Assertions.assertEquals(26 + 10, sale.getQt());
        Assertions.assertEquals(((0.24 * 26) + (10 * 2.63)) / (26 + 10), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 26) + (10 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 2", sale.getEstablishment());

        sale = saleCalculationResults.get(2);
        Assertions.assertEquals(26 + 10, sale.getQt());
        Assertions.assertEquals(((0.24 * 26) + (10 * 2.63)) / (26 + 10), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 26) + (10 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 3", sale.getEstablishment());
    }

    @Test
    public void calculate2Products5Establishments() {
        setMock2Products();
        List<SaleCalculationResult> saleCalculationResults = productService.calculateSale("EMMS", 5);
        SaleCalculationResult sale = saleCalculationResults.get(0);
        Assertions.assertEquals(16 + 6, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63)) / (16 + 6), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 1", sale.getEstablishment());

        sale = saleCalculationResults.get(1);
        Assertions.assertEquals(16 + 6, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63)) / (16 + 6), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 2", sale.getEstablishment());

        sale = saleCalculationResults.get(2);
        Assertions.assertEquals(16 + 6, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63)) / (16 + 6), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 3", sale.getEstablishment());

        sale = saleCalculationResults.get(3);
        Assertions.assertEquals(15 + 7, sale.getQt());
        Assertions.assertEquals(((0.24 * 15) + (7 * 2.63)) / (15 + 7), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 15) + (7 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 4", sale.getEstablishment());

        sale = saleCalculationResults.get(4);
        Assertions.assertEquals(15 + 6, sale.getQt());
        Assertions.assertEquals(((0.24 * 15) + (6 * 2.63)) / (15 + 6), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 15) + (6 * 2.63), sale.getTotal());
        Assertions.assertEquals("Establishment 5", sale.getEstablishment());
    }

    @Test
    public void calculate8Products5Establishments() {
        setMockFiles1And2();
        List<SaleCalculationResult> saleCalculationResults = productService.calculateSale("EMMS", 5);
        SaleCalculationResult sale = saleCalculationResults.get(0);

        // A SOMA COM ZERO É SOMENTE PARA EVIDENCIAR A REGRA
        Assertions.assertEquals(16 + 6 + 15 + 7 + 20 + 4 + 12 + 1, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36)) / (16 + 6 + 15 + 7 + 20 + 4 + 12 + 1), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36), sale.getTotal());
        Assertions.assertEquals("Establishment 1", sale.getEstablishment());

        sale = saleCalculationResults.get(1);
        Assertions.assertEquals(16 + 6 + 15 + 7 + 20 + 4 + 12 + 0, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (0 * 9.36)) / (16 + 6 + 15 + 7 + 20 + 4 + 12 + 0), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (0 * 9.36), sale.getTotal());
        Assertions.assertEquals("Establishment 2", sale.getEstablishment());

        sale = saleCalculationResults.get(2);
        Assertions.assertEquals(16 + 6 + 15 + 7 + 20 + 3 + 13 + 0, sale.getQt());
        Assertions.assertEquals(((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (3 * 7.05) + (13 * 7.45) + (0 * 9.36)) / (16 + 6 + 15 + 7 + 20 + 3 + 13 + 0), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 16) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (3 * 7.05) + (13 * 7.45) + (0 * 9.36), sale.getTotal());
        Assertions.assertEquals("Establishment 3", sale.getEstablishment());

        sale = saleCalculationResults.get(3);
        Assertions.assertEquals(15 + 7 + 14 + 8 + 19 + 4 + 12 + 1, sale.getQt());
        Assertions.assertEquals(((0.24 * 15) + (7 * 2.63) + (14 * 3.75) + (8 * 5.39) + (19 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36)) / (15 + 7 + 14 + 8 + 19 + 4 + 12 + 1), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 15) + (7 * 2.63) + (14 * 3.75) + (8 * 5.39) + (19 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36), sale.getTotal());
        Assertions.assertEquals("Establishment 4", sale.getEstablishment());

        sale = saleCalculationResults.get(4);
        Assertions.assertEquals(15 + 6 + 15 + 7 + 20 + 4 + 12 + 1, sale.getQt());
        Assertions.assertEquals(((0.24 * 15) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36)) / (15 + 6 + 15 + 7 + 20 + 4 + 12 + 1), sale.getAvgPrice());
        Assertions.assertEquals((0.24 * 15) + (6 * 2.63) + (15 * 3.75) + (7 * 5.39) + (20 * 5.80) + (4 * 7.05) + (12 * 7.45) + (1 * 9.36), sale.getTotal());
        Assertions.assertEquals("Establishment 5", sale.getEstablishment());
    }


    @Test
    public void calculateSale() {
        setMockFiles1And2();
        List<SaleCalculationResult> saleCalculationResults = productService.calculateSale("EMMS", 2);
    }

    private void setMock2Products() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Product> products = new ArrayList<>();
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":78,\"price\":\"0.24\",\"type\":\"S\",\"industry\":\"Broadcasting\",\"origin\":\"NE\"}", Product.class));
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":31,\"price\":\"2.63\",\"type\":\"XS\",\"industry\":\"Broadcasting\",\"origin\":\"MI\"}", Product.class));

        when(productRepository.findByProductOrderByPrice("EMMS")).thenReturn(products);
    }

    private void setMockFiles1And2() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Product> products = new ArrayList<>();
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":78,\"price\":\"0.24\",\"type\":\"S\",\"industry\":\"Broadcasting\",\"origin\":\"NE\"}", Product.class));
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":31,\"price\":\"2.63\",\"type\":\"XS\",\"industry\":\"Broadcasting\",\"origin\":\"MI\"}", Product.class));
        products.add(gson.fromJson("{\n" +
                "                \"product\": \"EMMS\",\n" +
                "                \"quantity\": 74,\n" +
                "                \"price\": \"3.75\",\n" +
                "                \"type\": \"L\",\n" +
                "                \"industry\": \"Broadcasting\",\n" +
                "                \"origin\": \"TX\"\n" +
                "        }", Product.class));
        products.add(gson.fromJson("{\n" +
                "            \"product\": \"EMMS\",\n" +
                "                    \"quantity\": 36,\n" +
                "                    \"price\": \"5.39\",\n" +
                "                    \"type\": \"3XL\",\n" +
                "                    \"industry\": \"Broadcasting\",\n" +
                "                    \"origin\": \"MN\"\n" +
                "        }", Product.class));
        products.add(gson.fromJson("{\n" +
                "            \"product\": \"EMMS\",\n" +
                "                \"quantity\": 99,\n" +
                "                \"price\": \"5.80\",\n" +
                "                \"type\": \"2XL\",\n" +
                "                \"industry\": \"Broadcasting\",\n" +
                "                \"origin\": \"MI\"\n" +
                "        }", Product.class));
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":19,\"price\":\"7.05\",\"type\":\"S\",\"industry\":\"Broadcasting\",\"origin\":\"FL\"}", Product.class));
        products.add(gson.fromJson("{\n" +
                "            \"product\": \"EMMS\",\n" +
                "                    \"quantity\": 61,\n" +
                "                    \"price\": \"7.45\",\n" +
                "                    \"type\": \"2XL\",\n" +
                "                    \"industry\": \"Broadcasting\",\n" +
                "                    \"origin\": \"LA\"\n" +
                "        }", Product.class));
        products.add(gson.fromJson("{\"product\":\"EMMS\",\"quantity\":3,\"price\":\"9.36\",\"type\":\"XS\",\"industry\":\"Broadcasting\",\"origin\":\"PA\"}", Product.class));

        when(productRepository.findByProductOrderByPrice("EMMS")).thenReturn(products);
    }
}
