package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface GameServiceInterface
{
    boolean saveGame(final String destination, final String extension, final List<MoveInterface> moves) throws IOException;
    List<MoveInterface> loadGame(final String source);



}
