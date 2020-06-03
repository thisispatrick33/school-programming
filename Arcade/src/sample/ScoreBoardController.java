package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScoreBoardController {

    /* Function which decrypts the scores */
    public static String decrypt(String strToDecrypt)
    {
        String shift = "";
        for(int i = 0; i < strToDecrypt.length(); i++){
            shift = (char)((int)(strToDecrypt.charAt(i))-5) + shift;
        }
        String halfSplit;
        if(strToDecrypt.length() %2 == 0){
            halfSplit = shift.substring(shift.length()/2)+shift.substring(0, shift.length()/2);
        }
        else {
            halfSplit = shift.substring(shift.length()/2+1)+shift.substring(0, shift.length()/2+1);
        }

        return halfSplit;
    }

    /* The Stage variable */
    static Stage stage = new Stage();
    /* Label which cointains the currently viewed score category */
    Label section;
    /* ScrollPane for all the scores */
    ScrollPane sp;
    /* List with all the score values */
    List<String> scores;
    /* int which keeps track of which slide we are on */
    int slide = 0;
    /* All the section names */
    String [] sections = new String[]{"SNAKE", "PONG with Opponent", "PONG", "TETRIS LINES", "TETRIS SCORE", "SPACE"};

    /* The main function which sets everything up */
    public void showBoard () throws IOException {
        /* BufferedReader for reading all values from the .txt */
        BufferedReader in = new BufferedReader(new FileReader("src/sample/Scores"));
        /* Scores variable which stores the values */
        scores = new ArrayList<>();
        /* Loop which goes through all the lines in the .txt */
        while (true){
            String line = decrypt(in.readLine());
            System.out.println(line);
            scores.add(line);
            if(line.compareTo("Space") == 0){
                break;
            }
        }
        /* The main pane which contains all the components */
        Pane pane = new Pane();
        /* The background Image */
        ImageView image = new ImageView("file:src/Image/backTile.png");
        /* The Left Arrow which changes the section */
        Label arrowLeft = new Label("<");
        arrowLeft.setFont(Font.font ("Regular", FontWeight.BOLD,70));
        arrowLeft.setTextFill(Color.web("#FFFFFF"));
        arrowLeft.setOnMouseClicked((e) -> changeSlide(-1));
        arrowLeft.setLayoutX(340);
        arrowLeft.setLayoutY(80);
        /* The Section Label which shows the section name */
        section = new Label("SNAKE");
        section.setFont(Font.font ("Regular", FontWeight.BOLD,50));
        section.setTextFill(Color.web("#FFFFFF"));
        section.setLayoutY(10);
        section.setLayoutX(100);
        /* The Right Arrow which changes the section */
        Label arrowRight = new Label(">");
        arrowRight.setFont(Font.font ("Regular", FontWeight.BOLD,70));
        arrowRight.setTextFill(Color.web("#FFFFFF"));
        arrowRight.setOnMouseClicked((e) -> changeSlide(1));
        arrowRight.setLayoutY(80);
        arrowRight.setLayoutX(420);

        /* Add all of the above to the Pane */
        pane.getChildren().addAll(image);
        pane.getChildren().addAll(arrowLeft);
        pane.getChildren().addAll(section);
        pane.getChildren().addAll(arrowRight);

        /* Create a new ScrollPane, add the settings*/
        sp = new ScrollPane();
        sp.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        sp.setLayoutY(200);
        sp.setLayoutX(200);
        sp.setPrefSize(400, 580);
        changeSlide(0);
        pane.getChildren().addAll(sp);

        /* Show the stage */
        Scene scene = new Scene(pane, 800, 800);
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /* Changes the slide adn values shown*/
    public void changeSlide(int way){
        /* Updates the slide var accordingly */
        if(slide == 0 && way == -1){
            slide = sections.length-1;
        }
        else if(slide == sections.length-1 && way == 1){
            slide = 0;
        }
        else {
            slide = slide + way;
        }

        /* Changes to X property according to the shown value */
        switch(slide) {
            case 1:
                section.setLayoutX(160);
                break;
            case 2:
                section.setLayoutX(335);
                break;
            case 3:
                section.setLayoutX(250);
                break;
            case 4:
                section.setLayoutX(245);
                break;
            case 5:
                section.setLayoutX(330);
                break;
            default:
                section.setLayoutX(325);
        }

        /* Sets the section title */
        section.setText(sections[slide]);

        /* Variables used to filter the score values that will be shown */
        String currentGame = "";
        Pane lines = new Pane();
        int i = 0;

        /* Loop goes through all the values and shows only the right ones */
        for (String s : scores) {
            if(!s.contains(" ")){
                currentGame = s;
            }
            else {
                if((currentGame.compareTo("")==0 && slide==0) || (currentGame.compareTo("Snake")==0 && slide==1) || (currentGame.compareTo("PongOP")==0 && slide==2) || (currentGame.compareTo("Pong")==0 && slide==3) || (currentGame.compareTo("TetrisL")==0 && slide==4) || (currentGame.compareTo("Tetris")==0 && slide==5)){
                    /* If a value is to be shown, then it is added as a Label, with specific styles */
                    Label label = new Label((i+1)+". "+s);
                    label.setFont(Font.font ("Regular", FontWeight.BOLD,40));
                    label.setStyle("-fx-border-color: white; -fx-border-width: 0 0 2px 0; ");
                    label.setPadding(new Insets(5,0,0,20));
                    label.setMinWidth(380);
                    label.setTextFill(Color.web("#FFFFFF"));
                    label.setLayoutY(i*60);
                    lines.getChildren().addAll(label);
                    i++;
                }
            }

        }
        sp.setContent(lines);
    }
}
