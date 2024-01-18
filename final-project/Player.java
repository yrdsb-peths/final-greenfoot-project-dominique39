import greenfoot.*;
import java.util.*;

public class Player extends Gravitational
{
    private boolean escaped = false;
    private boolean dead = false;
    private boolean cheating = false;

    //move
    private String keyLeft;
    private String keyRight;
    private String keyUp;
    private int moveSpeed = 4;
    private int jumpHeight = 19;
    private boolean canJump;

    //animate
    private int facing;
    private int imageIndex = 0;
    private GreenfootImage[] left = new GreenfootImage[4];
    private GreenfootImage[] right = new GreenfootImage[4];

    /**
     * Create a new player
     *
     * @param name the name of the player
     * @param keyLeft the key that move the player to the left
     * @param keyRight the key that move the player to the right
     * @param keyUp the key that move the player up & let the player enter the door
     */
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

    /**
     * check if pressure plate is stepped from the superclass; horizontal movement & collision; vertical movement & collision from the superclass but overrided; check if player obtains key; check if player enter the door; check if player dies by touching the laser
     *
     */
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
            new GreenfootSound("fail.wav").play();
            dead = true;
        }

        //leave level
        if(isTouching(Door.class) && Greenfoot.isKeyDown(keyUp)){
            inWorld.goInDoor(this);
        }
    }

    //horizontal movement
    private void moveX(){
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
        setLocation(getX()+(moveDirection*moveSpeed), getY());
        //revert to original position if character at world edge
        if(getX() < objWidth || getX() > inWorld.getWidth() - objWidth){
            setLocation(getX()-moveDirection*moveSpeed, getY());
        }

        //collision & whether the class is pushable by the player
        collisionX(Player.class, 10, false);
        collisionX(Floor.class, 0, false);
        collisionX(Block.class, 0, true);
    }

    //override the fall method in the superclass
    private void fall(){
        canJump = false;
        //fall
        if(fallIndex < 40){
            fallIndex++;
        }
        setLocation(getX(), getY()+fallIndex);

        //return to the top of the screen if player fell out the world
        if(getY() > inWorld.getHeight() + objHeight){
            setLocation(getX()-300, -objHeight);
            if(getX() < objWidth){
                setLocation(objWidth, getY());
            }
        }

        //onground collision
        collisionY(Floor.class, true);
        collisionY(Gravitational.class, true);

        //jumping
        if(Greenfoot.isKeyDown(keyUp) && canJump){
            fallIndex = -jumpHeight;
        }

        //head hitting collision
        collisionY(Floor.class, false);
        collisionY(Gravitational.class, false);
    }

    //horizontal collision
    private void collisionX(Class c, int pixelDifference, boolean willPush){
        List<? extends Actor> list = getIntersectingObjects(c);
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                GameObjects temp = (GameObjects) list.get(i);
                int tempHeight = temp.getImage().getHeight()-pixelDifference;
                if(getY() >= temp.getY()-tempHeight && getY() <= temp.getY()+tempHeight){ //this.height under other.topEdge && this.height ontop of other.bottomEdge
                    if(temp.isPushable()){
                        setLocation(getX()-moveDirection*4, getY());
                        temp.setLocation(temp.getX()+moveDirection*2, temp.getY()); 
                    }else{
                        setLocation(getX()-moveDirection*4, getY());
                    }
                }
            }
        }
    }

    //override the vertical collision in the superclass
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

    /**
     * return which side is the player facing
     *
     * @return the side is the player is facing
     */
    public int getFacing(){
        return facing;
    }

    /**
     * return if the player has escaped
     *
     * @return if the player has escaped
     */
    public boolean isEscaped(){
        return escaped;
    }

    /**
     * set the player as 'escaped'
     *
     */
    public void escaped(){
        escaped = true;
    }

    /**
     * return if the player is dead
     *
     * @return if the player is dead
     */
    public boolean isDead(){
        return dead;
    }
}