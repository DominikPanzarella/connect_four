package ch.supsi.connectfour.backend.service.gamelogic.player;

public class MyColor implements MyColorInterface
{
    public double red;
    public double green;
    public double blue;


    public MyColor(double red, double green, double blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public double getRedChannel() {
        return red;
    }

    @Override
    public double getGreenChannel() {
        return green;
    }

    @Override
    public double getBlueChannel() {
        return blue;
    }

    @Override
    public String toHex() {
        int redInt = (int) Math.round(clamp(red) * 255);
        int greenInt = (int) Math.round(clamp(green) * 255);
        int blueInt = (int) Math.round(clamp(blue) * 255);

        return String.format("#%02X%02X%02X", redInt, greenInt, blueInt);
    }

    private double clamp(double value) {
        return Math.max(0, Math.min(1, value));
    }
}
