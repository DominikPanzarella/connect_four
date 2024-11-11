package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class NameAlreadyUsedPresentable implements Presentable
{
    private final String stringPlaceholder = "label.nameUsed";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
