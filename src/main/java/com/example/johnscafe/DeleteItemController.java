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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.example.johnscafe.Item.itemList;

public class DeleteItemController {

    @FXML
    private Label itemCodeLabel;
    @FXML
    private Label itemQuantityLabel;
    @FXML
    private Label itemPriceLabel;
    @FXML
    private Label itemBrandLabel;
    @FXML
    private Label itemNameLabel;
    @FXML
    private Label itemCategoryLabel;
    @FXML
    private Label itemDateLabel;
    @FXML
    private TextField itemCodeInput;

    @FXML
    private Button previewDetailsButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    private AnchorPane itemDetailsPane;
    @FXML
    private ImageView itemImageView;

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
        // Automatically load the item Details to image View

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

    @FXML
    //Preview the Details of the item
    private void onPreviewButtonClick(ActionEvent actionEvent) throws IOException {
        itemTable.setVisible(false);
        String itemCode = itemCodeInput.getText().trim();
        Item selectedItem = getItemByCode(itemCode);

        if (selectedItem != null) {
            itemCodeLabel.setText(selectedItem.getItemId());
            itemNameLabel.setText(selectedItem.getItemName());
            itemBrandLabel.setText(selectedItem.getBrand());
            itemPriceLabel.setText(String.valueOf(selectedItem.getItemPrice()));
            itemQuantityLabel.setText(String.valueOf(selectedItem.getQuantity()));
            itemCategoryLabel.setText(selectedItem.getCategory());
            itemDateLabel.setText(selectedItem.getPurchasedDate());

            String imagePath = "/com/example/johnscafe/Product Images/" + selectedItem.getImageFile();
            Optional<InputStream> imageStream = Optional.ofNullable(getClass().getResourceAsStream(imagePath));

            if (imageStream.isPresent()) {
                Image image = new Image(imageStream.get());
                itemImageView.setImage(image);
            } else {
                // Handle the case when the image is not found
                // For example, set a default image or show an error message
                itemImageView.setImage(null); // Set a default image or leave it empty
            }

            itemDetailsPane.setVisible(true);
            deleteItemButton.setVisible(true);
        } else {
            // Show an error message or handle the case when the item with the entered item code is not found.
        }
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

    @FXML
    private boolean onDeleteButtonClick(ActionEvent actionEvent) throws IOException {
        String itemCode = itemCodeInput.getText().trim();
        Item selectedItem = getItemByCode(itemCode);

        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this item?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, proceed with deletion
                Item.itemList.remove(selectedItem);

                // Delete the image file associated with the deleted item
                String imagePath = "/com/example/johnscafe/Product Images/" + selectedItem.getImageFile();
                try {
                    Files.delete(Path.of(imagePath));
                    System.out.println("Image file deleted successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Hide the panes after deletion
                itemDetailsPane.setVisible(false);
                deleteItemButton.setVisible(false);
                initialize();
                itemTable.setVisible(true);
                return true; // Return true to indicate successful deletion
            } else {
                // User clicked Cancel, do nothing or handle the case accordingly
                return false; // Return false to indicate deletion was canceled
            }
        } else {
            itemTable.setVisible(true);
            // Show an error message or handle the case when the item with the entered item code is not found.
            return false; // Return false to indicate deletion failed
        }
    }
}
