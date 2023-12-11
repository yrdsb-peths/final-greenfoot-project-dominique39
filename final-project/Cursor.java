import greenfoot.*;
import java.util.*;

public class Cursor extends Actor
{
    public void act(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            this.setLocation(mouse.getX(), mouse.getY());
        }
    }
}
