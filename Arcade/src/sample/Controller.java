package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    /* Main stage variable for Arcade*/
    static Stage stage = new Stage();

    /* This function is called when we need to return to the menu. */
    public void returnToChooser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* This function is called when a game ends */
    public void goToScore(String name, int [] value) throws IOException {
        ScoreController x = new ScoreController();
        x.setScore(name, value);
    }

    /* This function starts the Pong Game */
    public void playPong(ActionEvent event) throws IOException {
        stage.close();
        PongGame newPong = new PongGame();
        newPong.playPong(event);
    }

    /* This function starts the Snake Game */
    public void playSnake() {
        stage.close();
        SnakeGame newSnake = new SnakeGame();
        newSnake.playSnake();
    }

    /* This function starts the Tetris Game */
    public void playTetris() {
        stage.close();
        TetrisGame newTetris = new TetrisGame();
        newTetris.playTetris();
    }

    /* This function starts the Space Invaders Game */
    public void playSpace() {
        stage.close();
        SpaceGame newSpace = new SpaceGame();
        newSpace.playSpace();
    }

    /* This function is called when we need to go the scoreboard */
    public void showBoard() throws Exception {
        ScoreBoardController scoreCon = new ScoreBoardController();
        scoreCon.showBoard();
    }
}
