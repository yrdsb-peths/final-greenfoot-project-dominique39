import greenfoot.*;

public class Door extends GameObjects
{
    private boolean unlocked = false;

    /**
     * Method act
     *
     */
    public void act()
    {
        if(unlocked){
            setImage("objects/door_opened.png");
        }else{
            setImage("objects/door_locked.png");
        }
    }

    /**
     * unlock the door
     *
     */
    public void unlock(){
        unlocked = true;
    }

    /**
     * return if the door is locked
     *
     * @return if the door is locked
     */
    public boolean isUnlocked(){
        return unlocked;
    }
}