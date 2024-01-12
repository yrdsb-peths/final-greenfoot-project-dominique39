import greenfoot.*;
import java.util.*;

public class Block extends Gravitational
{
    public Block(){
        GreenfootImage img = new GreenfootImage("objects/block.png");
        setImage(img);
    }

    public void act(){
        collisionX(Player.class, false);
        collisionX(Floor.class, false);
        collisionX(Block.class, true);   
    }

    void collisionX(Class c, boolean willPush){
        for(int i = -1; i < 2; i += 2){
            Actor temp = getOneObjectAtOffset(objWidth+i+2, 0, c);
            if(temp != null){
                if(willPush){
                    setLocation(getX()-i*2, getY());
                    temp.setLocation(temp.getX()+i*2, temp.getY());
                }else{
                    setLocation(getX()-i*2, getY());
                }
            }
        }
    }
}