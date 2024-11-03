package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.receiver.Receiver;

public abstract class AbstractCommand<T extends Receiver> implements Command
{
    protected T receiver;

    protected AbstractCommand(T receiver){
        this.receiver = receiver;
    }


}
