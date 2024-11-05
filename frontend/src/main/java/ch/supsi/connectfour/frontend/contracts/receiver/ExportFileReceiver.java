package ch.supsi.connectfour.frontend.contracts.receiver;


import ch.supsi.connectfour.frontend.contracts.handler.ExportFileHandler;

public interface ExportFileReceiver<T extends ExportFileHandler> extends Receiver
{
    void exportFile();
}
