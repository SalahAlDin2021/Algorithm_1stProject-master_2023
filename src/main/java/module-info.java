module com.example.ahmadalgo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ahmadalgo to javafx.fxml;
    exports com.example.ahmadalgo;
}