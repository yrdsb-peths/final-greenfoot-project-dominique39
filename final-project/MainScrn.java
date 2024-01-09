import greenfoot.*;
public class MainScrn extends WorldwCursor
{
    private Button startBut;
    private Label title;

    public MainScrn()
    {
        super();
        title = new Label("title.png");
        addObject(title, (getWidth()/2),((getHeight()-600)));
        
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