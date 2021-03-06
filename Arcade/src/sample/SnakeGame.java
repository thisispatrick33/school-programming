package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

    /* GAME VARIABLES */
    static int speed = 5;
    static int foodcolor = 0;
    static int width = 30;
    static int height = 30;
    static int foodX = 0;
    static int foodY = 0;
    static int cornersize = 25;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();
    static Stage stage = new Stage();
    static Controller con = new Controller();
    static AnimationTimer animate;
    static int score = 0;

    /* The directions */
    public enum Dir {
        left, right, up, down
    }

    /* Corner class that makes up the body of the snake */
    public static class Corner {
        int x;
        int y;
        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /* Main Function which starts the game */
    public void playSnake() {
        try {
            /* Starting food */
            newFood();

            /* Set up the container*/
            VBox root = new VBox();
            Canvas c = new Canvas(width * cornersize, height * cornersize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            /* New Animation */
            animate = new AnimationTimer() {
                /* Variable to determine the time since the last tick */
                long lastTick = 0;

                /* Call the tick function in the right time */
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        try {
                            tick(gc);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        try {
                            tick(gc);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            };

            /* Start the animation and show the scene */
            animate.start();
            Scene scene = new Scene(root, width * cornersize, height * cornersize);

            /* Control the Snake */
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.UP) {
                    direction = Dir.up;
                }
                if (key.getCode() == KeyCode.LEFT) {
                    direction = Dir.left;
                }
                if (key.getCode() == KeyCode.DOWN) {
                    direction = Dir.down;
                }
                if (key.getCode() == KeyCode.RIGHT) {
                    direction = Dir.right;
                }
            });

            /* Initial snake parts and Stage */
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setTitle("SNAKE GAME");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Tick function which handles the Game */
    public static void tick(GraphicsContext gc) throws IOException {
        /* If the game ends, close the stage, reset the variables, go to the score prompt */
        if (gameOver) {
            stage.close();
            animate.stop();
            con.goToScore("Snake", new int[]{ score });
            resetVars();
            return;
        }

        /* Move every snake body part by one */
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        /* Changes the coordinates of the first snake body part based on the direction */
        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;
        }

        /* If the snake colides with food, add a Corner and generate a new one */
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
            score++;
        }

        /* If the snake tries to go back end the game */
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        /* Show the background and score */
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        /* Generate a random food Color */
        Color cc = Color.WHITE;
        switch (foodcolor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
        /* Show the food */
        gc.setFill(cc);
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);

        /* Show every part of the snake body */
        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);
        }

    }

    /* Generate a new food */
    public static void newFood() {
        start: while (true) {
            /* X a Y coords */
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            /* Handle that the  */
            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }

            /* Generate a random color and add 1 to the speed */
            foodcolor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    /* Reset all the variables to their default state */
    public static void resetVars(){
        speed = 5;
        foodcolor = 0;
        width = 30;
        height = 30;
        foodX = 0;
        foodY = 0;
        cornersize = 25;
        snake = new ArrayList<>();
        direction = Dir.left;
        gameOver = false;
        rand = new Random();
        stage = new Stage();
        con = new Controller();
        score = 0;
    }
}
