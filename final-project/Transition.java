import greenfoot.*;

public class Transition extends World
{
    private Label label;
    private int lv;
    
    public Transition(int lv)
    {    
        super(1000, 900, 1, false);
        this.lv = lv;
        label = new Label("images/buttons/lv"+lv+".png");
        addObject(label, getWidth()/2, getHeight()/2);
    }

    public void act(){
        Greenfoot.delay(30);
        
        label = null;
        Greenfoot.setWorld(new Level(lv));
    }
}