package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreController {
    static Stage stage = new Stage();

    int score = -1;


    public void setScore (int value) throws IOException {
        score = value;
        System.out.println(value);
        gameOver();
    }

    public void gameOver()throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("over.fxml"));
        Label label = new Label(Integer.toString(score));
        label.setLayoutY(200);
        Pane pane = new Pane();
        pane.getChildren().addAll(root);
        pane.getChildren().addAll(label);
        Scene scene = new Scene(pane, 300, 300);
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void say() throws IOException {
        System.out.println("hehe");
        Controller con = new Controller();
        stage.close();
        con.returnToChooser();

    }
}
