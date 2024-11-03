package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;


public interface OpenFileReceiver<T extends OpenFileHandler> extends Receiver {
    void openFile();
}
