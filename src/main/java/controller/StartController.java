package controller;


import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.*;


public class StartController {

    Label welcomeText;
    Button startButton;
    ImageView imageView;

    public StartController() {
        // Image setup
        Image image = new Image("https://th.bing.com/th/id/OIP.nVy89J5iKR8lQs3AO-6rLQHaHZ?w=177&h=180&c=7&r=0&o=5&dpr=2.2&pid=1.7"); // Replace with the correct path
        imageView = new ImageView(image);
        imageView.setFitHeight(180); // Adjust size as needed
        imageView.setFitWidth(177);

        // Welcome text
        welcomeText = new Label("Welcome to Battleship!");
        welcomeText.setStyle("-fx-font-size: 24; -fx-text-fill: white;");

        // Start button
        startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 18; -fx-background-color: #9a8c98; -fx-text-fill: #f2e9e4;");
        startButton.setOnAction(e -> onHelloButtonClick());
    }

    void onHelloButtonClick() {
        welcomeText.setText("Game Starting...");
        GameWindow.displaySetupWindow();
    }

    public Label getWelcomeText() {
        return welcomeText;
    }

    public Button getStartButton() {
        return startButton;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
