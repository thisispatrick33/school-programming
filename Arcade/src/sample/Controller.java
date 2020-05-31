package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;



public class Controller {
    static Stage stage = new Stage();

    public void returnToChooser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToScore(String name, int [] value) throws IOException {
        ScoreController x = new ScoreController();
        x.setScore(name, value);
    }

    public void playPong(ActionEvent event) throws IOException {
        stage.close();
        PongGame newPong = new PongGame();
        newPong.playPong(event);
    }

    public void playSnake() {
        stage.close();
        SnakeGame newSnake = new SnakeGame();
        newSnake.playSnake();
    }

    public void playTetris() {
        stage.close();
        TetrisGame newTetris = new TetrisGame();
        newTetris.playTetris();
    }


    public void playSpace() {
        stage.close();
        SpaceGame newSpace = new SpaceGame();
        newSpace.playSpace();
    }
}
