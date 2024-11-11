package ch.supsi.connectfour.frontend.mediator;

import ch.supsi.connectfour.frontend.contracts.handler.NewGameHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.NewGameReceiver;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NewGameMediator<T extends NewGameReceiver<NewGameHandler>> implements ShortcutMediator
{
    private T receiver;
    private KeyCombination openFileKeyCombination;
    private static NewGameMediator myself;


    protected NewGameMediator(Stage stage, T receiver, KeyCombination openFileKeyCombination){
        this.openFileKeyCombination = openFileKeyCombination;
        this.receiver = receiver;

        Scene scene = stage.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            if(openFileKeyCombination.match(keyEvent))
                receiver.newGame();
        });
    }

    public static <T extends NewGameReceiver<NewGameHandler>> NewGameMediator<T> getInstance(Stage stage, T receiver, KeyCombination openFileKeyCombination){

        if(myself == null)
            myself = new NewGameMediator<>(stage, receiver, openFileKeyCombination);
        return myself;
    }


}
