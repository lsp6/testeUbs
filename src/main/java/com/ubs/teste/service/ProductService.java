package com.ubs.teste.service;

import com.google.gson.GsonBuilder;
import com.ubs.teste.controller.model.LotModel;
import com.ubs.teste.controller.model.SaleCalculationResult;
import com.ubs.teste.entity.Product;
import com.ubs.teste.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    @Value("${import.path}")
    private String path;

    @Value("${import.readPath}")
    private String readPath;

    @Value("${import.errorPath}")
    private String errorPath;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void importProducts() {
        long startTime = System.currentTimeMillis();
        File dir = new File(File.listRoots()[0].getAbsolutePath() + path);
        File[] fs = dir.listFiles();
        if (fs != null) {
            Arrays.asList(fs).parallelStream().forEach(f -> {
                if (!f.isDirectory()) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
                        LotModel lot = new GsonBuilder().create().fromJson(br, LotModel.class);
                        lot.getData().parallelStream().forEach(p -> {
                            try {
                                productRepository.save(new Product(p.getProduct(), p.getQuantity(), p.getPrice(), p.getType(),
                                        p.getIndustry(), p.getOrigin(), f.getName()));
                            } catch (DataIntegrityViolationException e) {
                                log.warn(String.format("Product %s already exists", p.toString()));
                            }
                        });
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        File errorFile = new File(f.getParent() + errorPath + "\\" + f.getName()
                                .substring(0, f.getName().lastIndexOf("."))
                                + System.currentTimeMillis()
                                + f.getName().substring(f.getName().lastIndexOf(".")));
                        f.renameTo(new File(errorFile.getAbsolutePath()));
                    } finally {
                        File readFile = new File(f.getParent() + readPath + "\\" + f.getName()
                                .substring(0, f.getName().lastIndexOf("."))
                                + System.currentTimeMillis()
                                + f.getName().substring(f.getName().lastIndexOf(".")));
                        f.renameTo(new File(readFile.getAbsolutePath()));
                    }
                }
            });
        }

        log.info(String.format("%d milliseconds ellapsed", System.currentTimeMillis() - startTime));
    }

    public List<SaleCalculationResult> calculateSale(final String product, final Integer establishmentQuantity) {
        List<Product> products = productRepository.findByProductOrderByPrice(product);

        List<SaleCalculationResult> sales = new ArrayList<>();
        int nextAddRemainderPos = 0;

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            int qtdDivision = (int) Math.floor(p.getQuantity() / establishmentQuantity);
            int remainder = p.getQuantity() - (qtdDivision * establishmentQuantity);
            int roundAux = -1;

            if (nextAddRemainderPos + remainder > establishmentQuantity) {
                roundAux += nextAddRemainderPos + remainder - establishmentQuantity + 1;
            }

            for (int j = 0; j < establishmentQuantity; j ++) {
                SaleCalculationResult sale;
                if (sales.size() > j) {
                    sale = sales.get(j);
                } else {
                    sale  = new SaleCalculationResult();
                    sales.add(sale);
                }

                int addition = 0;
                if (remainder > 0 && (j >= nextAddRemainderPos || j < roundAux)) {
                    addition ++;
                    remainder --;
                    if (remainder == 0) {
                        nextAddRemainderPos = roundAux > -1 ? roundAux: j+1 == establishmentQuantity ? 0 : (j + 1);
                    }
                }

                int finalQt = qtdDivision + addition;

                sale.setEstablishment(String.format("Establishment %d", j + 1));
                sale.addQt(finalQt);
                sale.addTotal(finalQt, p.getPrice());
                sale.calculateAvg();
            }
        }

        return sales;
    }
}
