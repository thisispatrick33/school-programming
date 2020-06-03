package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class PongGame {
    
    /* GAME Variables */
    private static final int windowWidth = 800;
    private static final int windowHeight = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double BALL_Radius = 15;
    private double ballYSpeed = 0.5;
    private double ballXSpeed = 0.5;
    private double playerOneYPos = windowHeight / 2;
    private double playerTwoYPos = windowHeight / 2;
    private double ballXPos = windowWidth / 2;
    private double ballYPos = windowHeight / 2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = windowWidth - PLAYER_WIDTH;
    private long startTime = 0;
    Stage stage = new Stage();
    Controller con = new Controller();
    Timeline animation;
    int timeLimit = 120;

    /* Main Function which starts everything */
    public void playPong(ActionEvent event) throws IOException {
        /* Set up main variables */
        stage.setTitle("PONG GAME");
        Canvas canvas = new Canvas(windowWidth, windowHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        /* Create a new animation which runs the game */
         animation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            try {
                run(gc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
        /* Set up the animation to run indefinitelly*/
        animation.setCycleCount(Timeline.INDEFINITE);

        /* Set up the mouse controls for player one, show the stage and run the animation*/
        canvas.setOnMouseMoved(e ->  playerOneYPos  = e.getY() < 50 ? playerOneYPos : (e.getY()-50 > windowHeight -PLAYER_HEIGHT ? playerOneYPos : e.getY()-50));
        canvas.setOnMouseClicked(e ->  gameStarted = true);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        animation.play();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /* This function is called within the animation every 10 milliseconds */
    private void run(GraphicsContext gc) throws IOException {
        /* If the time has run out end the game */
        if(((System.currentTimeMillis() - startTime)/1000 >= timeLimit) && (startTime !=0)){
            stage.close();
            con.goToScore("Pong", new int[]{ scoreP1, scoreP2 });
            animation.stop();
        }
        else {
            /* Set up the background */
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, windowWidth, windowHeight);
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(25));

            /* If the game is running (A click happened)*/
            if(gameStarted) {
                /* If the start has not been set yet, set it to the current system millis time */
                if(startTime == 0){
                    startTime = System.currentTimeMillis();
                }
                /* Move the ball */
                ballXPos+=ballXSpeed;
                ballYPos+=ballYSpeed;
                /* Opponent movement based on the distance from the ball */
                if(ballXPos < windowWidth - windowWidth / 4) {
                    playerTwoYPos  = ballYPos == 0 ? playerTwoYPos : ((ballYPos > windowHeight -50 || ballYPos-50 < 0) ? playerTwoYPos : ballYPos-50);
                }  else {
                    if(ballYPos > 50 && ballYPos < windowHeight -50)
                        playerTwoYPos =  ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ? playerTwoYPos += 2 : playerTwoYPos - 2;
                }
                /* Show the ball */
                gc.fillOval(ballXPos, ballYPos, BALL_Radius, BALL_Radius);

            } else {
                /* Show the click prompt */
                gc.setStroke(Color.WHITE);
                gc.setTextAlign(TextAlignment.CENTER);
                gc.strokeText("Click", windowWidth / 2, windowHeight / 2);

                /* Reset the ball and its speed and direction */
                ballXPos = windowWidth / 2;
                ballYPos = windowHeight / 2;
                ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
                ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
            }

            /* If the ball his the ceiling reverse its Y speed */
            if(ballYPos > windowHeight - BALL_Radius || ballYPos < 0) ballYSpeed *=-1;

            /* Opponent gets a point */
            if(ballXPos < playerOneXPos - PLAYER_WIDTH) {
                scoreP2++;
                gameStarted = false;
            }
            /* You get a point */
            if(ballXPos > playerTwoXPos + PLAYER_WIDTH) {
                scoreP1++;
                gameStarted = false;
            }

            /* The balls speed is increased with every hit */
            if( ((ballXPos + BALL_Radius > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
                    ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
                ballYSpeed += 1 * Math.signum(ballYSpeed);
                ballXSpeed += 1 * Math.signum(ballXSpeed);

                ballXSpeed *= -1;
                ballYSpeed *= new Random().nextInt(2) == 0 ? 1: -1;
            }

            /* Show the score and timer */
            if(startTime!=0){
                long time = timeLimit-((System.currentTimeMillis() - startTime)/1000);
                gc.fillText(scoreP1 + "\t\t"+(time/60)+":"+((time-((int)(time/60)*60)) < 10 ? "0"+(time-((int)(time/60)*60)) : (time-((int)(time/60)*60)) )+"\t\t" + scoreP2, windowWidth / 2, 100);
            }
            /* Show the score */
            gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
            gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        }

    }

}
