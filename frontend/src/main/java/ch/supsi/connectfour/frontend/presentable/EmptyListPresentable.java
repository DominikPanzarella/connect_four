package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class EmptyListPresentable implements Presentable
{
    private final String stringPlaceholder = "user.emptyGame";
    private final TranslationsController translationsController = TranslationsController.getInstance();


    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
