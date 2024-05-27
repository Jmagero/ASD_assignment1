package edu.miu.cs.cs489appsd.lab1.productmgmtapp.model;

import java.time.LocalDate;

public class Product {
    private String productID;
    private String name;
    private LocalDate dateSupplied;
    private int quantityInStock;
    private double price;

    public Product() {
    }

    public Product(String productID, String name, LocalDate dateSupplied, int quantityInStock, double price) {
        this.productID = productID;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.price = price;
    }

    public Product(String name, LocalDate dateSupplied, int quantityInStock, double price) {
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
