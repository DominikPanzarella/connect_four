package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.ChangeLanguageHandler;

public interface ChangeLanguageReceiver<T extends ChangeLanguageHandler> extends Receiver{

    void changeLanguage(String languageTag);
}
