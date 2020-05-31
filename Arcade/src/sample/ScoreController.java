package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreController {

    TextField nameInput;

    static Stage stage = new Stage();

    String game = "a";
    int [] score = new int[]{ -1 };


    public void setScore (String name, int [] value) throws IOException {
        game = name;
        score = value;
        gameOver();
    }

    public void gameOver(){
        Pane pane = new Pane();
        nameInput = new TextField();
        nameInput.setLayoutX(200);
        nameInput.setLayoutY(300);
        pane.getChildren().addAll(nameInput);
        Button btn = new Button();
        btn.setText("SUBMIT");
        btn.setLayoutX(200);
        btn.setLayoutY(350);
        btn.setOnMouseClicked((e) -> {
            try {
                returnToMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().addAll(btn);
        if(game.compareTo("Snake") == 0){
            Label Snake = new Label("SNAKE");
            Snake.setTextFill(Color.WHITE);
            Snake.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Snake.setLayoutX(170);
            Snake.setLayoutY(150);
            pane.getChildren().addAll(Snake);
            Label SnakeScore = new Label(Integer.toString(score[0]));
            SnakeScore.setAlignment(Pos.CENTER);
            SnakeScore.setContentDisplay(ContentDisplay.CENTER);
            SnakeScore.setTextFill(Color.WHITE);
            SnakeScore.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[0] < 10)
                SnakeScore.setLayoutX(200);
            else if(score[0] < 100)
                SnakeScore.setLayoutX(190);
            else
                SnakeScore.setLayoutX(185);
            SnakeScore.setLayoutY(180);
            pane.getChildren().addAll(SnakeScore);
        }
        if(game.compareTo("Pong") == 0){
            Label Pong = new Label("PONG");
            Pong.setTextFill(Color.WHITE);
            Pong.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Pong.setLayoutX(175);
            Pong.setLayoutY(150);
            pane.getChildren().addAll(Pong);
            Label PongScore = new Label(score[0]+" : "+score[1]);
            PongScore.setAlignment(Pos.CENTER);
            PongScore.setContentDisplay(ContentDisplay.CENTER);
            PongScore.setTextFill(Color.WHITE);
            PongScore.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[0] < 10 && score[1] < 10)
                PongScore.setLayoutX(185);
            else if((score[0] < 100 && score[1] < 10)||(score[1] < 100 && score[0] < 10))
                PongScore.setLayoutX(180);
            else
                PongScore.setLayoutX(170);
            PongScore.setLayoutY(180);
            pane.getChildren().addAll(PongScore);
        }
        if(game.compareTo("Tetris") == 0){
            Label Tetris = new Label("TETRIS");
            Tetris.setTextFill(Color.WHITE);
            Tetris.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Tetris.setLayoutX(175);
            Tetris.setLayoutY(140);
            pane.getChildren().addAll(Tetris);
            Label TetrisScore = new Label("Points: "+score[0]);
            TetrisScore.setAlignment(Pos.CENTER);
            TetrisScore.setContentDisplay(ContentDisplay.CENTER);
            TetrisScore.setTextFill(Color.WHITE);
            TetrisScore.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[0] < 1000)
                TetrisScore.setLayoutX(155);
            else
                TetrisScore.setLayoutX(147);
            TetrisScore.setLayoutY(160);
            pane.getChildren().addAll(TetrisScore);
            Label TetrisLines = new Label("Lines: "+score[1]);
            TetrisLines.setAlignment(Pos.CENTER);
            TetrisLines.setContentDisplay(ContentDisplay.CENTER);
            TetrisLines.setTextFill(Color.WHITE);
            TetrisLines.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[1] < 10)
                TetrisLines.setLayoutX(173);
            else  if(score[1] < 100)
                TetrisLines.setLayoutX(165);
            else
                TetrisLines.setLayoutX(159);
            TetrisLines.setLayoutY(180);
            pane.getChildren().addAll(TetrisLines);
        }
        if(game.compareTo("Space") == 0){
            Label Space = new Label("SPACE");
            Space.setTextFill(Color.WHITE);
            Space.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Space.setLayoutX(173);
            Space.setLayoutY(150);
            pane.getChildren().addAll(Space);
            Label SpaceScore= new Label(Integer.toString(score[0]));
            SpaceScore.setAlignment(Pos.CENTER);
            SpaceScore.setContentDisplay(ContentDisplay.CENTER);
            SpaceScore.setTextFill(Color.WHITE);
            SpaceScore.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[0] < 10)
                SpaceScore.setLayoutX(200);
            else if(score[0] < 100)
                SpaceScore.setLayoutX(190);
            else
                SpaceScore.setLayoutX(185);
            SpaceScore.setLayoutY(180);
            pane.getChildren().addAll(SpaceScore);
        }
        Scene scene = new Scene(pane, 400, 400);
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMenu() throws IOException {
        String player = nameInput.getText();
        System.out.println(game);
        if(player.length() > 0 && !player.contains(" ")){
            BufferedReader in = new BufferedReader(new FileReader("src/sample/Scores"));
            List <String> scores = new ArrayList<>();
            while (true){
                String line = in.readLine();
                scores.add(line);
                if(line.compareTo("Space") == 0){
                    break;
                }
            }
            boolean foungGame = false;
            boolean savedScore = false;
            BufferedWriter out = new BufferedWriter(new FileWriter("src/sample/Scores"));
            for (String s : scores) {
                if(game.compareTo("Snake") == 0){
                    if(!foungGame && s.compareTo("Snake") == 0 && !savedScore){
                        out.write(player+" "+score[0]);
                        out.newLine();
                        out.write(s);
                        System.out.println(player+" "+score[0]);
                        savedScore = true;
                    }
                    else if (!foungGame && s.contains(" ")){
                        String [] data = s.split(" ");
                        if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                            out.write(player+" "+score[0]);
                            out.newLine();
                            out.write(s);
                            System.out.println(player+" "+score[0]);
                            savedScore = true;
                        }
                        else {
                            out.write(s);
                            System.out.println(s);
                        }
                    }
                    else if(s.compareTo("Snake") == 0){
                        out.write(s);
                        System.out.println(s);
                        foungGame = true;
                    }
                    else {
                        out.write(s);
                        System.out.println(s);
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
            }
            out.close();
            System.out.println("saved");
            Controller con = new Controller();
            stage.close();
            con.returnToChooser();
        }

    }
}
