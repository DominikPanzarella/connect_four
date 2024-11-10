package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.frontend.contracts.handler.ChangeLanguageHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.ChangeLanguageReceiver;

public class ChangeLanguageCommand<T extends ChangeLanguageReceiver<ChangeLanguageHandler>> extends AbstractCommand<T> {
    private final String languageTag;
    protected ChangeLanguageCommand(T receiver, String languageTag) {
        super(receiver);
        this.languageTag = languageTag;
    }

    public static <T extends ChangeLanguageReceiver<ChangeLanguageHandler>> ChangeLanguageCommand<T> create(T receiver, String languageTag) throws InstantiationException {
        if(receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new ChangeLanguageCommand<>(receiver, languageTag);
    }

    @Override
    public void execute() {
        receiver.changeLanguage(this.languageTag);
    }
}
