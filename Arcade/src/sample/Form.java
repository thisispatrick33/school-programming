package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {

    /* Variables for the parts of the Shape */
    Rectangle blockA;
    Rectangle blockB;
    Rectangle blockC;
    Rectangle blockD;

    /* Color variable */
    Color color;

    /* Shape Variables */
    private String name;
    public int form = 1;

    /* Fuction setting the color and name */
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this.blockA = a;
        this.blockB = b;
        this.blockC = c;
        this.blockD = d;
        this.name = name;

        switch (name) {
            case "j":
                color = Color.rgb(7, 199, 237);
                break;
            case "l":
                color = Color.rgb(232, 203, 12);
                break;
            case "o":
                color = Color.rgb(217, 15, 39);
                break;
            case "s":
                color = Color.rgb(41, 181, 5);
                break;
            case "t":
                color = Color.rgb(11, 65, 214);
                break;
            case "z":
                color = Color.rgb(209, 15, 199);
                break;
            case "i":
                color = Color.rgb(230, 108, 9);
                break;

        }
        this.blockA.setFill(color);
        this.blockB.setFill(color);
        this.blockC.setFill(color);
        this.blockD.setFill(color);
    }

    /* Name getter */
    public String getName() {
        return name;
    }

    /* Change form function */
    public void changeForm() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }
}
