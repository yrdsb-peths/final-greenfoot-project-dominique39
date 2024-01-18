import greenfoot.*;
import java.util.*;

public class Cursor extends Actor
{
    /**
     * let this actor follow the user's cursor
     *
     */
    public void act(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            this.setLocation(mouse.getX(), mouse.getY());
        }
    }
}