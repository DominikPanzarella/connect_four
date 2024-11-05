package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.command.MakeMoveCommand;
import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.observable.ColumnFullObservable;
import ch.supsi.connectfour.frontend.contracts.observable.MoveObservable;
import ch.supsi.connectfour.frontend.contracts.observer.ColumnFullObserver;
import ch.supsi.connectfour.frontend.contracts.observer.GameHasAWinnerObserver;
import ch.supsi.connectfour.frontend.contracts.receiver.MakeMoveReceiver;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import ch.supsi.connectfour.frontend.presentable.Presentable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnSelectorView implements ControlledViewFxml, MoveObservable, ColumnFullObserver, GameHasAWinnerObserver
{
    @FXML
    private GridPane columnPane;
    @FXML
    private Button col0;
    @FXML
    private Button col1;
    @FXML
    private Button col2;
    @FXML
    private Button col3;
    @FXML
    private Button col4;
    @FXML
    private Button col5;
    @FXML
    private Button col6;
    private static Parent parent;
    private static ColumnSelectorView myself;

    protected ColumnSelectorView()
    {
        //empty constructor
    }

    public static ColumnSelectorView getInstance(){
        if(myself==null){
            myself = new ColumnSelectorView();
            try{
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = ColumnSelectorView.class.getResource("/view/columnselector.fxml");
                if(fxmlurl != null){
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    @Override
    public void initialize() {
    }

    @Override
    public Parent getParent() {
        return parent;
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn0(T command){
        col0.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn1(T command){
        col1.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn2(T command){
        col2.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn3(T command){
        col3.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn4(T command){
        col4.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn5(T command){
        col5.setOnAction(action->command.execute());
    }

    public <T extends MakeMoveCommand<? extends MakeMoveReceiver<MakeMoveHandler>>> void makeMoveColumn6(T command){
        col6.setOnAction(action->command.execute());
    }

    //TODO: refactor with a more flexible approach
    @Override
    public void disableColumn(int column) {
        switch (column)
        {
            case 0:
                col0.setDisable(true);
            break;
            case 1:
                col1.setDisable(true);
                break;
            case 2:
                col2.setDisable(true);
                break;
            case 3:
                col3.setDisable(true);
                break;
            case 4:
                col4.setDisable(true);
                break;
            case 5:
                col5.setDisable(true);
                break;
            case 6:
                col6.setDisable(true);
                break;
        }
    }

    @Override
    public void gameHasAWinner(Presentable winnerPlayer) {
        col0.setDisable(true);
        col1.setDisable(true);
        col2.setDisable(true);
        col3.setDisable(true);
        col4.setDisable(true);
        col5.setDisable(true);
        col6.setDisable(true);
    }
}
