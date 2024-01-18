import greenfoot.*;

public class Instructions extends WorldwCursor
{
    Label instructions;

    /**
     * Creates a world with game instructions
     *
     */
    public Instructions()
    {
        instructions = new Label("texts/instructions.png");
        addObject(instructions, getWidth()/2, getHeight()/2);
    }

    /**
     * checks if the leave world condition is met
     *
     */
    public void act(){
        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new MainScrn());
        }
    }
}