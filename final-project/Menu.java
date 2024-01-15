import greenfoot.*;

public class Menu extends WorldwCursor
{
    private int onLevel;
    private Button[] buttons = new Button[2];
    private Label menuLabel;

    public Menu(int lv){    
        super();
        onLevel = lv;
        menuLabel = new Label("menu.png");
        addObject(menuLabel, getWidth()/2, 100+(getHeight()-200)/(buttons.length+2));
        
        buttons[0] = new Button(restart,"restart");
        buttons[1] = new Button(exit,"exit_level");

        for(int i = 0; i < buttons.length; i++){
            addObject(buttons[i], getWidth()/2, 100+(getHeight()-200)/(buttons.length+2)*(i+2));
        }
    }

    private void toWorld(String world){
        switch(world){
            case "restart":
                Greenfoot.setWorld(new Transition(onLevel));
                break;
            case "exit":
                Greenfoot.setWorld(new LvSelection());
                break;
        }
    }

    private Clickable restart = () -> toWorld("restart");
    private Clickable exit = () -> toWorld("exit");
}