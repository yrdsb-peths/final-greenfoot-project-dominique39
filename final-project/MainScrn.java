import greenfoot.*;
public class MainScrn extends WorldwCursor
{
    private Button startBut;

    public MainScrn()
    {
        super();
        startBut = new Button(toLvSelection,"start");
        addObject(startBut,(getWidth()/2),(300+(getHeight()-600)));
    }
    
    private void nextWorld(){
        startBut = null;
        LvSelection world = new LvSelection();
        Greenfoot.setWorld(world);
    }
    
    private Clickable toLvSelection = () -> nextWorld();
}
