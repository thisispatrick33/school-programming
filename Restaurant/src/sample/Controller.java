package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Controller {
    @FXML
    AnchorPane pane;

    public void createTables(int count){
        for (int i=0; i < count; i++){
            Rectangle rec = new Rectangle(50,50);
            rec.setX(i*60);
            rec.setY(i*60);
            pane.getChildren().addAll(rec);
        }
    }

    @FXML
    public void initialize() {
        createTables(12);
    }
}
