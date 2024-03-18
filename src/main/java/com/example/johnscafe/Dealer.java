package com.example.johnscafe;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dealer implements Serializable {
    private static final long serialVersionUID = 97154L;

    private String dealerCode;
    private String dealerName;
    private int contactNumber;
    private String location;
    private int itemQuantity;

    static List<Dealer> dealerList = new ArrayList<>();

    // Constructors
    public Dealer(String dealerCode, String dealerName, int contactNumber, String location, int itemQuantity) {
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
        this.contactNumber = contactNumber;
        this.location = location;
        this.itemQuantity = itemQuantity;
    }

    // Getters
    public String getDealerCode() {
        return dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }


    // Sorting dealers by location (first letter of location name)
    public static void sortByLocation(List<Dealer> dealerList) {
        int n = dealerList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String location1 = dealerList.get(j).getLocation();
                String location2 = dealerList.get(j + 1).getLocation();
                if (!location1.isEmpty() && !location2.isEmpty()) {
                    char firstLetter1 = location1.charAt(0);
                    char firstLetter2 = location2.charAt(0);

                    if (firstLetter1 > firstLetter2) {
                        // Swap the dealers
                        Dealer temp = dealerList.get(j);
                        dealerList.set(j, dealerList.get(j + 1));
                        dealerList.set(j + 1, temp);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerCode='" + dealerCode + '\'' +
                ", dealerName='" + dealerName + '\'' +
                ", contactNumber=" + contactNumber +
                ", location='" + location + '\'' +
                ", itemQuantity=" + itemQuantity +
                '}';
    }

}
