package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.MyColorInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.contracts.observer.*;
import ch.supsi.connectfour.frontend.contracts.viewContracts.UncontrolledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoardView implements UncontrolledViewFxml, MoveObserver, ClearViewObserver, RePaintObserver, UndoObserver, RedoObserver
{
    @FXML
    private GridPane gridPane;

    private static Parent parent;
    private static GameBoardView myself;
    private final int ROWS = 6;
    private final int COLS = 7;

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

        Character currentPlayerSymbol = move.getPlayer().getPlayerCharacter();
        MyColorInterface playerColors = move.getPlayer().getPlayerColors();
        String playerColor = playerColors.toHex();
        System.out.println(playerColor);
        label.setStyle("-fx-background-color: " + playerColor + ";");
        label.setText(currentPlayerSymbol.toString());
    }

    @Override
    public void clearView() {
        for(int i=0;i<ROWS; i++)
        {
            for(int j=0; j<COLS; j++)
            {
                Button label = (Button)gridPane.lookup("#cell" + i+ j);
                label.setStyle("-fx-background-color: " + "white" + ";");
                label.setText("");
            }
        }
    }


    @Override
    public void repaint(List<MoveInterface> moves) {
        for(MoveInterface move : moves){
            final int row = move.getRow();
            final int col = move.getColumn();

            Button label = (Button)gridPane.lookup("#cell" + row+ col);
            Character currentPlayerSymbol = move.getPlayer().getPlayerCharacter();
            MyColorInterface playerColors = move.getPlayer().getPlayerColors();
            String playerColor = playerColors.toHex();
            System.out.println(playerColor);
            label.setStyle("-fx-background-color: " + playerColor + ";");
            label.setText(currentPlayerSymbol.toString());
        }
    }

    @Override
    public void redo(List<MoveInterface> data) {
        clearView();
        repaint(data);
    }

    @Override
    public void undo(List<MoveInterface> data) {
        repaint(data);
    }
}
