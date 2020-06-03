package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreController {
    /* Encryption function */
    public static String encrypt(String strToEncrypt)
    {
        String halfSplit = strToEncrypt.substring(strToEncrypt.length()/2)+strToEncrypt.substring(0, strToEncrypt.length()/2);
        String shift = "";
        for(int i = 0; i < halfSplit.length(); i++){
            shift = (char)((int)(halfSplit.charAt(i))+5) + shift;
        }
        return shift;
    }
    /* Decryption function */
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

    /* Name input field variable */
    TextField nameInput;
    /* Main Stage */
    static Stage stage = new Stage();
    /* Variables which store the users data */
    String game = "a";
    int [] score = new int[]{ -1 };

    /* Main function which recieves the data and shows to context menu */
    public void setScore (String name, int [] value) {
        game = name;
        score = value;
        gameOver();
    }

    /* This function shows the users score and the input for his name */
    public void gameOver(){
        /* Set up the main Pane, background image, GAME OVER, SUBMIT button and name input */
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
        /* Link the function to the onMouseClicked event */
        btn.setOnMouseClicked((e) -> {
            try {
                returnToMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().addAll(btn);
        /* Decide which game was played and add the specific values */
        if(game.compareTo("Snake") == 0){
            /* Set the game Label */
            Label Snake = new Label("SNAKE");
            Snake.setTextFill(Color.WHITE);
            Snake.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Snake.setPrefWidth(120);
            Snake.setLayoutX(170);
            Snake.setLayoutY(150);
            pane.getChildren().addAll(Snake);
            /* Set the score Label/s with the right placement */
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
            /* Set the game Label */
            Label Pong = new Label("PONG");
            Pong.setTextFill(Color.WHITE);
            Pong.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Pong.setLayoutX(175);
            Pong.setLayoutY(150);
            pane.getChildren().addAll(Pong);
            /* Set the score Label/s with the right placement */
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
            /* Set the game Label */
            Label Tetris = new Label("TETRIS");
            Tetris.setTextFill(Color.WHITE);
            Tetris.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Tetris.setLayoutX(175);
            Tetris.setLayoutY(140);
            pane.getChildren().addAll(Tetris);
            /* Set the score Label/s with the right placement */
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
            /* Set the score Label/s with the right placement */
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
            /* Set the game Label */
            Label Space = new Label("SPACE");
            Space.setTextFill(Color.WHITE);
            Space.setFont(Font.font ("Regular", FontWeight.BOLD,20));
            Space.setLayoutX(173);
            Space.setLayoutY(150);
            pane.getChildren().addAll(Space);
            /* Set the score Label/s with the right placement */
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
        /* Show the stage */
        Scene scene = new Scene(pane, 400, 400);
        stage.setTitle("Arcade Classics");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /* This function returns you to the menu and saves the score */
    public void returnToMenu() throws IOException {
        String player = nameInput.getText();
        /* Determine whether the name of the player is 0 < x < 15 and doesnt contain spaces */
        if(player.length() > 0 && player.length() < 15 && !player.contains(" ")){
            /* Reads all the current score entries in the .txt file and saves them to a local variable */
            BufferedReader in = new BufferedReader(new FileReader("src/sample/Scores"));
            List <String> scores = new ArrayList<>();
            while (true){
                String line = decrypt(in.readLine());
                scores.add(line);
                if(line.compareTo("Space") == 0){
                    break;
                }
            }

            /* Control variables for determining the right place where to save the score based on the value and game played */
            boolean foungGame = false;
            String latestGame = "";
            boolean savedScore = false;
            BufferedWriter out = new BufferedWriter(new FileWriter("src/sample/Scores"));
            /* Go through every value */
            for (String s : scores) {
                /* Determine which game was played */
                if(game.compareTo("Snake") == 0){
                    /* If the current line equals the game and the score was not yet saved, write it in last place */
                    if(!foungGame && s.compareTo("Snake") == 0 && !savedScore){
                        out.write(encrypt(player+" "+score[0]));
                        out.newLine();
                        out.write(encrypt(s));
                        savedScore = true;
                    }
                    /* If the wasnt found yet and the current line contains a space check whether it is the right place to save it */
                    else if (!foungGame && s.contains(" ")){
                        String [] data = s.split(" ");
                        if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                            out.write(encrypt(player+" "+score[0]));
                            out.newLine();
                            out.write(encrypt(s));
                            savedScore = true;
                        }
                        else {
                            out.write(encrypt(s));
                        }
                    }
                    /* Else, just write the line */
                    else{
                        out.write(encrypt(s));
                        if(s.compareTo("Snake") == 0){
                            foungGame = true;
                        }
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
                if(game.compareTo("Pong") == 0){
                    /* If the current line equals the game and the score was not yet saved, write it in last place */
                    if((s.compareTo("Pong") == 0 && !savedScore && latestGame.compareTo("PongOP") == 0) || (s.compareTo("PongOP") == 0 && !savedScore && latestGame.compareTo("Snake") == 0)){
                        if(latestGame.compareTo("PongOP") == 0) {
                            out.write(encrypt(player+" "+score[0]));
                        }
                        if(latestGame.compareTo("Snake") == 0){
                            out.write(encrypt(player + " " + ((score[0]*2) - ((score[1]/2)+1))));
                        }
                        out.newLine();
                        out.write(encrypt(s));
                        latestGame = s;
                    }
                    /* If the wasnt found yet and the current line contains a space check whether it is the right place to save it */
                    else if ((s.contains(" ") && latestGame.compareTo("PongOP") == 0) || (s.contains(" ") && latestGame.compareTo("Snake") == 0)){
                        String [] data = s.split(" ");
                        if(latestGame.compareTo("PongOP") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                                out.write(encrypt(player+" "+score[0]));
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        if(latestGame.compareTo("Snake") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < ((score[0]*2) - ((score[1]/2)+1))){
                                out.write(encrypt(player + " " + ((score[0]*2) - ((score[1]/2)+1))));
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        out.write(encrypt(s));
                    }
                    /* Else, just write the line */
                    else{
                        latestGame = s;
                        savedScore = false;
                        out.write(encrypt(s));
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
                if(game.compareTo("Space") == 0){
                    /* If the current line equals the game and the score was not yet saved, write it in last place */
                    if(!foungGame && s.compareTo("Space") == 0 && !savedScore){
                        out.write(encrypt(player+" "+score[0]));
                        out.newLine();
                        out.write(encrypt(s));
                        savedScore = true;
                    }
                    /* If the wasnt found yet and the current line contains a space check whether it is the right place to save it */
                    else if (!foungGame && s.contains(" ") && latestGame.compareTo("Tetris") == 0){
                        String [] data = s.split(" ");
                        if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                            out.write(encrypt(player+" "+score[0]));
                            out.newLine();
                            out.write(encrypt(s));
                            savedScore = true;
                        }
                        else {
                            out.write(encrypt(s));
                        }
                    }
                    /* Else, just write the line */
                    else{
                        out.write(encrypt(s));
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
                    /* If the current line equals the game and the score was not yet saved, write it in last place */
                    if((s.compareTo("Tetris") == 0 && !savedScore && latestGame.compareTo("TetrisL") == 0) || (s.compareTo("TetrisL") == 0 && !savedScore && latestGame.compareTo("Pong") == 0)){
                        if(latestGame.compareTo("TetrisL") == 0) {
                            out.write(encrypt(player+" "+score[0]));
                        }
                        if(latestGame.compareTo("Pong") == 0){
                            out.write(encrypt(player+" "+score[1]));
                        }
                        out.newLine();
                        out.write(encrypt(s));
                        latestGame = s;
                    }
                    /* If the wasnt found yet and the current line contains a space check whether it is the right place to save it */
                    else if ((s.contains(" ") && latestGame.compareTo("TetrisL") == 0) || (s.contains(" ") && latestGame.compareTo("Pong") == 0)){
                        String [] data = s.split(" ");
                        if(latestGame.compareTo("TetrisL") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[0]){
                                out.write(encrypt(player+" "+score[0]));
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        if(latestGame.compareTo("Pong") == 0){
                            if(!savedScore && Integer.parseInt(data[1]) < score[1]){
                                out.write(encrypt(player+" "+score[1]));
                                out.newLine();
                                savedScore = true;
                            }
                        }
                        out.write(encrypt(s));
                    }
                    /* Else, just write the line */
                    else{
                        latestGame = s;
                        savedScore = false;
                        out.write(encrypt(s));
                    }
                    if(s.compareTo("Space") != 0){
                        out.newLine();
                    }
                }
            }
            /* Close the stage and return to the main menu */
            out.close();
            Controller con = new Controller();
            stage.close();
            con.returnToChooser();
        }

    }
}
