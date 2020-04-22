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
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class PongGame {
    /* PONG VARIABLES */

    private static final int widthPong = 800;
    private static final int heightPong = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double BALL_R = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = heightPong / 2;
    private double playerTwoYPos = heightPong / 2;
    private double ballXPos = widthPong / 2;
    private double ballYPos = heightPong / 2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = widthPong - PLAYER_WIDTH;



    public void playPong(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("P O N G");
        //background size
        Canvas canvas = new Canvas(widthPong, heightPong);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //JavaFX Timeline = free form animation defined by KeyFrames and their duration
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse control (move and click)
        canvas.setOnMouseMoved(e ->  playerOneYPos  = e.getY() == 0 ? playerOneYPos : (e.getY() > heightPong -PLAYER_HEIGHT ? playerOneYPos : e.getY()));
        canvas.setOnMouseClicked(e ->  gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void run(GraphicsContext gc) {
        //set graphics
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, widthPong, heightPong);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(gameStarted) {
            //set ball movement
            ballXPos+=ballXSpeed;
            ballYPos+=ballYSpeed;

            //simple computer opponent who is following the ball
            if(ballXPos < widthPong - widthPong / 4) {
                playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
            }  else {
                playerTwoYPos =  ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ? playerTwoYPos += 2: playerTwoYPos - 2;
            }
            //draw the ball
            gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);

        } else {
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click", widthPong / 2, heightPong / 2);

            //reset the ball start position
            ballXPos = widthPong / 2;
            ballYPos = heightPong / 2;

            //reset the ball speed and the direction

            ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
        }

        //makes sure the ball stays in the canvas
        if(ballYPos > heightPong -BALL_R || ballYPos < 0) ballYSpeed *=-1;

        //if you miss the ball, computer gets a point
        if(ballXPos < playerOneXPos - PLAYER_WIDTH) {
            scoreP2++;
            gameStarted = false;
        }

        //if the computer misses the ball, you get a point
        if(ballXPos > playerTwoXPos + PLAYER_WIDTH) {
            scoreP1++;
            gameStarted = false;
        }

        //increase the speed after the ball hits the player
        if( ((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
                ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);

            ballXSpeed *= -1;
            ballYSpeed *= new Random().nextInt(2) == 0 ? 1: -1;
        }

        //draw score
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t" + scoreP2, widthPong / 2, 100);
        //draw player 1 & 2
        gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

}
