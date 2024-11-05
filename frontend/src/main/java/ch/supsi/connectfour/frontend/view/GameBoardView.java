package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.player.MyColorInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.contracts.observer.MoveObserver;
import ch.supsi.connectfour.frontend.contracts.viewContracts.UncontrolledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardView implements UncontrolledViewFxml, MoveObserver
{
    @FXML
    private GridPane gridPane;

    private static Parent parent;
    private static GameBoardView myself;

    protected GameBoardView()
    {
        //empty constructor
    }

    public static GameBoardView getInstance()
    {
        if(myself==null){
            myself = new GameBoardView();
            try{
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = ColumnSelectorView.class.getResource("/view/gameboard.fxml");
                if(fxmlurl != null){
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();

                    String cssPath = GameBoardView.class.getResource("/style.css").toExternalForm();
                    parent.getStylesheets().add(cssPath);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }


    @Override
    public void initialize()
    {

    }

    @Override
    public Parent getParent() {
        return parent;
    }


    @Override
    public void makeMove(Move move) {
        System.out.println("BoardView::playerMoved()");
        final int row = move.getRow();
        final int col = move.getColumn();
        Button label = (Button)gridPane.lookup("#cell" + row+ col);
        System.out.println(label);
        // Retrieve the current player's color preference
        label.setStyle("-fx-background-color: #"+"red" + ";");
        label.setText("X");

        Character currentPlayerSymbol = move.getPlayer().getPlayerCharacter();
        MyColorInterface playerColors = move.getPlayer().getPlayerColors();
        String playerColor = playerColors.toHex();
        System.out.println(playerColor);
        label.setStyle("-fx-background-color: " + playerColor + ";");
        label.setText(currentPlayerSymbol.toString());
    }
}
