package com.example.johnscafe;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Item extends DealerItem implements Serializable {
    private static final long serialVersionUID=26958L;

    private String itemId;
    private String itemName;
    private String brand;
    private double itemPrice;
    private int quantity;
    private String category;
    private String purchasedDate;

    private String imageFile;


    //Getters
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getBrand() {
        return brand;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public String getImageFile() {
        return imageFile;
    }


    public Item(String itemId, String itemName, String brand, double itemPrice, int quantity, String category, String purchasedDate) {
        super(null, itemName, brand, itemPrice, quantity);
        this.itemId = itemId;
        this.category = category;
        this.purchasedDate = purchasedDate;
    }



    //Setters

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }



    public Item(String itemId, String itemName, String brand, double itemPrice, int quantity, String category, String purchasedDate, String imageFile) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.brand = brand;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.category = category;
        this.purchasedDate = purchasedDate;
        this.imageFile = imageFile;
    }



    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", purchasedDate='" + purchasedDate + '\'' +
                ", fileName="+imageFile+'\''+
                '}';
    }


    public static void main(String[] args) throws IOException {
        CreateItemList();
        System.out.println(itemList.toString());

    }
    static List<Item> itemList = new ArrayList<>();


    public static void CreateItemList() throws IOException {

        try {
            itemList = FileHandler.ReadItemsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(itemList);
    }

    //Sorting Algorithm for Item Code
    static void sort(List<Item> listName) {
        List<Item> sortedList = new ArrayList<>();

        while (!listName.isEmpty()) {
            Item min = listName.get(0); // Appending first item value as min
            for (Item i : listName) { // Comparing min with other values in the list with the same index
                if (i.getItemId().compareTo(min.getItemId()) < 0) {
                    min = i;
                }
            }
            sortedList.add(min); // Appending min value to an internal list
            listName.remove(min); // Removing min value from the main list
        }

        listName.addAll(sortedList);
    }

}

