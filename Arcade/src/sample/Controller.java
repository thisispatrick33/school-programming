package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;



public class Controller {
    static Stage stage = new Stage();


    public void returnToChooser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
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
