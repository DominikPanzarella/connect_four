package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.handler.CancelHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.CancelReceiver;
import javafx.stage.Stage;

public class CancelCommand<T extends CancelReceiver<CancelHandler>> extends AbstractCommand<T> {
    private final Stage stage;
    protected CancelCommand(T receiver, Stage stage) {
        super(receiver);
        this.stage = stage;
    }

    public static <T extends CancelReceiver<CancelHandler>> CancelCommand<T> create(T receiver,Stage stage) throws InstantiationException {
        if(receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return  new CancelCommand<>(receiver,stage);
    }

    @Override
    public void execute() {
        receiver.cancel(stage);
    }
}
