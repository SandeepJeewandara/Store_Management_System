package com.example.johnscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PromptBoxController {
   

    public Button okButton;
    public AnchorPane promptBoxPain;
    public Label promptText;


    public void onOkButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setPromptText(String  message) {
        promptText.setText(message);
    }

    public void showPromptMessage(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PromptBox.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        PromptBoxController controller = loader.getController();
        controller.setPromptText(message);
        stage.setResizable(false);
        stage.show();
    }

}
