package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SideMenu extends VBox {

    public static int whitePiecesKilled = 0;
    public static int redPiecesKilled = 0;
    static Label whiteKilledLabel = new Label("White pieces killed: 0");
    static Label redKilledLabel = new Label("Red pieces killed: 0");
    public VBox score = new VBox(5);

    public SideMenu() {
        setPadding(new Insets(10, 3, 10, 3));
        setPrefWidth(250);
        Label label = new Label("Daniiar's Checkers");
        label.setFont(Font.font("Constantia", FontWeight.BOLD, 27));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(Double.MAX_VALUE, 105);
        label.setTextFill(Color.BLACK);

        score.setPrefSize(Double.MAX_VALUE, 125);
        score.setAlignment(Pos.CENTER_LEFT);
        whiteKilledLabel.setFont(Font.font("Century", 18));
        redKilledLabel.setFont(Font.font("Century", 18));
        score.getChildren().addAll(whiteKilledLabel, redKilledLabel);

        Button button = new Button("Congrats window");
        CongratulationWindow cong = new CongratulationWindow();
        button.setOnAction(e -> cong.display("Red"));
        getChildren().addAll(label, score, button);
    }
}
