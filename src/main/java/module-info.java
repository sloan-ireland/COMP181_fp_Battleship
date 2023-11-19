module controller.comp181_fp_battleship {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller to javafx.fxml;
    exports controller;
    exports view;
    opens view to javafx.fxml;
}