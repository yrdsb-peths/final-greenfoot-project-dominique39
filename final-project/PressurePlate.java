import greenfoot.*;

public class PressurePlate extends NonObstructables
{
    private boolean stepped;
    
    public void act()
    {
        if(stepped){
            //setImage();
        }else{
            //setImage();
        }
    }
    
    public void stepped(){
        stepped = true;
    }
    
    public boolean isStepped(){
        return stepped;
    }
}