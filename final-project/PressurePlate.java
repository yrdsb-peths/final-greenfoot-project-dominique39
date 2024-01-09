import greenfoot.*;
import java.util.*;

public class PressurePlate extends NonObstructables
{
    private boolean stepped;
    
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
}