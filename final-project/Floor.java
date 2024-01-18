import greenfoot.*;

public class Floor extends Actionable
{
    /**
     * Creates a floor that is not controlled by a pressure plate
     *
     * @param width the width of the floor
     * @param height the height of the floor
     */
    public Floor(int width, int height){
        super(width, height, "floor.png");
        pushable = false;
    }
    
    /**
     * Creates a floor that is controlled by a pressure plate
     *
     * @param width the width of the floor
     * @param height the height of the floor
     * @param pressurePlate the pressure plate that controls this floor
     * @param displaceX the horizontal distance that will move
     * @param displaceY the vertical distance that will move
     * @param moveSpeed the moving speed of the floor
     */
    public Floor(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, int moveSpeed){
        super(width, height, pressurePlate, displaceX, displaceY, moveSpeed, "floor.png");
        pushable = false;
    }
}