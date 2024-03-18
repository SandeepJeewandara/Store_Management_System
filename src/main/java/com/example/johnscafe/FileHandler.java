package com.example.johnscafe;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FileHandler {

    public void SaveItemsToFile(List<Item> itemList) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saving Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Save Updated Data to Text File?");
        alert.showAndWait();

        File file = new File("JohnsCafe.txt");
        file.createNewFile();

        try {
            FileOutputStream fileOut = new FileOutputStream("JohnsCafe.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(itemList);

            objectOut.close();
            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static List<Item> ReadItemsFile() throws IOException {
        List<Item> itemList = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("JohnsCafe.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            itemList = (List<Item>) objectIn.readObject();

            objectIn.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public static List<Dealer> ReadDealersFile() throws IOException {
        List<Dealer> dealerList = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("Dealers.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            dealerList = (List<Dealer>) objectIn.readObject();

            objectIn.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dealerList;
    }


    public static void SaveDealersToFile(List<Dealer> dealerList) throws IOException {

        File file = new File("Dealers.txt");
        file.createNewFile();

        try {
            FileOutputStream fileOut = new FileOutputStream("Dealers.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(dealerList);

            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveDealerItemsToFile(List<DealerItem> dealerItems) throws IOException {

        File file = new File("DealerItems.txt");
        file.createNewFile();

        try {
            FileOutputStream fileOut = new FileOutputStream("DealerItems.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(dealerItems);

            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<DealerItem> ReadDealerItemsFile() throws IOException {
        List<DealerItem> dealerItems = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("DealerItems.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            dealerItems = (List<DealerItem>) objectIn.readObject();

            objectIn.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dealerItems;
    }







}

