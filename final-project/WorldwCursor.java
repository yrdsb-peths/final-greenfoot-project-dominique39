import greenfoot.*;

public class WorldwCursor extends World
{
    Cursor cursor;

    /**
     * Create a world where cursor is needed
     *
     */
    public WorldwCursor()
    {
        super(1000, 900, 1, false);
        cursor = new Cursor();
        addObject(cursor,0,0);
    }
}