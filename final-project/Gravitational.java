import greenfoot.*;
import java.util.*;

public class Gravitational extends GameObjects
{
    Level inWorld;
    int objWidth;
    int objHeight;

    int moveDirection;

    int jumpHeight = 0;
    int fallIndex = 0;

    public Gravitational(){
        objWidth = getImage().getWidth()+28;
        objHeight = getImage().getHeight();
    }

    public void act(){
        inWorld = (Level) getWorld();

        if(getIntersectingObjects(PressurePlate.class).size() > 0){
            inWorld.pressPlate(getIntersectingObjects(PressurePlate.class).get(0));
        }

        fall();
    }

    void fall(){
        if(fallIndex < 40){
            fallIndex++;
        }
        setLocation(getX(), getY()+fallIndex);

        collisionY(Floor.class);
        collisionY(Gravitational.class);
    }

    void collisionY(Class c){
        List<? extends Actor> list = getIntersectingObjects(c);
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                Actor temp = list.get(i);
                if(getY() < temp.getY()){ //this.y lower than other.y
                    setLocation(getX(), getY()-fallIndex);
                    fallIndex = 0;
                }
            }
        }
    }
}