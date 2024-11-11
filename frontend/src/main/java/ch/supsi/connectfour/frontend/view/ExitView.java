package ch.supsi.connectfour.frontend.view;


import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.command.CancelCommand;
import ch.supsi.connectfour.frontend.command.OkCommand;
import ch.supsi.connectfour.frontend.contracts.handler.CancelHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OKHandler;
import ch.supsi.connectfour.frontend.contracts.observer.ExitObserver;
import ch.supsi.connectfour.frontend.contracts.receiver.CancelReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.OkReceiver;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExitView implements ControlledView, ExitObserver
{
    @FXML
    Button cancelButton;
    @FXML
    Button okButton;

    private static ExitView myself;
    private static Parent parent;
    private static Stage stage;

    private ExitView(){
    }

    public static ExitView getInstance(){
        if(myself == null){
            try{
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = ExitView.class.getResource("/view/exitView.fxml");
                if(fxmlurl != null){;
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    myself = new ExitView();
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    stage.setScene(new Scene(parent));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    public <T extends OkCommand<? extends OkReceiver<OKHandler>>> void createOKBehaviour(T command){
        okButton.setOnAction(action->command.execute());
    }

    public <T extends CancelCommand<? extends CancelReceiver<CancelHandler>>> void createCancelBehaviour(T command){
        cancelButton.setOnAction(action->command.execute());
    }

    @Override
    public void initialize() {

    }

    @Override
    public Parent getParent() {
        return parent;
    }


    @Override
    public void exit() {
        stage.show();
    }


    public Stage getStage(){
        return stage;
    }
}
