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
import java.util.Random;

public class SelectDealersController {

    public Button addItemBtn;
    @FXML
    public Button addDealersButton;
    @FXML
    public AnchorPane addDealerPane;
    @FXML
    public TextField dealerCodeInput;
    @FXML
    public TextField dealerNameInput;
    @FXML
    public TextField contactInput;
    @FXML
    public TextField locationInput;
    @FXML
    public TextField noOfItemsInput;
    @FXML
    public TableColumn<Dealer, String> dealerId;
    @FXML
    public TableColumn<Dealer, String> dealerName;
    @FXML
    public TableColumn<Dealer, Integer> dealerTel;
    @FXML
    public TableColumn<Dealer, String> dealerLocation;
    @FXML
    public TableColumn<Dealer, Integer> noOfItems;



    @FXML
    public TableView<Dealer> dealerTableView;

    @FXML
    public TableView<Dealer> selectTableView;

    @FXML
    public TableColumn<Object, Object> selectDealerId1;
    @FXML
    public TableColumn<Object, Object> selectDealerName1;
    @FXML
    public TableColumn<Object, Object> selectDealerTel1;
    @FXML
    public TableColumn<Object, Object> selecetDealerLocation1;
    @FXML
    public TableColumn<Object, Object> selectnoOfItems1;
    @FXML
    public Button selectDealersButton;
    @FXML
    public Label label2;
    @FXML
    public Label label1;




    private final ObservableList<Dealer> dealerList = FXCollections.observableArrayList();
    static ObservableList<Dealer> selectedDealers = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws IOException {
        // Initialize the TableView columns to display dealer information
        dealerId.setCellValueFactory(new PropertyValueFactory<>("dealerCode"));
        dealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        dealerTel.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        dealerLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        noOfItems.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));

        selectDealerId1.setCellValueFactory(new PropertyValueFactory<>("dealerCode"));
        selectDealerName1.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        selectDealerTel1.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        selecetDealerLocation1.setCellValueFactory(new PropertyValueFactory<>("location"));
        selectnoOfItems1.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));


        try {
            List<Dealer> existingDealers = FileHandler.ReadDealersFile();
            dealerList.addAll(existingDealers);
            Dealer.sortByLocation(dealerList);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(dealerList);
        dealerTableView.setItems(dealerList);
        dealerTableView.setTableMenuButtonVisible(false);
        addDealerPane.setVisible(false);
        dealerTableView.setVisible(true);
        selectTableView.setVisible(true);
    }

    public void onAddItemBtnClick(ActionEvent actionEvent) {
        String dealerCode = dealerCodeInput.getText();
        String dealerName = dealerNameInput.getText();
        int contactNumber = Integer.parseInt(contactInput.getText());
        String location = locationInput.getText();
        int itemQuantity = Integer.parseInt(noOfItemsInput.getText());

        Dealer newDealer = new Dealer(dealerCode, dealerName, contactNumber, location, itemQuantity);

        dealerList.add(newDealer);
        System.out.println(dealerList);

        dealerNameInput.clear();
        dealerCodeInput.clear();
        contactInput.clear();
        locationInput.clear();
        noOfItemsInput.clear();

        try {
            List<Dealer> existingDealers = FileHandler.ReadDealersFile();
            existingDealers.add(newDealer);
            FileHandler.SaveDealersToFile(existingDealers);

        } catch (IOException e) {
            e.printStackTrace();
        }


        addDealerPane.setVisible(false);
        dealerTableView.setVisible(true);
        selectTableView.setVisible(true);
        label1.setVisible(true);
        label2.setVisible(true);

    }

    public void onAddDealersButtonClick(ActionEvent actionEvent) {
        dealerTableView.setVisible(false);
        selectTableView.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        addDealerPane.setVisible(true);
    }

    public void onSelectDealersBtn(ActionEvent actionEvent) {
        selectedDealers.clear();

        for(int i=0; i<4; i++){
            Dealer randomDealer = selectRandomObject(dealerList);
            if (!selectedDealers.contains(randomDealer)) {
                this.selectedDealers.add(randomDealer);
            } else {
                i--;
            }
        }

        selectTableView.setItems(selectedDealers);
        selectTableView.setFixedCellSize(20);
        System.out.println(selectedDealers);
    }


    public Dealer selectRandomObject(List<Dealer> objectList) {
        Random random = new Random();
        int randomNo = random.nextInt(objectList.size());
        return objectList.get(randomNo);
    }

    public void setSelectedDealers(ObservableList<Dealer> selectedDealers) {
        this.selectedDealers = selectedDealers;
    }

}
