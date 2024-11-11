package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.CancelHandler;
import javafx.stage.Stage;

public interface CancelReceiver<T extends CancelHandler> extends Receiver
{
    void cancel(Stage toClose);
}
