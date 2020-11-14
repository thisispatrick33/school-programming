package sample;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;


public class Controller {
    Random rand = new Random();
    int n = rand.nextInt(2);

    @FXML
    Pane plocha;

    @FXML
    Circle hracModry, hracCerveny;

    @FXML
    Slider slider;

    @FXML
    Label you;

    @FXML
    void initialize() {
        if(n==0){
            you.setText("SI MODRY");
            hracModry.setOnMouseDragged(event -> drag(event));
            hracCerveny.setVisible(false);
        }
        else {
            you.setText("SI CERVENY");
            hracCerveny.setOnMouseDragged(event -> drag(event));
            hracModry.setVisible(false);
        }
        hracModry.setTranslateX(200);
        hracModry.setTranslateY(200);
        hracCerveny.setTranslateX(300);
        hracCerveny.setTranslateY(200);

        new AnimationTimer(){
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now){
                if (now - lastUpdate >= 70_000_000*(slider.getValue()+1)) {
                    visible();
                    lastUpdate = now ;
                }

            }
        }.start();

    }

    public void restart(){
        hracModry.setOnMouseDragged(event -> drag(event));
        hracCerveny.setOnMouseDragged(event -> drag(event));
        hracModry.setTranslateX(200);
        hracModry.setTranslateY(200);
        hracCerveny.setTranslateX(300);
        hracCerveny.setTranslateY(200);
    }

    public void visible(){
        if(n==0){
            hracCerveny.setVisible(!hracCerveny.isVisible());
        }
        else {
            hracModry.setVisible(!hracModry.isVisible());
        }

    }


    public void drag(MouseEvent event) {
        Node n = (Node)event.getSource();
        if(plocha.getWidth() - 76 > (n.getTranslateX() + event.getX()) && (n.getTranslateX() + event.getX()) > 0){
            n.setTranslateX(n.getTranslateX() + event.getX());
        }
        if(plocha.getHeight() -76 > (n.getTranslateY() + event.getY()) && (n.getTranslateY() + event.getY()) > 0) {
            n.setTranslateY(n.getTranslateY() + event.getY());
        }

    }


}
