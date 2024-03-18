package com.example.johnscafe;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validators {

    // Method to validate if the input string is numeric
    public static boolean isNumeric(String str) {
        return Pattern.matches("-?\\d+", str);
    }

    // Method to validate if the input string represents a positive integer
    public static boolean isPositiveInteger(String str) {
        return Pattern.matches("\\d+", str);
    }

    // Method to validate if the input string represents a positive decimal number
    public static boolean isPositiveDecimal(String str) {
        return Pattern.matches("\\d+(\\.\\d+)?", str);
    }

    public static String[] getAllItemIds(List<Item> itemList) {
        List<String> itemIdsList = new ArrayList<>();

        for (Item item : itemList) {
            itemIdsList.add(item.getItemId());
        }

        // Convert the list of item IDs to an array
        String[] itemIdsArray = itemIdsList.toArray(new String[0]);

        return itemIdsArray;
    }


}
