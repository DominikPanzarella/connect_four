package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class OpenFilePresentable implements Presentable
{
    private final String stringPlaceholder = "user.message.open.success";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    public OpenFilePresentable(){

    }


    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
