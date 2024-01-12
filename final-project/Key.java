import greenfoot.*;

public class Key extends GameObjects
{
    private boolean obtained;
    private Player player;

    public Key(){
        setImage("objects/key.png");
    }

    public void act(){
        if(obtained){
            if(player.getFacing() == 1){
                setLocation(player.getX()-70, player.getY()-50);
            }else{
                setLocation(player.getX()+70, player.getY()-50);
            }
        }
    }

    public void obtained(){
        obtained = true;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    
    public Player getPlayer(){
        return player;
    }
}