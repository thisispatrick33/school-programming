package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class Controller {
    Random rn = new Random();
    @FXML
    Button btn;

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
        btn.relocate(rn.nextInt(400),rn.nextInt(400));
    }
}
