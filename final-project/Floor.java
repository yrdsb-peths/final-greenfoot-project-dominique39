import greenfoot.*;

public class Floor extends Obstructables
{
    public Floor(int width, int height){
        GreenfootImage img = new GreenfootImage("objects/floor.png");
        img.scale(width, height);
        setImage(img);
    }
}