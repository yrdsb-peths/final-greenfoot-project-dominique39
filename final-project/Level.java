import greenfoot.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Level extends WorldwCursor
{
    private Button homeBut;
    private ScreenMover scrnMover;
    private Key key;
    private Door door;
    private Door leftBoundary;
    private Label pass_label;
    private ArrayList<Floor> floor = new ArrayList(0);
    private ArrayList<Player> players = new ArrayList(0);
    private ArrayList<Laser> lasers = new ArrayList(0);
    private ArrayList<PressurePlate> pressurePlates = new ArrayList(0);

    private int lv;
    private boolean won = false;
    private boolean error = false;

    public Level(int lv)
    {
        super();
        this.lv = lv;
        setPaintOrder(Player.class);
        setActOrder(Level.class);

        homeBut = new Button(backMainScrn,"home");
        addObject(homeBut,70,70);

        leftBoundary = new Door();
        addObject(leftBoundary,-200, 0);

        scrnMover = new ScreenMover();
        addObject(scrnMover, 0, 0);

        players.add(new Player("p1","a","d","w"));
        players.add(new Player("p2","left","right","up"));

        addObject(players.get(0),getWidth()/10,getHeight()-170);
        addObject(players.get(1),getWidth()/10*2,getHeight()-170);

        //load level
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
            System.out.println("File not loaded; \n    file not found");
            error = true;
        }catch (InputMismatchException e) {
            System.out.println("File not loaded; \n    incorrect format for object, size, or location");
            error = true;
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
            case "laser":
                lasers.add(new Laser());
                addObject(lasers.get(lasers.size()-1),data[0],data[1]);
                break;
            case "pressure_plate":
                pressurePlates.add(new PressurePlate());
                addObject(pressurePlates.get(pressurePlates.size()-1),data[0],data[1]);
                break;
        }
    }

    public void act(){
        if(error){
            leaveWorld("LvSelection");
            return;
        }

        if(!won){
            if(scrnMover.getX() != getWidth()/2){
                List<Obstructables> obstructables = getObjects(Obstructables.class);
                List<NonObstructables> nonObstructables = getObjects(NonObstructables.class);
                int moveConst = 0;
                if(scrnMover.getX() > getWidth()/2){
                    if(door.getX() > getWidth()-100){
                        moveConst = (scrnMover.getX() - getWidth()/2)/10*-1;
                    }
                }else{
                    if(leftBoundary.getX() < -200){
                        moveConst = (getWidth()/2 - scrnMover.getX())/10;
                    }
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

            for(int i = 0; i < players.size(); i++){
                if(players.get(i).isDead()){
                    leaveWorld("Transition");
                }
            }

            if(players.get(0).isEscaped() && players.get(1).isEscaped()){
                pass_label = new Label("level_cleared.png");
                addObject(pass_label, door.getX()-1000, getHeight()/2);
                won = true;
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

    private void leaveWorld(String worldName){
        World toWorld = null;
        clearObjs();
        switch(worldName){
            case "LvSelection":
                toWorld = new LvSelection();
                break;
            case "Transition":
                toWorld = new Transition(lv);
                break;
        }
        Greenfoot.setWorld(toWorld);
    }

    private void clearObjs(){
        homeBut = null;
        scrnMover = null;
        key = null;
        door = null;
        leftBoundary = null;
        pass_label = null;
        players = null;
        floor = null;
        lasers = null;
        pressurePlates = null;
    }

    public void obtainKey(Player player){
        key.setPlayer(player);
        key.obtained();
    }
    
    public void pressPlate(PressurePlate pressurePlate){
        pressurePlate.stepped();
    }

    public void goInDoor(Player player){
        if(door.isUnlocked()){
            removeObject(player);
            player.escaped();
        }else if(key.getPlayer() == player){
            door.unlock();
            removeObject(key);
            removeObject(player);
            player.escaped();
        }
    }

    private Clickable backMainScrn = () -> leaveWorld("LvSelection");
}