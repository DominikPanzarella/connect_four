package ch.supsi.connectfour.backend.service.gamelogic.game;

public enum GameStatusType {
    NOT_STARTED,            // Game not started yet
    IN_PROGRESS,            // Game is in progress
    DRAW,                   // No winner, a draw happened
    WINNER                  // There is a winner
}
