import greenfoot.*;

public class Key extends NonObstructables
{
    private boolean follow;
    private Player player;

    public Key(){
        GreenfootImage img = new GreenfootImage("objects/key.png");
        img.scale(50, 90);
        setImage(img);
    }

    public void act(){
        if(follow){
            if(player.facing == 1){
                setLocation(player.getX()-70, player.getY()-50);
            }else{
                setLocation(player.getX()+70, player.getY()-50);
            }
        }
    }

    public void setFollow(boolean r){
        follow = r;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}