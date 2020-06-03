/**
 * Write a description of class Wave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wave  
{
    // instance variables - replace the example below with your own
    private static int waves;

    /**
     * Constructor for objects of class Wave
     */
    public Wave()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void setWaves(int x)
    {
        waves = waves + x;
    }
    
    public int getWaves(int x)
    {
        return waves;
    }
}
