import greenfoot.*;
import java.util.*;

public class LvSelection extends WorldwCursor
{
    private int numLevels = 5;

    private List<Button> lvButtons = new ArrayList(numLevels);

    public LvSelection(){    
        super();

        lvButtons.add(new Button(toLv1,"lv1"));
        lvButtons.add(new Button(toLv2,"lv2"));
        lvButtons.add(new Button(toLv3,"lv3"));
        lvButtons.add(new Button(toLv4,"lv4"));
        lvButtons.add(new Button(toLv5,"lv5"));

        for(int i = 0; i < numLevels; i++){
            addObject(lvButtons.get(i), 100+(getWidth()-200)/(numLevels+1)*(i+1), getHeight()/2);
        }
    }

    private void toLv(int lv){
        lvButtons = null;
        Greenfoot.setWorld(new Transition(lv));
    }

    private Clickable toLv1 = () -> toLv(1);
    private Clickable toLv2 = () -> toLv(2);
    private Clickable toLv3 = () -> toLv(3);
    private Clickable toLv4 = () -> toLv(4);
    private Clickable toLv5 = () -> toLv(5);
}