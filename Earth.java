import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Earth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Earth extends SmoothMover
{
    private static final int respawn = 200;
    private static final int timeToTurn = 20;
    
    private int respawnDelayCount = 0;
    private int turnTime = 0;
    
    private boolean beginCount = false;
    
    /**
     * Act - do whatever the Earth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        checkCollision();
        respawn();
        respawnTimer();
        turnTime++;
        turnRandomly();
    }  
   
    private void checkCollision()
    {
        if( getOneIntersectingObject(Asteroid.class) != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            getWorld().removeObject(this);
            space.gameOver();
        }
        
    }
    
    public void respawn()
    {
        Rocket rocket = new Rocket();
        if(rocket.getDead())
        {
               beginCount = true;
                   if(respawnDelayCount >= respawn)
               {
                   getWorld().addObject(new Rocket(), 295, 260);
                   rocket.setDead(false);
                   beginCount = false;
                }
            }
    }
     
    /**
     * This method increases the respawn timer at a reasonable rate,
     * and only when the "beginCount" boolean has been set to true.
     */
    private void respawnTimer()
    {
        if(beginCount)
        {
            respawnDelayCount++;
        }
    }
    
    private void turnRandomly()
    {
        if(turnTime == timeToTurn)
        {
            turn(Greenfoot.getRandomNumber(10));
        }
    }
}
