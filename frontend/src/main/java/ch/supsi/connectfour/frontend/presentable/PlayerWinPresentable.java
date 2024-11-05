package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public class PlayerWinPresentable implements Presentable{

    private final String stringPlaceholder = "user.message.gameService.end.winner";
    private final TranslationsController translationsController = TranslationsController.getInstance();

    private Player player;


    public PlayerWinPresentable(final Player player)
    {
        this.player = player;
    }

    @Override
    public String getString() {
        String playerName = player.getName();
        String placeHolderTranslated = translationsController.translate(stringPlaceholder);
        return String.format(placeHolderTranslated, playerName);
    }
}
