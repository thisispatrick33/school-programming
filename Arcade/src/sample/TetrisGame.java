package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TetrisGame {

    /* Game Variables */
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int XMAX = SIZE * 12;
    public static int YMAX = SIZE * 24;
    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, XMAX + 150, YMAX);
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextObj = makeRect();
    private static int linesNo = 0;
    static Stage stage = new Stage();
    static Controller con = new Controller();
    Timer fall = new Timer();
    int points = 0;
    int lineNumber = 0;

    /* Main function to start the game */
    public void playTetris(){
        /* Fill the MESH with (a, 0) */
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        /* Game ScoreBoard elements */
        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(50);
        scoretext.setX(XMAX + 5);
        Text level = new Text("Lines: ");
        level.setStyle("-fx-font: 20 arial;");
        level.setY(100);
        level.setX(XMAX + 5);
        level.setFill(Color.GREEN);
        group.getChildren().addAll(scoretext, line, level);

        /* Create a new Form and add to the Scene*/
        Form a = nextObj;
        group.getChildren().addAll(a.blockA, a.blockB, a.blockC, a.blockD);
        moveOnKeyPress(a);
        object = a;
        nextObj = makeRect();

        /* Set up stage and show it */
        if(stage.getStyle().toString().equals("DECORATED")){
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        /* Create a new Timer */
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        /* Determine if there are blocks at the top */
                        if (object.blockA.getY() == 0 || object.blockB.getY() == 0 || object.blockC.getY() == 0
                                || object.blockD.getY() == 0)
                            top++;
                        else
                            top = 0;

                        /* If there are 2 blocks at the top stop the game, go to score handler adn reset variables */
                        if (top == 2) {
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial;");
                            over.setY(250);
                            over.setX(10);
                            group.getChildren().add(over);
                            game = false;
                            stage.close();
                            resetVars();
                            fall.cancel();
                            try {
                                con.goToScore("Tetris", new int[]{ points, lineNumber });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        /* If the game is still in progress update the falling object and scores */
                        if (game) {
                            MoveDown(object);
                            scoretext.setText("Score: " + (score));
                            level.setText("Lines: " + (linesNo));
                        }
                    }
                });
            }
        };
        /* set up the timer */
        fall.schedule(task, 0, 300);
    }

    /* Handle to player inputs */
    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    /* Move to the right */
                    case RIGHT:
                        MoveRight(form);
                        break;
                    /* Move down and add additional points */
                    case DOWN:
                        MoveDown(form);
                        score++;
                        points = score;
                        break;
                    /* Move to the left*/
                    case LEFT:
                        MoveLeft(form);
                        break;
                    /* Rotate */
                    case UP:
                        MoveTurn(form);
                        break;
                }
            }
        });
    }

    /* Handle the rotation of blocks*/
    private void MoveTurn(Form form) {
        /* Set up the block */
        int f = form.form;
        Rectangle a = form.blockA;
        Rectangle b = form.blockB;
        Rectangle c = form.blockC;
        Rectangle d = form.blockD;
        /* Determine the block type, and its current rotation, based on that rotate it, ends on line 441*/
        switch (form.getName()) {
            case "j":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.blockA);
                    MoveDown(form.blockA);
                    MoveDown(form.blockC);
                    MoveLeft(form.blockC);
                    MoveDown(form.blockD);
                    MoveDown(form.blockD);
                    MoveLeft(form.blockD);
                    MoveLeft(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    MoveLeft(form.blockD);
                    MoveLeft(form.blockD);
                    MoveUp(form.blockD);
                    MoveUp(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(form.blockA);
                    MoveUp(form.blockA);
                    MoveUp(form.blockC);
                    MoveRight(form.blockC);
                    MoveUp(form.blockD);
                    MoveUp(form.blockD);
                    MoveRight(form.blockD);
                    MoveRight(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    MoveRight(form.blockD);
                    MoveRight(form.blockD);
                    MoveDown(form.blockD);
                    MoveDown(form.blockD);
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.blockA);
                    MoveDown(form.blockA);
                    MoveUp(form.blockC);
                    MoveRight(form.blockC);
                    MoveUp(form.blockB);
                    MoveUp(form.blockB);
                    MoveRight(form.blockB);
                    MoveRight(form.blockB);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveRight(form.blockB);
                    MoveRight(form.blockB);
                    MoveDown(form.blockB);
                    MoveDown(form.blockB);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.blockA);
                    MoveUp(form.blockA);
                    MoveDown(form.blockC);
                    MoveLeft(form.blockC);
                    MoveDown(form.blockB);
                    MoveDown(form.blockB);
                    MoveLeft(form.blockB);
                    MoveLeft(form.blockB);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveLeft(form.blockB);
                    MoveLeft(form.blockB);
                    MoveUp(form.blockB);
                    MoveUp(form.blockB);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    MoveUp(form.blockD);
                    MoveUp(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    MoveDown(form.blockD);
                    MoveDown(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    MoveUp(form.blockD);
                    MoveUp(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    MoveDown(form.blockD);
                    MoveDown(form.blockD);
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveDown(form.blockD);
                    MoveLeft(form.blockD);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.blockA);
                    MoveDown(form.blockA);
                    MoveLeft(form.blockD);
                    MoveUp(form.blockD);
                    MoveUp(form.blockC);
                    MoveRight(form.blockC);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveUp(form.blockD);
                    MoveRight(form.blockD);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.blockA);
                    MoveUp(form.blockA);
                    MoveRight(form.blockD);
                    MoveDown(form.blockD);
                    MoveDown(form.blockC);
                    MoveLeft(form.blockC);
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.blockB);
                    MoveRight(form.blockB);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    MoveLeft(form.blockD);
                    MoveLeft(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.blockB);
                    MoveLeft(form.blockB);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    MoveRight(form.blockD);
                    MoveRight(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.blockB);
                    MoveRight(form.blockB);
                    MoveLeft(form.blockC);
                    MoveUp(form.blockC);
                    MoveLeft(form.blockD);
                    MoveLeft(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.blockB);
                    MoveLeft(form.blockB);
                    MoveRight(form.blockC);
                    MoveDown(form.blockC);
                    MoveRight(form.blockD);
                    MoveRight(form.blockD);
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.blockA);
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveRight(form.blockA);
                    MoveUp(form.blockB);
                    MoveRight(form.blockB);
                    MoveDown(form.blockD);
                    MoveLeft(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.blockA);
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveLeft(form.blockA);
                    MoveDown(form.blockB);
                    MoveLeft(form.blockB);
                    MoveUp(form.blockD);
                    MoveRight(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.blockA);
                    MoveUp(form.blockA);
                    MoveRight(form.blockA);
                    MoveRight(form.blockA);
                    MoveUp(form.blockB);
                    MoveRight(form.blockB);
                    MoveDown(form.blockD);
                    MoveLeft(form.blockD);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.blockA);
                    MoveDown(form.blockA);
                    MoveLeft(form.blockA);
                    MoveLeft(form.blockA);
                    MoveDown(form.blockB);
                    MoveLeft(form.blockB);
                    MoveUp(form.blockD);
                    MoveRight(form.blockD);
                    form.changeForm();
                    break;
                }
                break;
        }
    }

    /* Remove rows from the playing field */
    private void RemoveRows(Pane pane) {
        /* Variables used in this function */
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;

        /* Go through the MESH and find lines */
        for (int i = 0; i < MESH[0].length; i++) {
            for (int j = 0; j < MESH.length; j++) {
                if (MESH[j][i] == 1)
                    full++;
            }
            if (full == MESH.length)
                lines.add(i);
            full = 0;
        }

        /* If there were any lines remove them using this*/
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                score += 50;
                points = score;
                linesNo++;
                lineNumber = linesNo;
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[(int) Math.floor(a.getX() / SIZE)][(int) Math.floor(a.getY() / SIZE)] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }
                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }

    /* Move a block down */
    private void MoveDown(Rectangle rect) {
        if (rect.getY() + MOVE < YMAX)
            rect.setY(rect.getY() + MOVE);
    }

    /* Move a block to the right */
    private void MoveRight(Rectangle rect) {
        if (rect.getX() + MOVE <= XMAX - SIZE)
            rect.setX(rect.getX() + MOVE);
    }

    /* Move a block to the left */
    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - MOVE >= 0)
            rect.setX(rect.getX() - MOVE);
    }

    /* Move a block up */
    private void MoveUp(Rectangle rect) {
        if (rect.getY() - MOVE > 0)
            rect.setY(rect.getY() - MOVE);
    }

    private void MoveDown(Form form) {
        if (form.blockA.getY() == YMAX - SIZE || form.blockB.getY() == YMAX - SIZE || form.blockC.getY() == YMAX - SIZE
                || form.blockD.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int) form.blockA.getX() / SIZE][(int) form.blockA.getY() / SIZE] = 1;
            MESH[(int) form.blockB.getX() / SIZE][(int) form.blockB.getY() / SIZE] = 1;
            MESH[(int) form.blockC.getX() / SIZE][(int) form.blockC.getY() / SIZE] = 1;
            MESH[(int) form.blockD.getX() / SIZE][(int) form.blockD.getY() / SIZE] = 1;
            RemoveRows(group);

            Form a = nextObj;
            nextObj = makeRect();
            object = a;
            group.getChildren().addAll(a.blockA, a.blockB, a.blockC, a.blockD);
            moveOnKeyPress(a);
        }

        if (form.blockA.getY() + MOVE < YMAX && form.blockB.getY() + MOVE < YMAX && form.blockC.getY() + MOVE < YMAX
                && form.blockD.getY() + MOVE < YMAX) {
            int movea = MESH[(int) form.blockA.getX() / SIZE][((int) form.blockA.getY() / SIZE) + 1];
            int moveb = MESH[(int) form.blockB.getX() / SIZE][((int) form.blockB.getY() / SIZE) + 1];
            int movec = MESH[(int) form.blockC.getX() / SIZE][((int) form.blockC.getY() / SIZE) + 1];
            int moved = MESH[(int) form.blockD.getX() / SIZE][((int) form.blockD.getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.blockA.setY(form.blockA.getY() + MOVE);
                form.blockB.setY(form.blockB.getY() + MOVE);
                form.blockC.setY(form.blockC.getY() + MOVE);
                form.blockD.setY(form.blockD.getY() + MOVE);
            }
        }
    }

    /* Move the component A from a Block */
    private boolean moveA(Form form) {
        return (MESH[(int) form.blockA.getX() / SIZE][((int) form.blockA.getY() / SIZE) + 1] == 1);
    }

    /* Move the component B from a Block */
    private boolean moveB(Form form) {
        return (MESH[(int) form.blockB.getX() / SIZE][((int) form.blockB.getY() / SIZE) + 1] == 1);
    }

    /* Move the component C from a Block */
    private boolean moveC(Form form) {
        return (MESH[(int) form.blockC.getX() / SIZE][((int) form.blockC.getY() / SIZE) + 1] == 1);
    }

    /* Move the component D from a Block */
    private boolean moveD(Form form) {
        return (MESH[(int) form.blockD.getX() / SIZE][((int) form.blockD.getY() / SIZE) + 1] == 1);
    }

    private boolean cB(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * MOVE <= XMAX - SIZE;
        if (x < 0)
            xb = rect.getX() + x * MOVE >= 0;
        if (y >= 0)
            yb = rect.getY() - y * MOVE > 0;
        if (y < 0)
            yb = rect.getY() + y * MOVE < YMAX;
        return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }

    public static void MoveRight(Form form) {
        if (form.blockA.getX() + MOVE <= XMAX - SIZE && form.blockB.getX() + MOVE <= XMAX - SIZE
                && form.blockC.getX() + MOVE <= XMAX - SIZE && form.blockD.getX() + MOVE <= XMAX - SIZE) {
            int movea = MESH[((int) form.blockA.getX() / SIZE) + 1][((int) form.blockA.getY() / SIZE)];
            int moveb = MESH[((int) form.blockB.getX() / SIZE) + 1][((int) form.blockB.getY() / SIZE)];
            int movec = MESH[((int) form.blockC.getX() / SIZE) + 1][((int) form.blockC.getY() / SIZE)];
            int moved = MESH[((int) form.blockD.getX() / SIZE) + 1][((int) form.blockD.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.blockA.setX(form.blockA.getX() + MOVE);
                form.blockB.setX(form.blockB.getX() + MOVE);
                form.blockC.setX(form.blockC.getX() + MOVE);
                form.blockD.setX(form.blockD.getX() + MOVE);
            }
        }
    }

    public static void MoveLeft(Form form) {
        if (form.blockA.getX() - MOVE >= 0 && form.blockB.getX() - MOVE >= 0 && form.blockC.getX() - MOVE >= 0
                && form.blockD.getX() - MOVE >= 0) {
            int movea = MESH[((int) form.blockA.getX() / SIZE) - 1][((int) form.blockA.getY() / SIZE)];
            int moveb = MESH[((int) form.blockB.getX() / SIZE) - 1][((int) form.blockB.getY() / SIZE)];
            int movec = MESH[((int) form.blockC.getX() / SIZE) - 1][((int) form.blockC.getY() / SIZE)];
            int moved = MESH[((int) form.blockD.getX() / SIZE) - 1][((int) form.blockD.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.blockA.setX(form.blockA.getX() - MOVE);
                form.blockB.setX(form.blockB.getX() - MOVE);
                form.blockC.setX(form.blockC.getX() - MOVE);
                form.blockD.setX(form.blockD.getX() - MOVE);
            }
        }
    }

    public static Form makeRect() {
        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
                d = new Rectangle(SIZE-1, SIZE-1);
        if (block < 15) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            name = "t";
        } else if (block < 90) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 + SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX(XMAX / 2 - SIZE - SIZE);
            b.setX(XMAX / 2 - SIZE);
            c.setX(XMAX / 2);
            d.setX(XMAX / 2 + SIZE);
            name = "i";
        }
        return new Form(a, b, c, d, name);
    }

    public static void resetVars(){
        XMAX = SIZE * 12;
        YMAX = SIZE * 24;
        MESH = new int[XMAX / SIZE][YMAX / SIZE];
        group = new Pane();
        scene = new Scene(group, XMAX + 150, YMAX);
        score = 0;
        top = 0;
        game = true;
        nextObj = makeRect();
        linesNo = 0;
    }
}
