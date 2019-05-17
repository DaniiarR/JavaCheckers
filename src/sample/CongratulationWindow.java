package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CongratulationWindow {



    public void display(String winner) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Congratulations!");
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(250);

        Label label = new Label();
        label.setText(String.format("%s pieces won!", winner));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMinHeight(70);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Constantia", 34));
        Button restartButton = new Button("Restart");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            primaryStage.close();
            Main.primaryStage.close();
        });
        restartButton.setPrefSize(80, 40);
        exitButton.setPrefSize(80, 40);

        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(20);
        buttonsBox.getChildren().addAll(restartButton, exitButton);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonsBox);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}
