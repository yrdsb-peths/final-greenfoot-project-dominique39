import greenfoot.*;
import java.util.*;

public class Credits extends WorldwCursor
{
    Label credit;

    /**
     * Creates a credit world
     *
     */
    public Credits()
    {
        credit = new Label("texts/credits.png");
        addObject(credit, getWidth()/2, getHeight()*3/2);
        
        addObject(new Label("credit_ffw.png"),getWidth()/2, 20);
    }

    /**
     * make text move; check for leave world condition
     * 
     */
    public void act(){
        if(credit.getY() > getHeight()/3){
            if(Greenfoot.isKeyDown("`")){
                credit.setLocation(credit.getX(), credit.getY()-4);
            }else{
                credit.setLocation(credit.getX(), credit.getY()-1);
            }
        }

        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new MainScrn());
        }
    }
}