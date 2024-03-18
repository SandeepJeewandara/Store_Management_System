package com.example.johnscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DealerItemsController {
    @FXML
    public TableView<Dealer> selectTableView;
    @FXML
    public TableColumn<Dealer, String> selectDealerId1;
    @FXML
    public TableColumn<Dealer, String> selectDealerName1;
    @FXML
    public TableColumn<Dealer, Integer> selectDealerTel1;
    @FXML
    public TableColumn<Dealer, String> selecetDealerLocation1;
    @FXML
    public TableColumn<Dealer, Integer> selectnoOfItems1;
    @FXML
    public Label label2;
    @FXML
    public Label label1;
    @FXML
    public TableView<DealerItem> dealerItemsView;
    @FXML
    public TableColumn<DealerItem, String> selectDealerName;
    @FXML
    public TableColumn<DealerItem, String> selectItemName;
    @FXML
    public TableColumn<DealerItem, String> selectItemBrand;
    @FXML
    public TableColumn<DealerItem, Double> selectItemPrice;
    @FXML
    public TableColumn<DealerItem, Integer> selectStockQuantity;
    @FXML
    public Button addItemPreviewButton;
    @FXML
    public AnchorPane addDealerItemPane;
    @FXML
    public TextField addItemName;
    @FXML
    public TextField addBrand;
    @FXML
    public TextField addItemPrice;
    @FXML
    public TextField addQuantity;

    @FXML
    public TextField addDealer;

    private final ObservableList<Dealer> selectedDealers = FXCollections.observableArrayList();

    private static final List<DealerItem> dealerItems = new ArrayList<>();
    public AnchorPane selectDealerMenu;
    public Button addConfirm;
    public ChoiceBox<String> selectedDealerBox;

    @FXML
    public void initialize() throws IOException {

        selectedDealers.addAll(SelectDealersController.selectedDealers);

        //Get the User Inputs Dealers Name
        for(Dealer dealer:selectedDealers){
            selectedDealerBox.getItems().add(dealer.getDealerName());
        }

        // Initialize the TableView columns to display dealer items information
        selectDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        selectItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        selectItemBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        selectItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        selectStockQuantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        selectedDealerBox.setOnAction(this::onSelectedDealerChanged);

        try {
            List<DealerItem> existingDealers = FileHandler.ReadDealerItemsFile();
            dealerItems.clear();
            dealerItems.addAll(existingDealers);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Preview Dealer's items in a Table
        dealerItemsView.setItems(FXCollections.observableArrayList(dealerItems));
        dealerItemsView.setTableMenuButtonVisible(false);
        addDealerItemPane.setVisible(false);
        label1.setVisible(true);


    }

    public void onAddItemPreview(ActionEvent actionEvent) {

        label1.setVisible(false);
        dealerItemsView.setVisible(false);
        addDealerItemPane.setVisible(true);
        selectedDealerBox.setVisible(false);
    }

    //Adding New Dealer to the System
    public void onAddConfirmClick(ActionEvent actionEvent) throws IOException {
        String itemDealer = addDealer.getText();
        String itemName = addItemName.getText();
        String itemBrand = addBrand.getText();
        double itemPrice = Double.parseDouble(addItemPrice.getText());
        int stockQuantity = Integer.parseInt(addQuantity.getText());

        DealerItem newDealerItem = new DealerItem(itemDealer, itemName, itemBrand, itemPrice, stockQuantity);
        dealerItems.add(newDealerItem);
        FileHandler.SaveDealerItemsToFile(dealerItems);



        addDealer.clear();
        addItemName.clear();
        addBrand.clear();
        addItemPrice.clear();
        addQuantity.clear();

        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addDealerItemPane.setVisible(false);
        dealerItemsView.setVisible(true);
        selectTableView.setVisible(true);
        label1.setVisible(true);
    }

    @FXML
    public void onSelectedDealerChanged(ActionEvent actionEvent) {
        String selectedDealerName = selectedDealerBox.getValue();
        if (selectedDealerName != null) {
            ObservableList<DealerItem> itemsForSelectedDealer = FXCollections.observableArrayList();
            for (DealerItem dealerItem : dealerItems) {
                if (dealerItem.getDealerName().equals(selectedDealerName.trim())) {
                    itemsForSelectedDealer.add(dealerItem);
                }
            }
            dealerItemsView.setItems(itemsForSelectedDealer);
        } else {
            // If no dealer is selected, show all dealer items
            dealerItemsView.setItems(FXCollections.observableArrayList(dealerItems));
        }
    }

    public void setSelectedDealers(ObservableList<Dealer> selectedDealers) {
        this.selectedDealers.setAll(selectedDealers);
    }
}
