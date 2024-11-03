package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

import java.nio.file.Path;
import java.util.List;

public interface GameServiceInterface
{
    boolean saveGame(final Path destination);
    List<MoveInterface> loadGame(final Path source);



}
