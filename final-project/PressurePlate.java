import greenfoot.*;

public class PressurePlate extends NonObstructables
{
    private boolean stepped;
    private Floor moveObj;
    private String moveDirection;
    
    public void act()
    {
        if(stepped){
            setImage("objects/plate_stepped.png");
        }else{
            setImage("objects/plate.png");
        }
    }
    
    public void stepped(){
        stepped = true;
    }
    
    public boolean isStepped(){
        return stepped;
    }
}