package controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen extends Application {


    @Override
    public void start(Stage stage) {
        StartController controller = new StartController();

        VBox root = new VBox(10); // Spacing between elements
        root.setAlignment(Pos.CENTER); // Center alignment
        root.setStyle("-fx-background-color: #4a4e69;");
        r oot.getChildren().addAll(controller.getImageView(), controller.getWelcomeText(), controller.getStartButton());

        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Battleship Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
