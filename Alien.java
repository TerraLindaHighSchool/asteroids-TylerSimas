import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alien here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends SmoothMover
{
    private static final int alienReloadTime = 300;
    
    private int alienReloadDelay = 0;
    
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
            AlienBullet alienbullet = new AlienBullet (getVelocity(), getRotation());
            getWorld().addObject (alienbullet, getX(), getY());
            alienbullet.move ();
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
