import greenfoot.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Level extends WorldwCursor
{
    Button homeBut;
    ScreenMover scrnMover;
    Player p1;
    Player p2;
    Key key;
    Door door;
    Label pass_label;
    ArrayList<Floor> floor = new ArrayList(0);

    private boolean running = true;

    public Level(int lv)
    {
        super();
        setPaintOrder(Player.class);
        setActOrder(Level.class);
        homeBut = new Button(backMainScrn,"home");
        addObject(homeBut,70,70);

        scrnMover = new ScreenMover();
        addObject(scrnMover, 0, 0);

        p1 = new Player("p1","a","d","w");
        addObject(p1,getWidth()/10,getHeight()-170);

        p2 = new Player("p2","left","right","up");
        addObject(p2,getWidth()/10*2,getHeight()-170);

        loadLevel(lv);
    }

    private void loadLevel(int lv){
        try{
            Scanner lvFile = new Scanner(new File("levels/lv"+lv+".txt"));
            while(lvFile.hasNextLine()){
                String obj = lvFile.nextLine();
                String[] strData = lvFile.nextLine().split("/");

                //convert string array to int array
                int[] intData = new int[strData.length];
                for(int i = 0; i < strData.length; i++){
                    intData[i] = Integer.parseInt(strData[i]);
                }

                loadObj(obj,intData); //where/size
            }
            lvFile.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not loaded; \n not existing object");
        }catch (InputMismatchException e) {
            System.out.println("File not loaded; \n wrong object size or location");
        }
    }

    private void loadObj(String obj,int[] data){        
        switch(obj){
            case "key":
                key = new Key();
                addObject(key,data[0],data[1]);
                break;
            case "floor":
                floor.add(new Floor(data[2],data[3]));
                addObject(floor.get(floor.size()-1),data[0],data[1]);
                break;
            case "door":
                door = new Door();
                addObject(door,data[0],data[1]);
                break;
        }
    }

    public void act(){
        if(running){
            if(scrnMover.getX() != getWidth()/2){
                List<Obstructables> obstructables = getObjects(Obstructables.class);
                List<NonObstructables> nonObstructables = getObjects(NonObstructables.class);
                int moveConst = 0;
                if(scrnMover.getX() > getWidth()/2 && door.getX() > getWidth()-100){
                    moveConst = (scrnMover.getX() - getWidth()/2)/10*-1;
                }

                for(int i = 0; i < obstructables.size(); i++){
                    Actor temp = obstructables.get(i);
                    temp.setLocation(temp.getX()+moveConst,temp.getY());
                }
                for(int i = 0; i < nonObstructables.size(); i++){
                    Actor temp = nonObstructables.get(i);
                    temp.setLocation(temp.getX()+moveConst,temp.getY());
                }
            }

            if(p1.isEscaped() && p2.isEscaped()){
                pass_label = new Label("level_cleared");
                addObject(pass_label, door.getX()-1000, getHeight()/2);
                running = false;
            }
        }else{
            if(pass_label.getX() <= getWidth()/2){
                pass_label.setLocation(pass_label.getX()+10,pass_label.getY());
            }else{
                Greenfoot.delay(60*2);
                leaveWorld("LvSelection");
            }
        }
    }

    private void leaveWorld(String toWorld){
        clearObjs();
        if(toWorld.equals("LvSelection")){
            LvSelection world = new LvSelection();
            Greenfoot.setWorld(world);
        }
        if(toWorld.equals("MainScrn")){
            MainScrn world = new MainScrn();
            Greenfoot.setWorld(world);
        }
    }

    private void clearObjs(){
        homeBut = null;
        p1 = null;
        p2 = null;
        key = null;
        floor = null;
        scrnMover = null;
    }

    public void keyFollow(Player player){
        key.setPlayer(player);
        key.obtained();
    }

    public void goInDoor(Player player){
        if(door.opened){
            removeObject(player);
            player.escaped();
        }else if(key.getPlayer() == player){
            door.open();
            removeObject(key);
            removeObject(player);
            player.escaped();
        }
    }

    private Clickable backMainScrn = () -> leaveWorld("MainScrn");
}