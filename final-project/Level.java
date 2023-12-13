import greenfoot.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Level extends WorldwCursor
{
    Button homeBut;
    Button pauseBut;
    ScreenMover scrnMover;
    Player p1;
    Player p2;
    Key key;
    ArrayList<Floor> floor = new ArrayList(0);

    public Level(int lv)
    {
        super();
        homeBut = new Button(backMainScrn,"home");
        addObject(homeBut,70,70);

        pauseBut = new Button(pause,"pause");
        addObject(pauseBut,1425,75);
        
        scrnMover = new ScreenMover();
        addObject(scrnMover, 0, 0);

        p1 = new Player("p1","a","d","w");
        addObject(p1,100,730);

        p2 = new Player("p2","left","right","up");
        addObject(p2,200,730);

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

                loadObj(obj,intData);
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
        }
    }
    
    public void act(){
        if(scrnMover.getX() != getWidth()/2){
            List<Actor> allObjs = getObjects(null);
            int moveConst = 0;
            if(scrnMover.getX() > getWidth()/2){
                moveConst = (scrnMover.getX() - getWidth()/2)/100*-1;
            }else{
                moveConst = (getWidth()/2 - scrnMover.getX())/100;
            }
            
            for(int i = 0; i < allObjs.size(); i++){
                    Actor temp = allObjs.get(i);
                    temp.setLocation(temp.getX()+moveConst,temp.getY());
                }
        }
    }

    private void backMainScrn(){
        clearObjs();
        MainScrn world = new MainScrn();
        Greenfoot.setWorld(world);
    }

    private void pause(){
        clearObjs();
        Pause world = new Pause(this);
        Greenfoot.setWorld(world);
    }

    private void clearObjs(){
        homeBut = null;
        pauseBut = null;
        p1 = null;
        p2 = null;
        key = null;
        floor = null;
        scrnMover = null;
    }

    public void keyFollow(Player player){
        key.setPlayer(player);
        key.setFollow(true);
    }

    private Clickable backMainScrn = () -> backMainScrn();
    private Clickable pause = () -> pause();
}