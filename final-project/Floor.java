import greenfoot.*;

public class Floor extends Actionable
{
    public Floor(int width, int height){
        super(width, height, "floor.png");
        pushable = false;
    }
    
    public Floor(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY){
        super(width, height, pressurePlate, displaceX, displaceY, "floor.png");
        pushable = false;
    }
}