package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SideMenu extends VBox {

    public static int whitePiecesKilled = 0;
    public static int redPiecesKilled = 0;
    static Label whiteKilledLabel = new Label("White pieces killed: 0");
    static Label redKilledLabel = new Label("Red pieces killed: 0");

    public SideMenu() {
        setPadding(new Insets(10, 3, 10, 3));
        setPrefWidth(250);
        Label label = new Label("Daniiar's Checkers");
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 23));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.BASELINE_CENTER);

        getChildren().addAll(label, whiteKilledLabel, redKilledLabel);

    }
}
