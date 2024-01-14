import greenfoot.*;

public class Floor extends Actionable
{
    public Floor(int width, int height){
        super(width, height, "floor.png");
        pushable = false;
    }
    
    public Floor(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, int moveSpeed){
        super(width, height, pressurePlate, displaceX, displaceY, moveSpeed, "floor.png");
        pushable = false;
    }
}