import greenfoot.*;

public class Actionable extends GameObjects
{
    PressurePlate pressurePlate = null;
    int displaceX = 0;
    int displaceY = 0;
    int movedX = 0;
    int movedY = 0;
    
    //not actioning obj
    public Actionable(int width, int height, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);
    }
    
    //objs with actions to be triggered by specific pressure plate
    public Actionable(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY, String file){
        GreenfootImage img = new GreenfootImage("objects/" + file);
        img.scale(width, height);
        setImage(img);
        
        this.pressurePlate = pressurePlate;
        this.displaceX = displaceX;
        this.displaceY = displaceY;
    }
    
    public void act(){
        if(pressurePlate != null && pressurePlate.isStepped()){
            //move floor in X direction
            if(movedX != displaceX){
                if(displaceX > 0){
                    movedX++;
                    setLocation(getX()+1, getY());
                }else{
                    movedX--;
                    setLocation(getX()-1, getY());
                }
            }
            
            //move floor in Y direction
            if(movedY != displaceY){
                if(displaceY > 0){
                    movedY++;
                    setLocation(getX(), getY()+1);
                }else{
                    movedY--;
                    setLocation(getX(), getY()-1);
                }
            }
        }
    }
}