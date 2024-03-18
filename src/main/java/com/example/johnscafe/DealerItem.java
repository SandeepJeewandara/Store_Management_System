package com.example.johnscafe;

import java.io.Serializable;

public class DealerItem implements Serializable {

    private String dealerName;
    private String itemName;
    private String brand;
    private double itemPrice;
    private int stockQuantity;

    public DealerItem() {
        // Default constructor
    }

    public DealerItem(String dealerName, String itemName, String brand, double itemPrice, int quantity) {
        this.dealerName = dealerName;
        this.itemName = itemName;
        this.brand = brand;
        this.itemPrice = itemPrice;
        this.stockQuantity = quantity;
    }


    public String getDealerItemName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDealerName() {
        return dealerName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
