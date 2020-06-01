package sample;

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
    static Stage stage = new Stage();
    Label section;
    ScrollPane sp;
    List<String> scores;

    int slide = 0;
    String [] sections = new String[]{"SNAKE", "PONG with Opponent", "PONG", "TETRIS SCORE", "TETRIS LINES", "SPACE"};

    public void showBoard () throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/sample/Scores"));
        scores = new ArrayList<>();

        while (true){
            String line = in.readLine();
            scores.add(line);
            if(line.compareTo("Space") == 0){
                break;
            }
        }
        for(int i = 0; i < scores.size(); i++){
            Label tmp = new Label(scores.get(i));
            tmp.setLayoutY(i*15);
        }
        Pane pane = new Pane();
        ImageView image = new ImageView("file:src/Image/backTile.png");
        Label arrowLeft = new Label("<");
        arrowLeft.setFont(Font.font ("Regular", FontWeight.BOLD,50));
        arrowLeft.setTextFill(Color.web("#000000"));
        arrowLeft.setOnMouseClicked((e) -> changeSlide(-1));
        arrowLeft.setLayoutX(10);
        section = new Label("SNAKE");
        section.setFont(Font.font ("Regular", FontWeight.BOLD,50));
        section.setTextFill(Color.web("#000000"));
        section.setLayoutX(100);
        Label arrowRight = new Label(">");
        arrowRight.setFont(Font.font ("Regular", FontWeight.BOLD,50));
        arrowRight.setTextFill(Color.web("#000000"));
        arrowRight.setOnMouseClicked((e) -> changeSlide(1));
        arrowRight.setLayoutX(300);

        pane.getChildren().addAll(image);
        pane.getChildren().addAll(arrowLeft);
        pane.getChildren().addAll(section);
        pane.getChildren().addAll(arrowRight);
        sp = new ScrollPane();
        sp.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        sp.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setLayoutY(200);
        sp.setPrefSize(115, 150);
        changeSlide(0);
        pane.getChildren().addAll(sp);


        Scene scene = new Scene(pane, 800, 800);
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void changeSlide(int way){
        if(slide == 0 && way == -1){
            slide = sections.length-1;
        }
        else if(slide == sections.length-1 && way == 1){
            slide = 0;
        }
        else {
            slide = slide + way;
        }
        section.setText(sections[slide]);
        String currentGame = "";
        Pane lines = new Pane();
        int i = 0;
        for (String s : scores) {
            if(!s.contains(" ")){
                currentGame = s;
            }
            else {
                if((currentGame.compareTo("")==0 && slide==0) || (currentGame.compareTo("Snake")==0 && slide==1) || (currentGame.compareTo("PongOP")==0 && slide==2) || (currentGame.compareTo("Pong")==0 && slide==3) || (currentGame.compareTo("TetrisL")==0 && slide==4) || (currentGame.compareTo("Tetris")==0 && slide==5)){
                    Label label = new Label(s);
                    label.setLayoutY(i*10);
                    lines.getChildren().addAll(label);
                    i++;
                }
            }

        }
        sp.setContent(lines);
    }
}
