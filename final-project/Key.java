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
            if(player.getFacing() == 1){
                setLocation(player.getX()-70, player.getY()-50);
            }else{
                setLocation(player.getX()+70, player.getY()-50);
            }
        }
    }

    public void obtained(){
        follow = true;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    
    public Player getPlayer(){
        return player;
    }
}