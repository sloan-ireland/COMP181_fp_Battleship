package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartController {
    @FXML
    static Label welcomeText = new Label("hi");

    @FXML
    static void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public StartController() {

    }

    void onHelloButtonClique()
    {
        System.out.println("Ha ha");
    }
}