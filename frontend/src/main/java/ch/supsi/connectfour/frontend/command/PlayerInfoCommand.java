package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PlayerInfoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.AboutReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.PlayerInfoReceiver;

public class PlayerInfoCommand<T extends PlayerInfoReceiver<PlayerInfoHandler>> extends AbstractCommand<T>
{
    private int position;
    protected PlayerInfoCommand(T receiver) {
        super(receiver);
        this.position = receiver.getPosition();
    }

    public static <T extends PlayerInfoReceiver<PlayerInfoHandler>> PlayerInfoCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new PlayerInfoCommand<>( receiver);
    }

    @Override
    public void execute() {
        receiver.playerInfo(position);
    }
}
