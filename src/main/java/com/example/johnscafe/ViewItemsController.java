package com.example.johnscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ViewItemsController {

    public AnchorPane itemDetailsPane;
    @FXML
    public Label itemQuantityLabel;
    @FXML
    public Label itemPriceLabel;
    @FXML
    public Label itemBrandLabel;
    @FXML
    public Label itemNameLabel;
    @FXML
    public Label itemCategoryLabel;
    @FXML
    public Label itemDateLabel;
    @FXML
    public ImageView itemImageView;

    @FXML
    public Label itemCodeLabel;

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
    private void initialize() {
        // This method is automatically called when the FXML is loaded.

        // Use PropertyValueFactory to bind each TableColumn to the corresponding property of the Item class.
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        price.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        startingDate.setCellValueFactory(new PropertyValueFactory<>("purchasedDate"));

        Item.sort(Item.itemList);
        ObservableList<Item> observableItemList = FXCollections.observableArrayList(Item.itemList);
        itemTable.setItems(observableItemList);
        itemTable.setTableMenuButtonVisible(false);


        // Set a selection change listener on the itemTable
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the itemDetailsPane with the details of the selected item
                updateItemDetails(newValue);
            }
        });
    }

    private void updateItemDetails(Item selected) {
        itemCodeLabel.setText(selected.getItemId());
        itemNameLabel.setText(selected.getItemName());
        itemBrandLabel.setText(selected.getBrand());
        itemPriceLabel.setText(String.valueOf(selected.getItemPrice()));
        itemQuantityLabel.setText(String.valueOf(selected.getQuantity()));
        itemCategoryLabel.setText(selected.getCategory());
        itemDateLabel.setText(selected.getPurchasedDate());

        String imagePath = "/com/example/johnscafe/Product Images/" + selected.getImageFile();
        try {
            itemImageView.setImage(new Image(imagePath));
        } catch (Exception e) {
            // Handle the case when the image is not found
            itemImageView.setImage(null); // Set a default image or leave it empty
        }
    }
}
