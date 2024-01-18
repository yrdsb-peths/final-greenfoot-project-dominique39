import greenfoot.*;
import java.util.*;

public class PressurePlate extends GameObjects
{
    private boolean stepped;
    private boolean hold;
    
    /**
     * Creates a pressure plate
     *
     * @param hold  speifies if the objects controlled by this pressure plate should return original position when it is not stepped
     */
    public PressurePlate(int hold){
        if(hold == 1){
            this.hold = true;
        }else{
            this.hold = false;
        }
    }
    
    /**
     * stepping animation
     *
     */
    public void act(){
        if(stepped){
            setImage("objects/plate_stepped.png");
        }else{
            setImage("objects/plate.png");
        }
        stepped = false;
    }

    /**
     * set pressure plate to 'stepped'
     *
     */
    public void stepped(){
        stepped = true;
    }

    /**
     * returns if the pressure plate is stepped
     *
     * @return the value of stepped
     */
    public boolean isStepped(){
        return stepped;
    }
    
    /**
     * returns if the objects controlled by this pressure plate should stay in place when it is not stepped
     *
     * @return the value of hold
     */
    public boolean isHold(){
        return hold;
    }
}