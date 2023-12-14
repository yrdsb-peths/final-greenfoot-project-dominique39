import greenfoot.*;

public class WorldwCursor extends World
{
    Cursor cursor;

    public WorldwCursor()
    {    
        super(1000, 900, 1, false);
        cursor = new Cursor();
        addObject(cursor,0,0);
    }
    
    public WorldwCursor(GreenfootImage bg)
    {    
        super(1000, 900, 1, false);
        cursor = new Cursor();
        addObject(cursor,0,0);
        setBackground(bg);
    }
}