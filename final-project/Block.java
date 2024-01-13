import greenfoot.*;
import java.util.*;

public class Block extends Gravitational
{
    public Block(){
        GreenfootImage img = new GreenfootImage("objects/block.png");
        setImage(img);
        pushable = true;
    }

    public void act(){
        collisionX(Player.class);
        collisionX(Floor.class);
        collisionX(Block.class);   
    }

    void collisionX(Class c){
        for(int i = -1; i < 2; i += 2){
            GameObjects temp = (GameObjects) getOneObjectAtOffset(objWidth+i+2, 0, c);
            if(temp != null){
                if(temp.isPushable()){
                    setLocation(getX()-i*2, getY());
                    temp.setLocation(temp.getX()+i*2, temp.getY());
                }else{
                    setLocation(getX()-i*2, getY());
                    pushable = false;
                }
            }
        }
    }
}