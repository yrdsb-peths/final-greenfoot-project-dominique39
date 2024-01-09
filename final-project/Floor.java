import greenfoot.*;

public class Floor extends Obstructables
{
    private PressurePlate pressurePlate = null;
    private int displaceX;
    private int displaceY;
    private int movedX = 0;
    private int movedY = 0;
    
    //normal Floor
    public Floor(int width, int height){
        GreenfootImage img = new GreenfootImage("objects/floor.png");
        img.scale(width, height);
        setImage(img);
    }
    
    //Floor with actions to be triggered by specific pressure plate
    public Floor(int width, int height, PressurePlate pressurePlate, int displaceX, int displaceY){
        GreenfootImage img = new GreenfootImage("objects/floor.png");
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