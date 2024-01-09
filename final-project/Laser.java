import greenfoot.*;

public class Laser extends NonObstructables
{
    public Laser(int width, int height){
        GreenfootImage img = new GreenfootImage("objects/laser.png");
        img.scale(width, height);
        setImage(img);
    }
}