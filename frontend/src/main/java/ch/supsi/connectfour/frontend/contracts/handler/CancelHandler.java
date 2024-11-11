package ch.supsi.connectfour.frontend.contracts.handler;

import javafx.stage.Stage;

public interface CancelHandler extends Handler
{
    void cancel(Stage toClose);
}
