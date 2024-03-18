package com.example.johnscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController{

    @FXML
    public Button ContinueButton;

    @FXML
    public Button ExitButton;

    @FXML
    public PasswordField loginPassword;


    @FXML
    public TextField usernameField;


    private Parent root;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) throws IOException {

        String username=usernameField.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
        Parent root=fxmlLoader.load();

        DashboardController dashboardController=fxmlLoader.getController();
        dashboardController.displayUsername(username);

        Stage newStage = new Stage();
        Scene scene = new Scene(root, 1100, 700);
        newStage.setTitle("John's Cafe Home");
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();


        Item.CreateItemList();
        System.out.println(Item.itemList);

        // Close Previous Stage
        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(1);
    }
}

