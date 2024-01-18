import greenfoot.*;

public class Button extends Actor
{
    private Clickable button;
    private GreenfootImage buttonImage;
    private GreenfootImage hoverImage;
    
    /**
     * Creates a button
     *
     * @param button a clickable
     * @param imgName the image name of the button
     */
    public Button(Clickable button, String imgName){
        this.button = button;
        this.buttonImage = new GreenfootImage("images/buttons/"+imgName+".png");
        this.hoverImage = new GreenfootImage("images/buttons/"+imgName+"_clicked.png");
        setImage(this.buttonImage);
    }
    
    /**
     * button animation; also triggers the clickable interface when the button is pressed
     *
     */
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