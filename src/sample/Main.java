package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    Button button;
    @Override
    public void start(Stage primaryStage) throws Exception{
        button = new Button("Click me");
        button.setOnAction(e -> {
            boolean answer = ConfirmBox.display("ConfirmBox", "Are you sure you want to send your naked photos to your ex?");
            System.out.println(answer);
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Some title");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
