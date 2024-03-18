package com.example.johnscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import static com.example.johnscafe.Item.itemList;

public class AddItemController {

    @FXML
    public Button addItemBtn;

    @FXML
    public BorderPane imagePane;

    @FXML
    public Button browseImageButton;

    @FXML
    private TextField brandInput;

    @FXML
    private TextField itemCategoryInput;

    @FXML
    private TextField itemCodeInput;

    @FXML
    private TextField itemNameInput;

    @FXML
    private TextField itemPriceInput;

    @FXML
    private DatePicker itemPurchaseInput;

    @FXML
    private TextField itemQuantityInput;

    private PromptBoxController promptBoxController = new PromptBoxController();

    private File selectedImageFile;


    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void onAddItemBtnClick(ActionEvent event) throws IOException {
        String itemId = itemCodeInput.getText();

        if (!Validators.isNumeric(itemId)) {
            promptBoxController.showPromptMessage("Item code must be numeric.");
            return;
        }

        for (Item item : itemList) {
            if (item.getItemId().equals(itemId)) {
                promptBoxController.showPromptMessage("Item Code is Already Exists");
                return;
            }
        }

        String itemName = itemNameInput.getText();
        String brand = brandInput.getText();
        String category = itemCategoryInput.getText();

        if (itemId.isEmpty() || itemName.isEmpty() || brand.isEmpty() || category.isEmpty()) {
            promptBoxController.showPromptMessage("Please fill all the required fields.");
            return;
        }


        double itemPrice;
        try {
            itemPrice = Double.parseDouble(itemPriceInput.getText());
        } catch (NumberFormatException e) {
            promptBoxController.showPromptMessage("Item Price Must be Numeric.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(itemQuantityInput.getText());
        } catch (NumberFormatException e) {
            promptBoxController.showPromptMessage("Item Quantity Must be Numeric.");
            return;
        }

        // Get the selected date from the DatePicker
        LocalDate purchasedDate = itemPurchaseInput.getValue();

        if (purchasedDate == null) {
            promptBoxController.showPromptMessage("Please select a purchase date.");
            return;
        }

        // Check if an image is selected

        String imageFileName = null;
        if (selectedImageFile != null) {
            // Save the selected image to the specific folder
            String destinationFolderPath = "src/main/resources/com/example/johnscafe/Product Images";


            try {
                // Create the destination folder if it doesn't exist
                Path destinationFolder = Path.of(destinationFolderPath);
                Files.createDirectories(destinationFolder);

                // Set the image file name to the Item object
                imageFileName = selectedImageFile.getName();

                // Copy the selected image file to the destination folder
                Path destinationFilePath = destinationFolder.resolve(imageFileName);
                Files.copy(selectedImageFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(destinationFilePath);
            } catch (IOException e) {
                e.printStackTrace();
                promptBoxController.showPromptMessage("Error while saving image");
            }

        }
        // Create a new item object with the input values
        Item newItem = new Item(itemId, itemName, brand, itemPrice, quantity, category, purchasedDate.toString(), imageFileName);

        // Add the new item to the itemList
        Item.itemList.add(newItem);
        System.out.println(itemList);

        promptBoxController.showPromptMessage("Item Added Successfully");

        // Clear the input fields after adding the item
        clearInputFields();
        imagePane.setCenter(null);
    }


    private void clearInputFields() {
        itemCodeInput.clear();
        itemNameInput.clear();
        brandInput.clear();
        itemPriceInput.clear();
        itemQuantityInput.clear();
        itemCategoryInput.clear();
        itemPurchaseInput.setValue(null);
    }


    @FXML
    private void onChooseImageBtnClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        selectedImageFile = fileChooser.showOpenDialog(null); // Store the selected file

        if (selectedImageFile != null) {
            // Display the selected image in the imagePane
            Image image = new Image(selectedImageFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(150.0);
            imagePane.setCenter(imageView);
        }
    }
}






