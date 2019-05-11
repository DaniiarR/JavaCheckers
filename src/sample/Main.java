package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {

    public static final int TILE_SIZE = 70;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createBoard());
        primaryStage.setTitle("Daniiar's Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createBoard() {
        //Left side of the main window that contains the board
        Pane leftPanel = new Pane();
        leftPanel.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        leftPanel.getChildren().addAll(tileGroup, pieceGroup);
        //Right side the contains additional information about the game
        VBox rightPanel = new VBox();
        rightPanel.setPrefWidth(200);
        Label label = new Label("Daniiar's Checkers");
        rightPanel.getChildren().add(label);
        //Layout of the main window
        HBox layout = new HBox(10, leftPanel, rightPanel);
        //Creating the tiles and pieces
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;
                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.BLACK, x, y);
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }
                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return layout;
    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);
        return piece;
    }
}
