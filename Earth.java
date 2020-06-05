import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @description The Earth, protect it at all costs
 * 
 * @author Tyler S 
 * @version 2.0
 */
public class Earth extends SmoothMover
{
    private static final int respawn = 200;
    private static final int timeToTurn = 20;
    
    private int respawnDelayCount = 0;
    private int turnTime = 0;
    
    private boolean beginCount = false;
    
    public Earth()
    {
        GreenfootImage image = getImage();
        image.scale(100, 100);
        
        Rocket rocket = new Rocket();
        rocket.setDead(false);
    }
    
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
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        Alien alien = (Alien) getOneIntersectingObject(Alien.class);
        
        if( asteroid != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            space.removeObject(asteroid);
            space.removeObject(this);
            space.gameOver();
        }
        
        if( alien != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            space.removeObject(alien);
            space.removeObject(this);
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
                   getWorld().addObject(new Rocket(), getX(), getY());
                   rocket.setDead(false);
                   respawnDelayCount = 0;
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

