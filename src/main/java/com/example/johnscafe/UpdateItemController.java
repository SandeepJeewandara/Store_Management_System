package com.example.johnscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Optional;

import static com.example.johnscafe.Item.itemList;

public class UpdateItemController {
    public TextField itemCodeInput;
    public Button previewDetailsButton;
    public AnchorPane itemDetailsPane;
    public Label itemCodeLabel;
    public Label itemQuantityLabel;
    public Label itemPriceLabel;
    public Label itemBrandLabel;
    public Label itemNameLabel;
    public Label itemCategoryLabel;
    public Label itemDateLabel;
    public ImageView itemImageView;
    public TextField defaultItemCodeInput;
    public TextField defaultItemNameInput;
    public TextField defaultItemBrandInput;
    public TextField defaultItemQuantityInput;
    public TextField defaultItemPriceInput;
    public TextField defaultItemCategoryInput;
    public TextField defaultItemPurchasedDate;
    private Item selectedItem; // Store the selected item here
    public TextField itemIdInput;
    public TextField itemCode1Input;
    public TextField itemName1Input;
    public TextField itemBrand1Input;
    public TextField itemQuantity1Input;
    public TextField itemCategory1Input;
    public TextField itemPurchased1Date;
    public TextField itemPrice1Input;
    public Button updateDetailsButton;
    public Button saveDetailsButton1;
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


    private final PromptBoxController promptBoxController = new PromptBoxController();

    @FXML
    private void initialize() {
        // Use PropertyValueFactory to bind each TableColumn to the corresponding property of the Item class.
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        price.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        startingDate.setCellValueFactory(new PropertyValueFactory<>("purchasedDate"));

        ObservableList<Item> observableItemList = FXCollections.observableArrayList(Item.itemList);
        itemTable.setItems(observableItemList);
        itemTable.setTableMenuButtonVisible(false);
    }

    public void onPreviewButtonClick(ActionEvent actionEvent) throws IOException {
        String itemCode = itemCodeInput.getText().trim();
        selectedItem = getItemByCode(itemCode);
        itemTable.setVisible(false);

        if (selectedItem != null) {
            itemCodeLabel.setText(selectedItem.getItemId());
            itemNameLabel.setText(selectedItem.getItemName());
            itemBrandLabel.setText(selectedItem.getBrand());
            itemPriceLabel.setText(String.valueOf(selectedItem.getItemPrice()));
            itemQuantityLabel.setText(String.valueOf(selectedItem.getQuantity()));
            itemCategoryLabel.setText(selectedItem.getCategory());
            itemDateLabel.setText(selectedItem.getPurchasedDate());


            // Set default values in hidden text fields
            defaultItemCodeInput.setText(selectedItem.getItemId());
            defaultItemNameInput.setText(selectedItem.getItemName());
            defaultItemBrandInput.setText(selectedItem.getBrand());
            defaultItemPriceInput.setText(String.valueOf(selectedItem.getItemPrice()));
            defaultItemQuantityInput.setText(String.valueOf(selectedItem.getQuantity()));
            defaultItemCategoryInput.setText(selectedItem.getCategory());
            defaultItemPurchasedDate.setText(selectedItem.getPurchasedDate());

            String imagePath = "/com/example/johnscafe/Product Images/" + selectedItem.getImageFile();
            Optional<InputStream> imageStream = Optional.ofNullable(getClass().getResourceAsStream(imagePath));

            if (imageStream.isPresent()) {
                Image image = new Image(imageStream.get());
                itemImageView.setImage(image);
            } else {
                itemImageView.setImage(null);
            }
            // Show labels
            itemCodeLabel.setVisible(true);
            itemNameLabel.setVisible(true);
            itemBrandLabel.setVisible(true);
            itemPriceLabel.setVisible(true);
            itemQuantityLabel.setVisible(true);
            itemCategoryLabel.setVisible(true);
            itemDateLabel.setVisible(true);

            itemCode1Input.setVisible(false);
            itemName1Input.setVisible(false);
            itemBrand1Input.setVisible(false);
            itemPrice1Input.setVisible(false);
            itemQuantity1Input.setVisible(false);
            itemCategory1Input.setVisible(false);
            itemPurchased1Date.setVisible(false);

            itemDetailsPane.setVisible(true);
            updateDetailsButton.setVisible(true);
        }

    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        // Hide labels
        itemCodeLabel.setVisible(false);
        itemNameLabel.setVisible(false);
        itemBrandLabel.setVisible(false);
        itemPriceLabel.setVisible(false);
        itemQuantityLabel.setVisible(false);
        itemCategoryLabel.setVisible(false);
        itemDateLabel.setVisible(false);

        // Show text fields
        itemCode1Input.setVisible(true);
        itemName1Input.setVisible(true);
        itemBrand1Input.setVisible(true);
        itemPrice1Input.setVisible(true);
        itemQuantity1Input.setVisible(true);
        itemCategory1Input.setVisible(true);
        itemPurchased1Date.setVisible(true);

        // Set default values from hidden text fields to visible text fields
        itemCode1Input.setText(defaultItemCodeInput.getText());
        itemName1Input.setText(defaultItemNameInput.getText());
        itemBrand1Input.setText(defaultItemBrandInput.getText());
        itemPrice1Input.setText(defaultItemPriceInput.getText());
        itemQuantity1Input.setText(defaultItemQuantityInput.getText());
        itemCategory1Input.setText(defaultItemCategoryInput.getText());
        itemPurchased1Date.setText(defaultItemPurchasedDate.getText());

        saveDetailsButton1.setVisible(true);
    }

    public void onSaveButtonClick(ActionEvent actionEvent) throws IOException {
        // Check if an item was previewed before saving changes
        String itemId = itemCode1Input.getText();

        if (!Validators.isNumeric(itemId)) {
            promptBoxController.showPromptMessage("Item code must be numeric.");
            return;
        }


        String itemName = itemName1Input.getText();
        String brand = itemBrand1Input.getText();
        String category = itemCategory1Input.getText();
        String purchaseDate=itemPurchased1Date.getText();

        if (itemId.isEmpty() || itemName.isEmpty() || brand.isEmpty() || category.isEmpty()|| purchaseDate.isEmpty()) {
            promptBoxController.showPromptMessage("Please fill all the required fields.");
            return;
        }

        double itemPrice;
        try {
            itemPrice = Double.parseDouble(itemPrice1Input.getText());
        } catch (NumberFormatException e) {
            promptBoxController.showPromptMessage("Item Price Must be Numeric.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(itemQuantity1Input.getText());
        } catch (NumberFormatException e) {
            promptBoxController.showPromptMessage("Item Quantity Must be Numeric.");
            return;
        }


        // Update the item properties with the values in the visible text fields
        selectedItem.setItemId(itemCode1Input.getText().trim());
        selectedItem.setItemName(itemName1Input.getText().trim());
        selectedItem.setBrand(itemBrand1Input.getText().trim());
        selectedItem.setItemPrice(Double.parseDouble(itemPrice1Input.getText().trim()));
        selectedItem.setQuantity(Integer.parseInt(itemQuantity1Input.getText().trim()));
        selectedItem.setCategory(itemCategory1Input.getText().trim());
        selectedItem.setPurchasedDate(itemPurchased1Date.getText().trim());

        saveDetailsButton1.setVisible(false);

        promptBoxController.showPromptMessage("Item Details Updated Successfully");
        itemDetailsPane.setVisible(false);
        initialize();
        itemTable.setVisible(true);

        clearInputFields();

    }

    private void clearInputFields() {
        itemCodeInput.clear();
        itemCode1Input.clear();
        itemName1Input.clear();
        itemQuantity1Input.clear();
        itemBrand1Input.clear();
        itemPrice1Input.clear();
        itemCategory1Input.clear();
        itemPurchased1Date.clear();

    }

    private Item getItemByCode(String itemCode) throws IOException {
        for (Item item : Item.itemList) {
            if (item.getItemId().equals(itemCode)) {
                return item;
            }
        }
        promptBoxController.showPromptMessage("Entered Item Code is Invalid");
        return null;
    }
}
