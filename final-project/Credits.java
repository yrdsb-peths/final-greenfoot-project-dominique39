import greenfoot.*;
import java.util.*;

public class Credits extends WorldwCursor
{
    Label credit;

    public Credits()
    {
        credit = new Label("texts/credits.png");
        addObject(credit, getWidth()/2, getHeight()*3/2);
    }

    public void act(){
        if(credit.getY() > getHeight()/3){
            credit.setLocation(credit.getX(), credit.getY()-1);
        }

        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new MainScrn());
        }
    }
}