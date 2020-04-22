package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class Controller {
    static Stage stage = new Stage();

    public void returnToChooser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void playPong(ActionEvent event) throws IOException {
        stage.close();
        PongGame newPong = new PongGame();
        newPong.playPong(event);
    }

    public void playSnake(ActionEvent event) {
        stage.close();
        SnakeGame newSnake = new SnakeGame();
        newSnake.playSnake(event);
    }

    public void playTetris(ActionEvent event) {
        System.out.println("tetris");
    }

    public void playBrick(ActionEvent event) {
        System.out.println("brick");
    }
}
