package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Controller {
    Random rn = new Random();
    @FXML
    Button btn;
    @FXML
    AnchorPane pane;

    @FXML
    public void klik(MouseEvent mouseEvent) {
        System.out.println("vedla");
    }

    @FXML
    public void catchMe(MouseEvent mouseEvent) {
        System.out.println("chytil si ma");
    }

    @FXML
    public void move(MouseEvent mouseEvent) {
        btn.relocate(rn.nextDouble()*(pane.getWidth()-btn.getWidth()),rn.nextDouble()*(pane.getHeight()-btn.getHeight()));
    }
}
