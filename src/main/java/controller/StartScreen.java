package controller;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application
{
    static Text anotherText = new Text();

    @Override
    public void start(Stage stage) throws IOException {
        /*
        default code from IntelliJ
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        //SHOW STAGE AND SCENE
        stage.setScene(scene);
        stage.show();
        */

        //MANUAL WINDOW SET UP

        //STEP (1) ADD ROOT NODE OF TYPE GROUP
        Group myRoot = new Group();

        //STEP (2) PASS ROOT NODE AS ARG TO SCENE CONSTRUCTOR
        Scene scene = new Scene(myRoot,  Color.ORANGERED); //

        //STEP (3) SET WINDOW ATTRIBUTES

        // (3A) TITLE TEXT
        stage.setTitle("BattleShip: Adavanced Warefare");

        //(3B) WINDOW DIMENSIONS
        stage.setWidth(720);
        stage.setHeight(480);

        //(3C) RESIZABILITY DEFAULTS TO TRUE
        //stage.setResizable(false);

        //(3D) LOCATION OF FIRST APPEARANCE (ORIGIN 0,0 IS UPPER LEFT CORNER)
        stage.setX(100);// A float between 0 and the screen width works.
        stage.setY(100);

        //--  END STEP(3) WINDOW SET UP ---


        //STEP (4) SET UP CHILD AND LEAF NODES OF "NODE GRAPH"

        //(4a) CREATES A TEXT NODE (not user-editable)
        //immediately visible
        String topicOfDay = "Scenes in JavaFX"; //creates local string variable
        Text myText = new Text(); //creates text object
        myText.setText(topicOfDay); //feeds string data into text object constructor
        myText.setFont(Font.font("Calibri", 40)); //set font and fontsize early for correct alignment
        myText.setTextAlignment(TextAlignment.CENTER);
        myText.setX(stage.getWidth()/2 - myText.getLayoutBounds().getWidth()/2); //positions X-axis. try stage.getWidth()/2 for dyanmic horizonal centering
        myText.setY(100); //positions Y-axis. try stage.getHeight()/2 for dynamic vertical centering
        //Attaches the  text node to root node. Must add node to root node to make it appear
        myRoot.getChildren().add(myText);

        //starts invisible
        anotherText.setFont(Font.font("Calibri", 30)); //set font and fontsize early for correct alignment
        anotherText.setTextAlignment(TextAlignment.CENTER);
        //width is set in the event handler
        anotherText.setY(300); //positions Y-axis. try stage.getHeight()/2 for dynamic vertical centering
        //Attaches the  text node to root node. Must add node to root node to make it appear
        //END STEP (4A) NEW TEXT NODE

        //(4b) CREATES A TEXTFIELD NODE (user editable)
        TextField myTextField = new TextField(); //creates new textfield object with empyt constructor
        myTextField.setMaxWidth(100); //constrains horizonal size.
        myTextField.setMaxHeight(20); //constrains vertical size.
        myTextField.setText("hello"); //feeds string data into textfield object
        myTextField.setLayoutX(stage.getWidth()/2 - myTextField.getMaxWidth()/2); //sets X-axis position. try stage.getWidth()/2 for dynamic horizonal centering
        myTextField.setLayoutY(200); //sets Y-axis position. try stage.getWidth()/2 for dynamic vertical centering
        //Attaches a textfieLd node to root node. Must add node to root node to make it appear
        //myRoot.getChildren().add(myTextField); //<< Can add node to root here for each node, or at end all at once.
        //Captures data from user to local variable
        String str = myTextField.getText();
        //Prints local string value to console
        System.out.println(str);
        //END STEP (4b) NEW TEXT NODE


        //(4c) CREATES A LABEL AND BUTTON NODES
        //Create button
        Button myButton = new Button("PRESS"); // creates a button with a button text
        myButton.setMaxWidth(100);
        myButton.setPrefHeight(20);
        myButton.setLayoutX(stage.getWidth()/2 - myButton.getWidth());
        myButton.setLayoutY(10);
        //Create label for button
        Label myLabel = StartController.welcomeText; // creates an instruction label for the button
        myLabel.setText("Press Button"); // sets text of label for button
        myLabel.setTextAlignment(TextAlignment.CENTER);
        myLabel.setLayoutX(stage.getWidth()/2 - myLabel.getLayoutBounds().getWidth()); // positions label on X-axis
        myLabel.setLayoutY(50); // positions label on Y-axis


        //END STEP (4c) CREATE A BUTTON NODE

        //(4d) EVENT HANDLER DECLARED AND SET FOR A BUTTON
        EventHandler<ActionEvent> myEvent = new EventHandler<ActionEvent>()
        {
            //Method within handler used for event parameter.
            public void handle(ActionEvent e)
            {
                //Any series of statements can comprize handle event method
                //External classes references must be in same package

                System.out.println(myTextField.getText());

                StartScreen.anotherText.setText(myTextField.getText());
                StartScreen.anotherText.setX(stage.getWidth()/2 - anotherText.getLayoutBounds().getWidth()/2); //positions X-axis. try stage.getWidth()/2 for dyanmic horizonal centering


                //Creates new user defined class object
                StartController hc = new StartController();
                //Activates instance method
                hc.onHelloButtonClique();
                //Activates static method
                StartController.onHelloButtonClick();
                //Activates static method for Program sequence
                //Program.run(new String[0]);
            }
        };
        //Assign event as argument to activate if button is clicked.
        myButton.setOnAction(myEvent);
        //END STEP (4d) DECLARE AND SET EVENT HANDLER FOR BUTTON


        //STEP (5) CONNECT ALL CHILD NODES TO ROOT NODE
        //Adding nodes to the root can be done at the end of each step to create the node or here all at once
        myRoot.getChildren().add(myTextField);
        myRoot.getChildren().add(myButton);
        myRoot.getChildren().add(myLabel);
        myRoot.getChildren().add(anotherText);

        //STEP (6) PASS SCENE AS ARG TO SET ARG OF METHOD.
        //Must always be second to last statement of method.
        stage.setScene(scene);

        //STEP (7) DEMONSTRATE STAGE TO THE SCREEN
        //Always last line of window layout.
        stage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
