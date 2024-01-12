import greenfoot.*;

public class Laser extends Actionable
{
    public Laser(int width, int height){
        super(width, height, "laser.png");
    }
    
    public Laser(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY){
        super(width, height, pressurePlate, displaceX, displaceY, "laser.png");
    }
}