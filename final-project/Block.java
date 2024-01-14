import greenfoot.*;
import java.util.*;

public class Block extends Gravitational
{
    int origX;
    int origY;

    public Block(int origX, int origY){
        GreenfootImage img = new GreenfootImage("objects/block.png");
        setImage(img);
        this.origX = origX;
        this.origY = origY;
        pushable = true;
    }

    public void act(){
        collisionX(Player.class);
        collisionX(Floor.class);
        collisionX(Block.class);
        
        super.act();
        if(getY() > inWorld.getHeight()){
            setLocation(origX, 0);
        }
    }

    void collisionX(Class c){
        for(int i = -1; i < 2; i += 2){
            GameObjects temp = (GameObjects) getOneObjectAtOffset(objWidth+i+4, 0, c);
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