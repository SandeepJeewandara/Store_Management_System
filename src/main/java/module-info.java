module com.example.johnscafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.johnscafe to javafx.fxml;
    exports com.example.johnscafe;
}