import greenfoot.*;

public class Instructions extends WorldwCursor
{
    Label instructions;

    public Instructions()
    {
        instructions = new Label("texts/instructions.png");
        addObject(instructions, getWidth()/2, getHeight()/2);
    }

    public void act(){
        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new MainScrn());
        }
    }
}