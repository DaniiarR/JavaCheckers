package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SideMenu extends VBox {

    public static int whitePiecesKilled = 0;
    public static int redPiecesKilled = 0;
    static Label whiteKilledLabel = new Label();
    static Label redKilledLabel = new Label();
    private VBox score = new VBox(5);
    public static Piece[][] whitePiecesArray = new Piece[2][6];
    public static Piece[][] redPiecesArray = new Piece[2][6];

    public SideMenu() {
        setPadding(new Insets(10, 3, 0, 5));
        setPrefWidth(340);

        score.setPrefSize(Double.MAX_VALUE, 125);
        score.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label("Daniiar's Checkers");
        label.setFont(Font.font("Constantia", FontWeight.BOLD, 27));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(Double.MAX_VALUE, 105);
        label.setTextFill(Color.BLACK);

        whiteKilledLabel.setFont(Font.font("Century", 18));

        GridPane whitePiecesLayout = new GridPane();
        whitePiecesLayout.setVgap(15);
        whitePiecesLayout.setHgap(5);
        whitePiecesLayout.setPadding(new Insets(0, 0, 20, 0));
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 6; column++) {
                Piece piece = new Piece(PieceType.WHITE, 0, 0);
                whitePiecesArray[row][column] = piece;
                GridPane.setConstraints(piece, column, row);
                whitePiecesLayout.getChildren().add(piece);
                piece.setVisible(false);
            }
        }

        redKilledLabel.setFont(Font.font("Century", 18));

        GridPane redPiecesLayout = new GridPane();
        redPiecesLayout.setVgap(15);
        redPiecesLayout.setHgap(5);
        redPiecesLayout.setPadding(new Insets(0, 0, 35, 0));
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 6; column++) {
                Piece piece = new Piece(PieceType.RED, 0, 0);
                redPiecesArray[row][column] = piece;
                GridPane.setConstraints(piece, column, row);
                redPiecesLayout.getChildren().add(piece);
                piece.setVisible(false);
            }
        }

        score.getChildren().addAll(whiteKilledLabel, whitePiecesLayout, redKilledLabel, redPiecesLayout);

        //Button button = new Button("Congrats window");
        //CongratulationWindow cong = new CongratulationWindow();
        //button.setOnAction(e -> cong.display("Red"));
        Button restartButton = new Button("Restart!");
        restartButton.setOnAction(e -> {
            Main.primaryStage.close();
            SideMenu.whitePiecesKilled = 0;
            SideMenu.redPiecesKilled = 0;
            SideMenu.whiteKilledLabel.setText("Player 1 killed: 0");
            SideMenu.redKilledLabel.setText("Player 2 killed: 0");
            Platform.runLater(() -> new Main().start(new Stage()));
        });
        restartButton.setFont(Font.font("Constantia", FontWeight.BOLD, 27));
        restartButton.setAlignment(Pos.CENTER);
        getChildren().addAll(label, score, restartButton);
    }
}
