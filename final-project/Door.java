import greenfoot.*;
import java.util.*;

public class Door extends NonObstructables
{
    public boolean opened = false;
    public void act()
    {
        if(opened){
            setImage("objects/door_opened.png");
        }else{
            setImage("objects/door_locked.png");
        }
    }
    
    public void open(){
        opened = true;
    }
}