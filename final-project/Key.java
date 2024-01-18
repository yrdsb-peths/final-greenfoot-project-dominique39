import greenfoot.*;

public class Key extends GameObjects
{
    private boolean obtained;
    private Player player;

    /**
     * Create a key
     *
     */
    public Key(){
        setImage("objects/key.png");
    }

    /**
     * make key follows player with the correct direction
     *
     */
    public void act(){
        if(obtained){
            if(player.getFacing() == 1){
                setLocation(player.getX()-70, player.getY()-50);
            }else{
                setLocation(player.getX()+70, player.getY()-50);
            }
        }
    }

    /**
     * set key to be obtained
     *
     */
    public void obtained(){
        obtained = true;
    }

    /**
     * set which player is holding the key
     *
     * @param player the player holding the key
     */
    public void setPlayer(Player player){
        this.player = player;
    }
    
    /**
     * returns the player holding the key
     *
     * @return the player holding the key
     */
    public Player getPlayer(){
        return player;
    }
}