import greenfoot.*;
public class MainScrn extends WorldwCursor
{
    private Label title;
    private Button startButton;
    private Button instructionButton;
    private Button creditButton;

    /**
     * Create a Main Screen World
     *
     */
    public MainScrn()
    {
        super();
        title = new Label("title.png");
        addObject(title, getWidth()/2, getHeight()/4);
        
        startButton = new Button(startGame,"start");
        addObject(startButton, getWidth()/2, getHeight()/3+(getHeight()/3*2)/4);    
        
        instructionButton = new Button(toInstructions,"instructions");
        addObject(instructionButton, getWidth()/2, getHeight()/3+(getHeight()/3*2)/4*2);
        
        creditButton = new Button(toCredits,"credit");
        addObject(creditButton, getWidth()/2, getHeight()/3+(getHeight()/3*2)/4*3);
    }
    
    private void toWorld(String worldName){
        World toWorld = null;
        switch(worldName){
            case "LvSelection":
                toWorld = new LvSelection();
                break;
            case "Credits":
                toWorld = new Credits();
                break;
            case "Instructions":
                toWorld = new Instructions();
                break;
        }
        Greenfoot.setWorld(toWorld);
    }
    
    private Clickable startGame = () -> toWorld("LvSelection");
    private Clickable toInstructions = () -> toWorld("Instructions");
    private Clickable toCredits = () -> toWorld("Credits");
}