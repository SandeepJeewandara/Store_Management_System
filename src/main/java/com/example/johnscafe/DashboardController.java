package com.example.johnscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.johnscafe.Item.itemList;


public class DashboardController {

    @FXML
    public Label usernameLabel;
    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> itemCode;

    @FXML
    private TableColumn<Item, String> name;

    @FXML
    private TableColumn<Item, String> brand;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, Integer> quantity;

    @FXML
    private TableColumn<Item, String> category;

    @FXML
    private TableColumn<Item, String> startingDate;


    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button updateItemButton;

    @FXML
    private Button saveItemButton;

    @FXML
    private Button viewItemButton;

    @FXML
    private Button selectDealersButton;

    @FXML
    private Button dealerItemsButton;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane titlePane;


    @FXML
    private AnchorPane pnAddItems;

    @FXML
    private AnchorPane pnDeleteItem;

    @FXML
    private AnchorPane pnUpdateItem;

    @FXML
    private AnchorPane pnSaveItem;

    @FXML
    private AnchorPane pnViewItems;

    @FXML
    private AnchorPane pnDealerItems;

    @FXML
    private AnchorPane pnSelectDealers;


    @FXML
    private StackPane stackPane;

    public void displayUsername(String username) {
        usernameLabel.setText(username);
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {

        if (event.getSource() == addItemButton) {
            titleLabel.setText("Add Item");
            pnAddItems.toFront();

            FXMLLoader addItemsLoader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
            AnchorPane pnAddItemsContent = addItemsLoader.load();
            AddItemController addItemController = addItemsLoader.getController();

            pnAddItems.getChildren().clear();
            pnAddItems.getChildren().addAll(pnAddItemsContent);
        } else if (event.getSource() == deleteItemButton) {
            titleLabel.setText("Delete Item");
            pnAddItems.toFront();

            FXMLLoader deleteItemsLoader = new FXMLLoader(getClass().getResource("DeleteItem.fxml"));
            AnchorPane pnDeleteItemsContent = deleteItemsLoader.load();
            DeleteItemController deleteItemController = deleteItemsLoader.getController();

            pnAddItems.getChildren().clear();
            pnAddItems.getChildren().addAll(pnDeleteItemsContent);

        } else if (event.getSource() == updateItemButton) {
            titleLabel.setText("Update Item");
            pnUpdateItem.toFront();

            FXMLLoader updateItemsLoader = new FXMLLoader(getClass().getResource("UpdateItems1.fxml"));
            AnchorPane pnUpdateItemsContent = updateItemsLoader.load();
            UpdateItemController updateItemController = updateItemsLoader.getController();

            pnUpdateItem.getChildren().clear();
            pnUpdateItem.getChildren().addAll(pnUpdateItemsContent);


        } else if (event.getSource() == saveItemButton) {
            FileHandler saveItemController = new FileHandler();
            try {
                saveItemController.SaveItemsToFile(itemList);
            } catch (IOException e) {
                System.err.println("Error while saving items");
            }


        } else if (event.getSource() == viewItemButton) {
            titleLabel.setText("View Items");
            pnViewItems.toFront();

            FXMLLoader viewItemsLoader = new FXMLLoader(getClass().getResource("ViewItems.fxml"));
            AnchorPane pnViewItemsContent = viewItemsLoader.load();

            ViewItemsController viewItemsController = viewItemsLoader.getController();

            pnViewItems.getChildren().clear();
            pnViewItems.getChildren().addAll(pnViewItemsContent);

        } else if (event.getSource() == selectDealersButton) {
            titleLabel.setText("Select Dealers");
            pnSelectDealers.toFront();

            FXMLLoader selectDealersLoader = new FXMLLoader(getClass().getResource("SelectDealers.fxml"));
            AnchorPane pnSelectDealersContent = selectDealersLoader.load();
            SelectDealersController selectDealersController = selectDealersLoader.getController();

            pnSelectDealers.getChildren().clear();
            pnSelectDealers.getChildren().addAll(pnSelectDealersContent);

        } else if (event.getSource() == dealerItemsButton) {

            titleLabel.setText("Dealer Items");
            pnDealerItems.toFront();

            FXMLLoader dealersItemsLoader = new FXMLLoader(getClass().getResource("DealerItemsDetails.fxml"));
            AnchorPane pnDealerItemsContent = dealersItemsLoader.load();
            DealerItemsController dealerItemsController = dealersItemsLoader.getController();

            pnDealerItems.getChildren().clear();
            pnDealerItems.getChildren().addAll(pnDealerItemsContent);
        }

    }

    public void onBackToLoginButton(ActionEvent actionEvent) {
        try {
            // Load the HomePage.fxml file
            FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = homePageLoader.load();
            Stage homePageStage = new Stage();
            homePageStage.setTitle("Home Page");
            Scene scene = new Scene(root);
            homePageStage.setScene(scene);
            homePageStage.show();
            Stage currentStage = (Stage) stackPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}


