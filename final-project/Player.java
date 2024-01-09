import greenfoot.*;
import java.util.*;

public class Player extends Obstructables
{
    private int playerWidth;
    private int playerHeight;
    private boolean escaped = false;
    private boolean dead = false;

    //move
    private String keyLeft;
    private String keyRight;
    private String keyUp;
    private int jumpHeight = 19;
    private int fallIndex = 0;

    //animate
    private int facing;
    private int imageIndex = 0;
    private GreenfootImage[] left = new GreenfootImage[4];
    private GreenfootImage[] right = new GreenfootImage[4];
    
    public Player(String name, String keyLeft, String keyRight, String keyUp){
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;

        for(int i = 0; i < 4; i++){
            left[i] = new GreenfootImage("player/" + name + "/tile" + i + ".png");
            right[i] = new GreenfootImage("player/" + name + "/tile" + i + ".png");
            right[i].mirrorHorizontally();
        }

        playerWidth = getImage().getWidth()+30;
        playerHeight = getImage().getHeight()+30;
    }

    public void act()
    {
        Level world = (Level) getWorld();
        moveY();
        moveX();

        //obtain key
        if(isTouching(Key.class)){
            world.obtainKey(this);
        }
        
        //touch laser
        if(isTouching(Laser.class)){
            dead = true;
        }
        
        if(getIntersectingObjects(PressurePlate.class).size() > 0){
            world.pressPlate(getIntersectingObjects(PressurePlate.class).get(0));
        }
        
        //leave level
        if(isTouching(Door.class) && Greenfoot.isKeyDown(keyUp)){
            world.goInDoor(this);
        }
    }

    private void moveX(){
        int moveDirection = 0;
        boolean isBothPressed = (Greenfoot.isKeyDown(keyLeft) == Greenfoot.isKeyDown(keyRight));

        if(isBothPressed){
            //reset animation when not moving
            if(facing < 0){
                setImage(left[1]);
                imageIndex = 0;
            }else{
                setImage(right[1]);
                imageIndex = 0;
            }
        }else{
            if(Greenfoot.isKeyDown(keyLeft)){
                facing = -1;
            }
            if(Greenfoot.isKeyDown(keyRight)){
                facing = 1;
            }
            animate();
            moveDirection = facing;
        }

        //move player accordingly
        setLocation(getX()+(moveDirection*4), getY());
        //revert to original position if character at world edge
        if(getX() <= playerWidth || getX() >= 1000-playerWidth){
            setLocation(getX()-moveDirection*4, getY());
        }

        //collision
        List<Obstructables> r = getIntersectingObjects(Obstructables.class);
        if(r != null){
            for(int i = 0; i < r.size(); i++){
                Actor temp = r.get(i);
                int tempHeight = temp.getImage().getHeight()-5;
                if(getY() >= temp.getY()-tempHeight && getY() <= temp.getY()+tempHeight){ //this.height under other.topEdge && this.height ontop of other.bottomEdge
                    setLocation(getX()-moveDirection*4, getY());
                }
            }
        }
    }

    private void moveY(){
        boolean canJump = false;

        //gradual falling with limits
        if(fallIndex < 40){
            fallIndex++;
        }

        if(getY() > playerHeight/2+900){
            //back to top of the screen
            setLocation(getX()-200, -playerHeight/2);
            if(getX() < playerWidth){
                setLocation(playerWidth, getY());
            }
        }else{
            //fall
            setLocation(getX(), getY()+fallIndex);
        }

        //standing collision
        List<Obstructables> r = getIntersectingObjects(Obstructables.class);
        if(r != null){
            for(int i = 0; i < r.size(); i++){
                Actor temp = r.get(i);
                if(getY() < temp.getY()){ //this.y lower than other.y
                    setLocation(getX(), getY()-fallIndex);
                    canJump = true;
                    fallIndex = 0;
                }else{
                    if(!canJump && fallIndex < 0){
                        fallIndex += fallIndex*-2;
                    }
                    canJump = false;
                }
            }
        }

        //check jumping
        if(Greenfoot.isKeyDown(keyUp) && canJump){
            fallIndex = -jumpHeight;
        }
        
        r = getIntersectingObjects(Obstructables.class);
        if(r != null){
            for(int i = 0; i < r.size(); i++){
                Actor temp = r.get(i);
                if(getY() > temp.getY()){
                    setLocation(getX(), getY()+fallIndex);
                    canJump = false;
                }
            }
        }
    }

    private void animate(){        
        if(imageIndex < 39){
            imageIndex++;
        }else{
            imageIndex = 0;
        }

        if(facing < 0){
            setImage(left[imageIndex/10]);
        }else{
            setImage(right[imageIndex/10]);
        }
    }
    
    public int getFacing(){
        return facing;
    }
    
    public boolean isEscaped(){
        return escaped;
    }
    
    public void escaped(){
        escaped = true;
    }
    
    public boolean isDead(){
        return dead;
    }
}