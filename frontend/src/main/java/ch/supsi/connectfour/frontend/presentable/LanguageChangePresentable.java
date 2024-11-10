package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class LanguageChangePresentable implements Presentable
{
    private final String stringPlaceholder = "label.languageChanged";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
