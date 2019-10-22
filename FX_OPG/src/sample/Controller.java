package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {
    int suma = 0;

    @FXML
    private Label label;

    @FXML
    private Button btn;

    @FXML
    private TextArea tf;

    @FXML
    void vypocitaj(ActionEvent event){
        suma = Integer.parseInt(tf.getText());
        label.setText(Integer.toString(suma*100));
    }
}
