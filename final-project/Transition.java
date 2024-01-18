import greenfoot.*;

public class Transition extends World
{
    private Label label;
    private int lv;
    
    /**
     * Creates a Transition World
     *
     * @param lv    specifies the world which will be directed to
     */
    public Transition(int lv)
    {    
        super(1000, 900, 1, false);
        this.lv = lv;
        label = new Label("images/buttons/lv"+lv+".png");
        addObject(label, getWidth()/2, getHeight()/2);
    }

    /**
     * direct to a level after a delay
     *
     */
    public void act(){
        Greenfoot.delay(30);
        Greenfoot.setWorld(new Level(lv));
    }
}