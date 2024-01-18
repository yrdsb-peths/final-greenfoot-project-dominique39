import greenfoot.*;

public class Laser extends Actionable
{
    /**
     * Creates a laser that is not controlled by a pressure plate
     *
     * @param width the width of the laser
     * @param height the height of the laser
     */
    public Laser(int width, int height){
        super(width, height, "laser.png");
    }
    
    /**
     * Creates a laser that is controlled by a pressure plate
     *
     * @param width the width of the laser
     * @param height the height of the laser
     * @param pressurePlate the pressure plate that controls this laser
     * @param displaceX the horizontal distance that will move
     * @param displaceY the vertical distance that will move
     * @param moveSpeed the moving speed of the laser
     */
    public Laser(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, int moveSpeed){
        super(width, height, pressurePlate, displaceX, displaceY, moveSpeed, "laser.png");
    }
}