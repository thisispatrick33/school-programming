package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class SpaceGame{
    /* GAME Variables */
    private static final Random RAND = new Random();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 60;
    static Image PLAYER_IMG = new Image("file:src/Image/rocket.png");
    static Image EXPLOSION_IMG = new Image("file:src/Image/explosion.png");
    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;
    static Image BOMBS_IMG[] = new Image[]{
            new Image("file:src/Image/asteroid1.png"),
            new Image("file:src/Image/asteroid2.png"),
            new Image("file:src/Image/asteroid3.png"),
    };
    final int MAX_BOMBS = 10,  MAX_SHOTS = 15;
    boolean gameOver = false;
    private GraphicsContext gc;
    Rocket player;
    List<Shot> shots;
    List<Universe> univ;
    List<Bomb> Bombs;
    Timeline timeline;
    private double mouseX;
    private int score;
    static Stage stage = new Stage();
    static Controller con = new Controller();

    /* Main function */
    public void playSpace() {
        /* Canvas set up*/
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        /* The timeline which handles the game */
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            try {
                run(gc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));

        /* Set timeline to indefinite and start it */
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        /* Set up functions for event onMouseClicked and onMouseMoved */
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if(shots.size() < MAX_SHOTS) shots.add(player.shoot());

        });

        /* Variables for game elements */
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        Bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;

        /* Add bombs based on the Max_Bombs variable */
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Bombs::add);

        /* Set up the stage and show it */
        if(stage.getStyle().toString().equals("DECORATED")){
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Space Invaders");
        stage.show();
    }

    /* Function which is called in the Timeline */
    private void run(GraphicsContext gc) throws IOException {
        /* Shows the score */
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60,  20);

        /* When the game */
        if(gameOver) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.YELLOW);
            gc.fillText("Game Over \n Your Score is: " + score, WIDTH / 2, HEIGHT /2.5);
            stage.close();
            timeline.stop();
            con.goToScore("Space", new int[]{ score });

        }
        /* Draw the background */
        univ.forEach(Universe::draw);

        /* Update the player position and draw him */
        player.update();
        player.draw();
        player.posX = (int) mouseX;

        /* Draw the falling Meteorites and handle the collision with the player */
        Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if(player.colide(e) && !player.exploding) {
                player.explode();
            }
        });

        /* Shoot a shot */
        for (int i = shots.size() - 1; i >=0 ; i--) {
            Shot shot = shots.get(i);
            /* Determine whether to remove a shot which is out of bounds */
            if(shot.posY < 0 || shot.toRemove)  {
                shots.remove(i);
                continue;
            }
            /* Update and draw the shot */
            shot.update();
            shot.draw();
            /* If a shot collides with a Meteorite, explode it */
            for (Bomb bomb : Bombs) {
                if(shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        /* If a Meteorite is in the destroyed state, then create a new one */
        for (int i = Bombs.size() - 1; i >= 0; i--){
            if(Bombs.get(i).destroyed)  {
                Bombs.set(i, newBomb());
            }
        }

        /* If the player is destroyed end the game */
        gameOver = player.destroyed;
        /* Handle the universe */
        if(RAND.nextInt(10) > 2) {
            univ.add(new Universe());
        }
        for (int i = 0; i < univ.size(); i++) {
            if(univ.get(i).posY > HEIGHT)
                univ.remove(i);
        }
    }

    /* Player class */
    public class Rocket {
        /* Player variables */
        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionStep = 0;

        /* Constructor */
        public Rocket(int posX, int posY, int size,  Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        /* Returns the position where to draw the shot */
        public Shot shoot() {
            return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
        }

        /* Explosion animation handler */
        public void update() {
            if(exploding) explosionStep++;
            destroyed = explosionStep > EXPLOSION_STEPS;
        }

        /* Determines which state to draw the player in */
        public void draw() {
            if(exploding) {
                gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W, (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
                        EXPLOSION_W, EXPLOSION_H,
                        posX, posY, size, size);
            }
            else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }

        /* Handle collisions with other objects */
        public boolean colide(Rocket other) {
            int d = distance(this.posX + size / 2, this.posY + size /2,
                    other.posX + other.size / 2, other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2 ;
        }

        /* Is called when player collided */
        public void explode() {
            exploding = true;
            explosionStep = -1;
        }

    }

    /* Meteorites subclass of Rocket*/
    public class Bomb extends Rocket {
        /* Speed variable */
        int SPEED = (score/5)+2;

        /* Constructor */
        public Bomb(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        /* Update when destroyed */
        public void update() {
            super.update();
            if(!exploding && !destroyed) posY += SPEED;
            if(posY > HEIGHT) destroyed = true;
        }
    }

    /* Bullet class */
    public class Shot {
        /* Variables */
        public boolean toRemove;
        int posX, posY, speed = 10;
        static final int size = 6;

        /* Constructor */
        public Shot(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        /* Update position */
        public void update() {
            posY-=speed;
        }

        /* Change the bullet type */
        public void draw() {
            gc.setFill(Color.RED);
            if (score >=50 && score<=70 || score>=120) {
                gc.setFill(Color.YELLOWGREEN);
                speed = 50;
                gc.fillRect(posX-5, posY-10, size+10, size+30);
            } else {
                gc.fillOval(posX, posY, size, size);
            }
        }

        /* Handle the collision with a Meteorite */
        public boolean colide(Rocket Rocket) {
            int distance = distance(this.posX + size / 2, this.posY + size / 2,
                    Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
            return distance  < Rocket.size / 2 + size / 2;
        }
    }

    /* Universe Background */
    public class Universe {
        /* Variables */
        int posX, posY;
        private int h, w, r, g, b;
        private double opacity;

        /* Constructor */
        public Universe() {
            posX = RAND.nextInt(WIDTH);
            posY = 0;
            w = RAND.nextInt(5) + 1;
            h =  RAND.nextInt(5) + 1;
            r = RAND.nextInt(100) + 150;
            g = RAND.nextInt(100) + 150;
            b = RAND.nextInt(100) + 150;
            opacity = RAND.nextFloat();
            if(opacity < 0) opacity *=-1;
            if(opacity > 0.5) opacity = 0.5;
        }

        /* Draw the background */
        public void draw() {
            if(opacity > 0.8) opacity-=0.01;
            if(opacity < 0.1) opacity+=0.01;
            gc.setFill(Color.rgb(r, g, b, opacity));
            gc.fillOval(posX, posY, w, h);
            posY+=20;
        }
    }

    /* Create a new Bomb */
    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
    }

    /* Get the distance between two objects */
    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

}
