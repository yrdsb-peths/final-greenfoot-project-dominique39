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
    public Actionable(int width, int height, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);
    }
    
    //objs with actions to be triggered by specific pressure plate
    public Actionable(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, int moveSpeed, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);

        this.pressurePlate = pressurePlate;
        this.displaceX = displaceX;
        this.displaceY = displaceY;
        this.moveSpeed = moveSpeed;
    }

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
                if(movedX != 0){
                    if(movedX > 0){
                        movedX--;
                        setLocation(getX()-moveSpeed, getY());
                    }else{
                        movedX++;
                        setLocation(getX()+moveSpeed, getY());
                    }
                }

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