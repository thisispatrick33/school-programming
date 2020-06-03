package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /* Start the application */

    @Override
    public void start(Stage stage) throws Exception{
        Controller con = new Controller();
        con.returnToChooser();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
