import greenfoot.*;
import java.util.*;

public class Player extends Obstructables
{
    //move
    private String keyLeft;
    private String keyRight;
    private String keyUp;

    //jump
    private boolean canJump;
    private int jumpHeight = 19;

    //fall
    private int fallIndex = 0;

    //animate
    public int facing;
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
    }

    public void act()
    {
        moveY();
        moveX();
        touchKey();
    }

    private void moveX(){
        int playerWidth = getImage().getWidth();
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

        setLocation(getX()+(moveDirection*4), getY());
        if(getX() <= playerWidth/2 || getX() >= 1500-playerWidth/2){
            setLocation(getX()+(-moveDirection*4), getY());
        }

        Actor intersectObj = getOneIntersectingObject(Obstructables.class);
        if(intersectObj != null){
            setLocation(getX()-moveDirection*4, getY());
        }
    }

    private void moveY(){
        int playerHeight = getImage().getHeight();

        canJump = false;
        if(getY() < playerHeight/2+900){
            setLocation(getX(), getY()+fallIndex);
        }else{
            setLocation(getX()-200, -playerHeight/2);
        }

        if(getOneIntersectingObject(Obstructables.class) != null){
            setLocation(getX(), getY()-fallIndex);
            canJump = true;
            fallIndex = 0;
        }

        //gradual falling with limits
        if(fallIndex < 40){
            fallIndex++;
        }

        if(Greenfoot.isKeyDown(keyUp) && canJump){
            fallIndex = -jumpHeight;
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

    private String collideDirection(){
        Actor collidingObjs = getOneIntersectingObject(Obstructables.class);
        if(collidingObjs != null){
            if(getX() > collidingObjs.getX()){
                return "Left";
            }
            if(getX() < collidingObjs.getX()){
                return "Right";
            }
        }
        return "";
    }

    private void touchKey(){
        if(isTouching(Key.class)){
            Level world = (Level) getWorld();
            world.keyFollow(this);
        }
    }
}