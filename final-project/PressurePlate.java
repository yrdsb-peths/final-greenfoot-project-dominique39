import greenfoot.*;
import java.util.*;

public class PressurePlate extends GameObjects
{
    private boolean stepped;
    private boolean hold;
    
    public PressurePlate(int hold){
        if(hold == 1){
            this.hold = true;
        }else{
            this.hold = false;
        }
    }
    
    public void act()
    {
        if(stepped){
            setImage("objects/plate_stepped.png");
        }else{
            setImage("objects/plate.png");
        }
        stepped = false;
    }

    public void stepped(){
        stepped = true;
    }

    public boolean isStepped(){
        return stepped;
    }
    
    public boolean isHold(){
        return hold;
    }
}