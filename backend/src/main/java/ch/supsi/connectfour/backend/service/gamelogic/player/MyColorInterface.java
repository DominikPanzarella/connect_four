package ch.supsi.connectfour.backend.service.gamelogic.player;

public interface MyColorInterface
{
    double getRedChannel();
    double getGreenChannel();
    double getBlueChannel();
    String toHex();
    boolean equals(MyColorInterface color);
}
