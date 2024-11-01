package ch.supsi.connectfour.frontend;


import ch.supsi.connectfour.frontend.initializer.Initializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Initializer.init(stage);
    }

    public static void main(String[] args){
        launch(args);
    }
}
