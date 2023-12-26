import greenfoot.*;
import java.util.*;

public class ScreenMover extends Actor
{
    public void act()
    {
        List<Player> players = getObjectsInRange(10000, Player.class);
        int totalX = 0;
        for(int i = 0; i < players.size(); i++){
            totalX+= players.get(i).getX();
        }
        if(players.size() != 0){
            setLocation(totalX/players.size(), getY());
        }
    }
}