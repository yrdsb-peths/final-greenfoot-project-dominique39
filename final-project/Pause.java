import greenfoot.*;
import java.util.*;

public class Pause extends WorldwCursor
{
    World returnWorld = null;
    List<Actor> allObjects;

    public Pause(World lastWorld)
    {
        super(lastWorld.getBackground());
        for(int x = 0; x < lastWorld.getWidth(); x++){
            for(int y = 0; y < lastWorld.getHeight(); y++){
                if(allObjects != null){
                    allObjects.clear();
                }
                
                allObjects = lastWorld.getObjectsAt(x,y,null);
                for(int n = 0; n < allObjects.size(); n++){
                    addObject(allObjects.get(n),x,y);
                }
            }
        }

        returnWorld = lastWorld;
    }
}