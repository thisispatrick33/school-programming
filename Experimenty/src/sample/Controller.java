package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    Label label;
    @FXML
    Circle circle;
    @FXML
    Rectangle rectangle;
    @FXML
    ColorPicker picker1;
    @FXML
    ColorPicker picker2;
    @FXML
    Circle colorCircle;
    @FXML
    Button loginButton;
    @FXML
    TextField meno;
    @FXML
    PasswordField heslo;
    @FXML
    BorderPane borderPane;
    @FXML
    Rectangle gulka;

    boolean strielaj = false;

    public void zmensi(MouseEvent mouseEvent) {
        Font font = label.getFont();
        label.setFont(new Font(font.getSize()-1));
    }

    public void zvacsi(MouseEvent mouseEvent) {
        Font font = label.getFont();
        label.setFont(new Font(font.getSize()+1));
    }

    public void dolava(MouseEvent mouseEvent) {
        circle.setCenterX(circle.getCenterX()-2);
    }

    public void doprava(MouseEvent mouseEvent) {
        circle.setCenterX(circle.getCenterX()+2);
    }

    public void gradient(MouseEvent mouseEvent) {
        Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.YELLOW)};
        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        rectangle.setFill(lg1);
    }

    public void zmenBorder(ActionEvent event) {
        colorCircle.setStroke(picker1.getValue());
    }

    public void zmenVypln(ActionEvent event) {
        colorCircle.setFill(picker2.getValue());
    }

    public void hide(MouseEvent mouseEvent) {
        if(loginButton.getText().equals("Skryt")){
            if(meno.getText().equals("admin")&&heslo.getText().equals("password")){
                System.out.println("sem som");
                meno.setText("");
                heslo.setText("");
                borderPane.setVisible(false);
                loginButton.setText("Odkryt");
            }
        }else if(loginButton.getText().equals("Odkryt")) {
            if(meno.getText().equals("admin")&&heslo.getText().equals("password")){
                System.out.println("sem som");
                meno.setText("");
                heslo.setText("");
                borderPane.setVisible(true);
                loginButton.setText("Skryt");
            }
        }
    }

    public void shoot(MouseEvent mouseEvent){
        gulka.setVisible(true);
        strielaj = true;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new AnimationTimer(){
            @Override
            public void handle(long l){
                if (strielaj){
                    gulka.setLayoutX(gulka.getLayoutX()+10);
                    if(gulka.getLayoutX()>=(1000-gulka.getWidth())){
                        gulka.setVisible(false);
                        gulka.setLayoutX(0);
                        strielaj = false;
                    }
                }

            }
        }.start();
    }
}
