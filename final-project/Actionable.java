import greenfoot.*;

public class Actionable extends GameObjects
{
    PressurePlate pressurePlate = null;
    int displaceX;
    int displaceY;
    int movedX;
    int movedY;
    int moveSpeed;

    //not actioning obj
    /**
     * Creates an actionable object that is not controlled by a pressure plate
     *
     * @param width the width of the object
     * @param height the height of the object
     * @param file the image file name of the object
     */
    public Actionable(int width, int height, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);
    }
    
    //objs with actions to be triggered by specific pressure plate
    /**
     * Creates an actionable object that is controlled by a pressureplate
     *
     * @param width the width of the object
     * @param height the height of the object
     * @param pressurePlate A parameter
     * @param displaceX the horizontal moving distance of the object
     * @param displaceY the vertical moving distance of the object
     * @param moveSpeed the moving speed of the object
     * @param file the image file name of the object
     */
    public Actionable(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, int moveSpeed, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);

        this.pressurePlate = pressurePlate;
        this.displaceX = displaceX;
        this.displaceY = displaceY;
        this.moveSpeed = moveSpeed;
    }

    /**
     * move this actor when the corresponding pressure plate is stepped and return to original location if applicable
     *
     */
    public void act(){
        if(pressurePlate != null){
            if(pressurePlate.isStepped()){
                //move floor in X direction
                if(movedX != displaceX){
                    if(displaceX > 0){
                        movedX++;
                        setLocation(getX()+moveSpeed, getY());
                    }else{
                        movedX--;
                        setLocation(getX()-moveSpeed, getY());
                    }
                }

                //move floor in Y direction
                if(movedY != displaceY){
                    if(displaceY > 0){
                        movedY++;
                        setLocation(getX(), getY()+moveSpeed);
                    }else{
                        movedY--;
                        setLocation(getX(), getY()-moveSpeed);
                    }
                }
            }else if(!pressurePlate.isHold()){
                //return to original location in X direction
                if(movedX != 0){
                    if(movedX > 0){
                        movedX--;
                        setLocation(getX()-moveSpeed, getY());
                    }else{
                        movedX++;
                        setLocation(getX()+moveSpeed, getY());
                    }
                }

                //return to original location in Y direction
                if(movedY != 0){
                    if(movedY > 0){
                        movedY--;
                        setLocation(getX(), getY()-moveSpeed);
                    }else{
                        movedY++;
                        setLocation(getX(), getY()+moveSpeed);
                    }
                }
            }
        }
    }
}