import greenfoot.*;
import java.util.*;

public class Player extends Gravitational
{
    private boolean escaped = false;
    private boolean dead = false;

    //move
    private String keyLeft;
    private String keyRight;
    private String keyUp;
    private int jumpHeight = 19;
    private boolean canJump;

    //animate
    private int facing;
    private int imageIndex = 0;
    private GreenfootImage[] left = new GreenfootImage[4];
    private GreenfootImage[] right = new GreenfootImage[4];

    public Player(String name, String keyLeft, String keyRight, String keyUp){
        super();
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        pushable = false;

        for(int i = 0; i < 4; i++){
            left[i] = new GreenfootImage("player/" + name + "/tile" + i + ".png");
            right[i] = new GreenfootImage("player/" + name + "/tile" + i + ".png");
            right[i].mirrorHorizontally();
        }
    }

    public void act()
    {
        super.act();
        moveX();

        //obtain key
        if(isTouching(Key.class)){
            inWorld.obtainKey(this);
        }

        //touch laser
        if(isTouching(Laser.class)){
            dead = true;
        }

        //leave level
        if(isTouching(Door.class) && Greenfoot.isKeyDown(keyUp)){
            inWorld.goInDoor(this);
        }
    }

    void moveX(){
        moveDirection = 0;
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
        if(getX() < objWidth || getX() > inWorld.getWidth() - objWidth){
            setLocation(getX()-moveDirection*4, getY());
        }

        collisionX(Player.class, 10, false);
        collisionX(Floor.class, 0, false);
        collisionX(Block.class, 0, true);
    }

    void fall(){
        canJump = false;
        if(fallIndex < 40){
            fallIndex++;
        }
        setLocation(getX(), getY()+fallIndex);

        if(getY() > inWorld.getHeight() + objHeight){
            //back to top of the screen
            setLocation(getX()-300, -objHeight);
            if(getX() < objWidth){
                setLocation(objWidth, getY());
            }
        }

        collisionY(Floor.class, true);
        collisionY(Gravitational.class, true);

        if(Greenfoot.isKeyDown(keyUp) && canJump){
            fallIndex = -jumpHeight;
        }

        collisionY(Floor.class, false);
        collisionY(Gravitational.class, false);
    }

    void collisionX(Class c, int pixelDifference, boolean willPush){
        List<? extends Actor> list = getIntersectingObjects(c);
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                GameObjects temp = (GameObjects) list.get(i);
                int tempHeight = temp.getImage().getHeight()-pixelDifference;
                if(getY() >= temp.getY()-tempHeight && getY() <= temp.getY()+tempHeight){ //this.height under other.topEdge && this.height ontop of other.bottomEdge
                    if(temp.isPushable()){
                        setLocation(getX()-moveDirection*2, getY());
                        temp.setLocation(temp.getX()+moveDirection*2, temp.getY()); 
                    }else{
                        setLocation(getX()-moveDirection*4, getY());
                    }
                }
            }
        }
    }

    private void collisionY(Class c, boolean onGroundCollision){
        List<? extends Actor> list = getIntersectingObjects(c);
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                Actor temp = list.get(i);
                if(onGroundCollision){
                    if(getY() < temp.getY()){ //this.y lower than other.y
                        setLocation(getX(), getY()-fallIndex); //return to last position
                        canJump = true;
                        fallIndex = 0;
                    }else if(fallIndex < 0){
                        canJump = false;
                        fallIndex += fallIndex*-2;
                    }
                }else if(getY() > temp.getY()){
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