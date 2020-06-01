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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
        ImageView image = new ImageView("file:src/Image/backTile.png");
        pane.getChildren().addAll(image);
        nameInput = new TextField();
        nameInput.setPrefWidth(200);
        nameInput.setLayoutX(100);
        nameInput.setLayoutY(250);
        pane.getChildren().addAll(nameInput);
        Label over = new Label("GAME OVER");
        over.setFont(Font.font ("Regular", FontWeight.BOLD,50));
        over.setTextFill(Color.web("#d60626"));
        over.setLayoutX(60);
        over.setLayoutY(20);
        pane.getChildren().addAll(over);
        Button btn = new Button();
        btn.setPrefWidth(120);
        btn.setPrefHeight(30);
        btn.setText("SUBMIT");
        btn.setLayoutX(140);
        btn.setLayoutY(300);
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
            Snake.setPrefWidth(120);
            Snake.setLayoutX(170);
            Snake.setLayoutY(150);
            pane.getChildren().addAll(Snake);
            Label SnakeScore = new Label(Integer.toString(score[0]));
            SnakeScore.setAlignment(Pos.CENTER);
            SnakeScore.setContentDisplay(ContentDisplay.CENTER);
            SnakeScore.setTextFill(Color.WHITE);
            SnakeScore.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            if(score[0] < 10)
                SnakeScore.setLayoutX(198);
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
            TetrisScore.setLayoutY(170);
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
            TetrisLines.setLayoutY(190);
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
            String latestGame = "";
            boolean savedScore = false;
            BufferedWriter out = new BufferedWriter(new FileWriter("src/sample/Scores"));
            for (String s : scores) {
                if(game.compareTo("Snake") == 0){
                    if(!foungGame && s.compareTo("Snake") == 0 && !savedScore){
                        out.write(player+" "+score[0]);
                        out.newLine();
                        out.write(s);
                        savedScore = true;
                    }
                    else if (!foungGame && s.contains(" ")){
                        String [] data = s.split(" ");
                        if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                            out.write(player+" "+score[0]);
                            out.newLine();
                            out.write(s);
                            savedScore = true;
                        }
                        else {
                            out.write(s);
                        }
                    }
                    else{
                        out.write(s);
                        if(s.compareTo("Snake") == 0){
                            foungGame = true;
                        }
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
                if(game.compareTo("Pong") == 0){

                    if((s.compareTo("Pong") == 0 && !savedScore && latestGame.compareTo("PongOP") == 0) || (s.compareTo("PongOP") == 0 && !savedScore && latestGame.compareTo("Snake") == 0)){
                        if(latestGame.compareTo("PongOP") == 0) {
                            out.write(player + " " + score[0]);
                        }
                        if(latestGame.compareTo("Snake") == 0){
                            out.write(player + " " + ((score[0]*2) / ((score[1]/2)+1)));
                        }
                        out.newLine();
                        out.write(s);
                        latestGame = s;
                    }
                    else if ((s.contains(" ") && latestGame.compareTo("PongOP") == 0) || (s.contains(" ") && latestGame.compareTo("Snake") == 0)){
                        String [] data = s.split(" ");
                        if(latestGame.compareTo("PongOP") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                                out.write(player+" "+score[0]);
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        if(latestGame.compareTo("Snake") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < ((score[0]*2) / ((score[1]/2)+1))){
                                out.write(player + " " + ((score[0]*2) / ((score[1]/2)+1)));
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        out.write(s);
                    }
                    else{
                        latestGame = s;
                        savedScore = false;
                        out.write(s);
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
                if(game.compareTo("Space") == 0){
                    if(!foungGame && s.compareTo("Space") == 0 && !savedScore){
                        out.write(player+" "+score[0]);
                        out.newLine();
                        out.write(s);
                        savedScore = true;
                    }
                    else if (!foungGame && s.contains(" ") && latestGame.compareTo("Tetris") == 0){
                        String [] data = s.split(" ");
                        if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                            out.write(player+" "+score[0]);
                            out.newLine();
                            out.write(s);
                            savedScore = true;
                        }
                        else {
                            out.write(s);
                        }
                    }
                    else{
                        out.write(s);
                        latestGame = s;
                        if(s.compareTo("Space") == 0){
                            foungGame = true;
                        }
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
                if(game.compareTo("Tetris") == 0){

                    if((s.compareTo("Tetris") == 0 && !savedScore && latestGame.compareTo("TetrisL") == 0) || (s.compareTo("TetrisL") == 0 && !savedScore && latestGame.compareTo("Pong") == 0)){
                        if(latestGame.compareTo("TetrisL") == 0) {
                            out.write(player + " " + score[0]);
                        }
                        if(latestGame.compareTo("Pong") == 0){
                            out.write(player + " " + score[1]);
                        }
                        out.newLine();
                        out.write(s);
                        latestGame = s;
                    }
                    else if ((s.contains(" ") && latestGame.compareTo("TetrisL") == 0) || (s.contains(" ") && latestGame.compareTo("Pong") == 0)){
                        String [] data = s.split(" ");
                        if(latestGame.compareTo("TetrisL") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                                out.write(player+" "+score[0]);
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        if(latestGame.compareTo("Pong") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[1]){
                                out.write(player+" "+score[1]);
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        out.write(s);
                    }
                    else{
                        latestGame = s;
                        savedScore = false;
                        out.write(s);
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
            }
            out.close();
            Controller con = new Controller();
            stage.close();
            con.returnToChooser();
        }

    }
}
