package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class SymbolAlreadyUsedPresentable implements Presentable
{
    private final String stringPlaceholder = "label.symbolUsed";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
