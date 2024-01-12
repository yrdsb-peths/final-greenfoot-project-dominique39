import greenfoot.*;

public class Door extends GameObjects
{
    private boolean unlocked = false;

    public void act()
    {
        if(unlocked){
            setImage("objects/door_opened.png");
        }else{
            setImage("objects/door_locked.png");
        }
    }

    public void unlock(){
        unlocked = true;
    }

    public boolean isUnlocked(){
        return unlocked;
    }
}