package edu.miu.cs.cs489appsd.lab1.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1.productmgmtapp.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProductMgmtApp {

    public static void main(String[] args) {
        Product[] products = {
             new Product(  "3128874119", "Banana", LocalDate.parse("2023-01-24") ,124 ,0.55),
                new Product("2927458265" ,"Apple", LocalDate.parse("2022-12-09") ,18, 1.09),
                new Product("9189927460", "Carrot",  LocalDate.parse("2023-03-31"), 89,  2.99)
        };

        List<Product> sortedList = Arrays.stream(products)
                .sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());


        printProductsAsJson(sortedList);
        printProductsAsXml(sortedList);
        printProductsAsCsv(sortedList);


        SpringApplication.run(ProductMgmtApp.class, args);
    }

    private static void printProductsAsJson(List<Product> products) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            jsonBuilder.append("  { ");
            jsonBuilder.append("\"productId\": ").append(product.getProductID()).append(", ");
            jsonBuilder.append("\"name\": \"").append(product.getName()).append("\", ");
            jsonBuilder.append("\"dateSupplied\": \"").append(product.getDateSupplied()).append("\", ");
            jsonBuilder.append("\"quantityInStock\": ").append(product.getQuantityInStock()).append(", ");
            jsonBuilder.append("\"unitPrice\": ").append(product.getPrice());
            jsonBuilder.append(" }");
            if (i < products.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        jsonBuilder.append("]");
        System.out.println("Printed in JSON Format:\n" + jsonBuilder.toString());
    }

    private static void printProductsAsXml(List<Product> products) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?>\n");
        xmlBuilder.append("<products>\n");
        for (Product product : products) {
            xmlBuilder.append("  <product");
            xmlBuilder.append(" productId=\"").append(product.getProductID()).append("\"");
            xmlBuilder.append(" name=\"").append(product.getName()).append("\"");
            xmlBuilder.append(" dateSupplied=\"").append(product.getDateSupplied()).append("\"");
            xmlBuilder.append(" quantityInStock=\"").append(product.getQuantityInStock()).append("\"");
            xmlBuilder.append(" unitPrice=\"").append(product.getPrice()).append("\"");
            xmlBuilder.append("/>\n");
        }
        xmlBuilder.append("</products>");
        System.out.println("------------------------------------");
        System.out.println("Printed in XML Format\n" + xmlBuilder.toString());
    }

    private static void printProductsAsCsv(List<Product> products) {
        int productIdWidth = "productId".length();
        int nameWidth = "name".length();
        int dateSuppliedWidth = "dateSupplied".length();
        int quantityInStockWidth = "quantityInStock".length();
        int unitPriceWidth = "unitPrice".length();

        for (Product product : products) {
            productIdWidth = Math.max(productIdWidth, String.valueOf(product.getProductID()).length());
            nameWidth = Math.max(nameWidth, product.getName().length());
            dateSuppliedWidth = Math.max(dateSuppliedWidth, product.getDateSupplied().toString().length());
            quantityInStockWidth = Math.max(quantityInStockWidth, String.valueOf(product.getQuantityInStock()).length());
            unitPriceWidth = Math.max(unitPriceWidth, String.valueOf(product.getPrice()).length());
        }

        String format = "%-" + productIdWidth + "s %-"+ nameWidth +"s %-"+ dateSuppliedWidth +"s %-"+ quantityInStockWidth +"s %-"+ unitPriceWidth + "s\n";

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(String.format(format, "productId", "name", "dateSupplied", "quantityInStock", "unitPrice"));
        for (Product product : products) {
            csvBuilder.append(String.format(format,
                    product.getProductID(),
                    product.getName(),
                    product.getDateSupplied(),
                    product.getQuantityInStock(),
                    product.getPrice()));
        }
        System.out.println("------------------------------------");
        System.out.println("Printed in Comma-Separated Value(CSV) Format");
        System.out.println(csvBuilder.toString());
    }

}
