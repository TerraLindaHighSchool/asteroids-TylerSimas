import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @description Aliens that will shoot at the player.
 * 
 * @author Tyler S 
 * @version 2.0
 */
public class Alien extends SmoothMover
{
    private static final int alienReloadTime = 300;
    
    private int alienReloadDelay = 0;
    
    public Alien()
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 0.5f));
    }
    
    /**
     * Act - do whatever the Alien wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        turnToRocket();
        alienReloadDelay++;
        fire();
    }    
    
    public void turnToRocket()
    {
        Rocket rocketClass = new Rocket();
        if(rocketClass.getDead() != true)
        {
            Actor rocket = (Actor)(getWorld().getObjects(Rocket.class).get(0));
            turnTowards(rocket.getX(), rocket.getY());
        }
        else
        {
            turn(1);
        }
    }
    
     /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (alienReloadDelay >= alienReloadTime) 
        {
            AlienBullet alienBullet = new AlienBullet (getVelocity(), getRotation());
            getWorld().addObject (alienBullet, getX(), getY());
            alienBullet.move ();
            alienReloadDelay = 0;
        }
    }
    
    public void alienHit()
    {
        Space space = (Space ) getWorld();
        space.addObject(new Explosion(),getX(),getY());
        getWorld().removeObject(this);
    }
}
