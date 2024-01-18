import greenfoot.*;
import java.util.*;

public class LvSelection extends WorldwCursor
{
    private Button[] buttons = new Button[5];
    private List<Label> passedIndication = new ArrayList(0);
    private static boolean[] passed = {false, false, false, false, false};

    public LvSelection(){    
        super();
        setPaintOrder(Button.class);

        buttons[0] = new Button(toLv1,"lv1");
        buttons[1] = new Button(toLv2,"lv2");
        buttons[2] = new Button(toLv3,"lv3");
        buttons[3] = new Button(toLv4,"lv4");
        buttons[4] = new Button(toLv5,"lv5");

        for(int i = 0; i < 5; i++){
            addObject(buttons[i], 100+(getWidth()-200)/6*(i+1), getHeight()/2);
            //add passed background to passed levels
            if(passed[i]){
                passedIndication.add(new Label("passed.png"));
                addObject(passedIndication.get(passedIndication.size()-1),100+(getWidth()-200)/6*(i+1), getHeight()/2);
            }
        }
    }

    public static void passed(int lv){
        passed[lv-1] = true;
    }

    private void toWorld(int lv){
        Greenfoot.setWorld(new Transition(lv));
    }

    private Clickable toLv1 = () -> toWorld(1);
    private Clickable toLv2 = () -> toWorld(2);
    private Clickable toLv3 = () -> toWorld(3);
    private Clickable toLv4 = () -> toWorld(4);
    private Clickable toLv5 = () -> toWorld(5);
}