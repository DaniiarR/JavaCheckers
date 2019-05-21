package sample;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static sample.SideMenu.whiteKilledLabel;
import static sample.SideMenu.redKilledLabel;

public class LoginWindow {

    static Label userName1 = new Label("Player 1: ");
    static TextField nameInput1 = new TextField();
    static ColorPicker colorPicker1 = new ColorPicker();
    static Label userName2 = new Label("Player 2: ");
    static TextField nameInput2 = new TextField();
    static ColorPicker colorPicker2 = new ColorPicker();
    static Button startButton = new Button("Start!");

    public static void display() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Congratulations!");
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(200);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.setVgap(5);
        layout.setHgap(10);

        userName1.setFont(Font.font("Constantia", FontWeight.NORMAL, 20));
        GridPane.setConstraints(userName1, 0, 0);

        nameInput1.setAlignment(Pos.CENTER);
        GridPane.setConstraints(nameInput1, 1, 0);

        colorPicker1.setPrefWidth(150);
        GridPane.setConstraints(colorPicker1, 1, 1);

        userName2.setFont(Font.font("Constantia", FontWeight.NORMAL, 20));
        GridPane.setConstraints(userName2, 0, 2);

        nameInput2.setAlignment(Pos.CENTER);
        GridPane.setConstraints(nameInput2, 1, 2);

        colorPicker2.setPrefWidth(150);
        GridPane.setConstraints(colorPicker2, 1, 3);

        GridPane.setConstraints(startButton, 0, 4);
        GridPane.setColumnSpan(startButton, GridPane.REMAINING);

        startButton.setFont(Font.font("Constantia", FontWeight.NORMAL, 20));
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setValignment(startButton, VPos.CENTER);
        startButton.setOnAction(e -> {
            primaryStage.close();
            if (isString(nameInput1.getText()) && isString(nameInput2.getText())) {
                whiteKilledLabel.setText(String.format("%s killed: 0", nameInput1.getText()));
                redKilledLabel.setText(String.format("%s killed: 0", nameInput2.getText()));
                if (nameInput1.getText().equals("")) {
                    whiteKilledLabel.setText("Player 1 killed: 0");
                }
                if (nameInput2.getText().equals("")) {
                    redKilledLabel.setText("Player 2 killed: 0");
                }
            }
            System.out.println(colorPicker1.getValue());
        Main.primaryStage.show();
        });
        layout.getChildren().addAll(userName1, nameInput1, colorPicker1, userName2, nameInput2, colorPicker2, startButton);

        Scene scene = new Scene(layout);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public static boolean isString(Object username) {
        if (username instanceof String) {
            return true;
        }
        return false;
    }
}
