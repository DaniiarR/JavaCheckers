package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import static sample.SideMenu.redPiecesKilled;
import static sample.SideMenu.whitePiecesKilled;


public class Main extends Application {

    static Stage primaryStage;

    public static final int TILE_SIZE = 70;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private int whiteColumn, whiteRow = 0;
    private int redColumn, redRow = 0;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        LoginWindow.display();
        //Left side of the main window that contains the board
        Pane leftPanel = new Pane();
        leftPanel.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        leftPanel.getChildren().addAll(tileGroup, pieceGroup);
        //Right side that contains additional information about the game
        SideMenu menu = new SideMenu();
        //Layout of the main window
        HBox layout = new HBox(0, leftPanel, menu);
        BackgroundImage myBI= new BackgroundImage(new Image("sample/alatoodanr.png",1200,1100,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        leftPanel.setBackground(new Background(myBI));

        //Creating the tiles and pieces
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;
                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);

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
        Scene scene = new Scene(layout);

        primaryStage.setTitle("Daniiar's Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }


    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);
        piece.setMouseTransparent(false);
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());
            MoveResult result;
            //if the new position of a piece is not a legal place, return NONE type
            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                //in legal cases try to make a move
                result = tryMove(piece, newX, newY);
            }
            //getting the old position in board coordinates
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            //make a decision according to the type of move a player wants to make
            switch (result.getType()) {
                //we simply abort the move if NONE
                case NONE:
                    piece.abortMove();
                    break;
                //if NORMAL, delete the piece from the old position and place it to the new position
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                //if KILL, we move the piece to the new position and delete it from the initial position
                //then we increment the counter for killed pieces
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    if (whiteColumn > 5) {
                        whiteRow = 1;
                        whiteColumn = 0;
                    }
                    if (redColumn > 5) {
                        redRow = 1;
                        redColumn = 0;
                    }
                    if (otherPiece.getType().equals(PieceType.WHITE)) {
                        if (LoginWindow.nameInput1.getText().equals("")) {
                            SideMenu.whiteKilledLabel.setText("Player 1 killed: " + (++whitePiecesKilled));
                        } else {
                            SideMenu.whiteKilledLabel.setText(String.format("%s killed: %d",
                                    LoginWindow.nameInput1.getText(), ++whitePiecesKilled));
                        }
                        SideMenu.whitePiecesArray[whiteRow][whiteColumn].setVisible(true);
                        whiteColumn++;
                        System.out.println(whiteColumn);

                    } else {
                        if (LoginWindow.nameInput2.getText().equals("")) {
                            SideMenu.redKilledLabel.setText("Player 2 killed: " + (++redPiecesKilled));
                        } else {
                            SideMenu.redKilledLabel.setText(String.format("%s killed: %d",
                                    LoginWindow.nameInput2.getText(), ++redPiecesKilled));
                        }
                        SideMenu.redPiecesArray[redRow][redColumn].setVisible(true);
                        redColumn++;
                        System.out.println(redColumn);
                    }
                    pieceGroup.getChildren().remove(otherPiece);

                    CongratulationWindow congratsWindow = new CongratulationWindow();
                    if (whitePiecesKilled == 12) {
                        congratsWindow.display(LoginWindow.nameInput1.getText());
                    } else if (redPiecesKilled == 12) {
                        congratsWindow.display(LoginWindow.nameInput2.getText());
                    }
                    break;
            }
        });
        return piece;
    }

    //returns the type of move that the user wants to make
    private MoveResult tryMove(Piece piece, int newX, int newY) {
        //if the tile on which user wants to move his piece is not empty
        //or if it is a white tile, return NONE MoveType
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }
        //getting piece's old position in board coordinates
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        // Normal move
        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveResult(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 &&
                ((newY - y0 == piece.getType().moveDir * 2) || (newY - y0 == piece.getType().moveDir * (-2)))) {
            //Kill move
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;
            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }
}
