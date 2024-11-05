package ch.supsi.connectfour.frontend.presentable;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;

import java.awt.print.Printable;

public class PlayerMovePresentable implements Presentable
{
    private final String stringPlaceholder = "user.message.player.move";
    private final TranslationsController translationsController = TranslationsController.getInstance();
    private Move move;

    public PlayerMovePresentable(final Move move)
    {
        this.move = move;
    }


    @Override
    public String getString(){
        String playerName = move.getPlayer().getName();
        String placeHolderTranslated = translationsController.translate(stringPlaceholder);
        return String.format(placeHolderTranslated, playerName, move.getRow(), move.getColumn());
    }



}
