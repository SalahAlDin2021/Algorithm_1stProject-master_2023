package com.example.ahmadalgo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;


public class HelloController {
    TravelCost travelCost = new TravelCost();

    @FXML
    private Button loadFileButton;

    @FXML
    private TextArea fileDataTextArea;

    @FXML
    private ComboBox<String> startCityCombo;

    @FXML
    private ComboBox<String> endCityCombo;

    @FXML
    private Button calculateButton;

    @FXML
    private TextArea resultTextArea;

    @FXML
    public void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(loadFileButton.getScene().getWindow());
        if (file != null) {
            travelCost.readData(file.getPath());

            // Display the loaded data and fill the combo boxes
            travelCost.cities.forEach((name, city) -> {
                startCityCombo.getItems().add(name);
                endCityCombo.getItems().add(name);
                fileDataTextArea.appendText("City: " + name + "\n");
                city.neighbours.forEach((neighbour, cost) -> fileDataTextArea.appendText("  - Neighbour: " + neighbour.name + ", Cost: " + cost + "\n"));
            });
        }
    }

    @FXML
    public void calculate(ActionEvent event) {
        String startCity = startCityCombo.getSelectionModel().getSelectedItem();
        String endCity = endCityCombo.getSelectionModel().getSelectedItem();

        // Find the path of minimum cost
        List<String> path = travelCost.findPathOfMinCost(startCity, endCity);
        int cost = travelCost.findMinCost(startCity, endCity);

        // Display the result
        resultTextArea.setText("Minimum cost: " + cost + "\nPath: " + String.join(" -> ", path));
    }
}