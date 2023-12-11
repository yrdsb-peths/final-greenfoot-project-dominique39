import greenfoot.*;

public class Button extends Actor
{
    private Clickable button;
    private GreenfootImage buttonImage;
    private GreenfootImage hoverImage;
    
    public Button(Clickable button, String imgName){
        this.button = button;
        this.buttonImage = new GreenfootImage("images/buttons/"+imgName+".png");
        this.hoverImage = new GreenfootImage("images/buttons/"+imgName+"_clicked.png");
        setImage(this.buttonImage);
    }
    
    public void act()
    {
        if(this.isTouching(Cursor.class)){
            setImage(hoverImage);
            if(Greenfoot.mousePressed(this)){
                button.clicked();
            }
        }else{
            setImage(buttonImage);
        }
    }
}
