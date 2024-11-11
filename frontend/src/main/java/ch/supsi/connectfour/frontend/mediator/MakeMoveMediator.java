package ch.supsi.connectfour.frontend.mediator;

import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MakeMoveReceiver;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MakeMoveMediator<T extends MakeMoveReceiver<MakeMoveHandler>> implements ShortcutMediator
{
    private T receiver;
    private KeyCombination makeMoveKeyCombination;
    private int column;
    private static MakeMoveMediator myself;


    public MakeMoveMediator(int column,Stage stage, T receiver, KeyCombination makeMoveKeyCombination){
        this.makeMoveKeyCombination = makeMoveKeyCombination;
        this.receiver = receiver;

        Scene scene = stage.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            if(makeMoveKeyCombination.match(keyEvent))
                receiver.makeMove(column);
        });
    }

    /*public static <T extends MakeMoveReceiver<MakeMoveHandler>> MakeMoveMediator<T> getInstance(int column,Stage stage, T receiver, KeyCombination runPipelineKeyCombination){

        if(myself == null)
            myself = new MakeMoveMediator(column, stage, receiver, runPipelineKeyCombination);
        return myself;
    }*/
}
