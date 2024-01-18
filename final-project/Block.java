import greenfoot.*;
import java.util.*;

public class Block extends Gravitational
{
    int origX;
    int origY;

    /**
     * Creates a block
     *
     * @param origX the original X location of the block
     * @param origY the original Y location of the block
     */
    public Block(int origX, int origY){
        GreenfootImage img = new GreenfootImage("objects/block.png");
        setImage(img);
        this.origX = origX;
        this.origY = origY;
        pushable = true;
    }

    /**
     * horizontal movement and collisions; check pressing pressureplate from superclass act method; vertical movement from the superclass act method
     *
     */
    public void act(){
        collisionX(Player.class);
        collisionX(Floor.class);
        collisionX(Block.class);
        
        super.act();
        if(getY() > inWorld.getHeight()){
            setLocation(origX, 0);
        }
    }

    //the horizontal collision
    private void collisionX(Class c){
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