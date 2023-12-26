import greenfoot.*;
import java.util.*;

public class Door extends NonObstructables
{
    private  boolean unlocked = false;
    
    public void act()
    {
        if(unlocked){
            setImage("objects/door_opened.png");
        }else{
            setImage("objects/door_locked.png");
        }
    }
    
    public void unlock(){
        unlocked = true;
    }
    
    public boolean isUnlocked(){
        return unlocked;
    }
}