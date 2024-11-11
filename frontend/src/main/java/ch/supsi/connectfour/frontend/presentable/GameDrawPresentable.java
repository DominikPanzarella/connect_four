package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;

public class GameDrawPresentable implements Presentable
{
    private final String stringPlaceholder = "user.message.gameService.end.draw";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    public GameDrawPresentable(){

    }

    @Override
    public String getString() {
        return translationsController.translate(stringPlaceholder);
    }
}
