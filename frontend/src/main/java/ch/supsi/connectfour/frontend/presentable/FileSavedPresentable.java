package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class FileSavedPresentable implements Presentable
{
    private final String stringPlaceholder = "user.message.save.success";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    public FileSavedPresentable(){

    }

    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
