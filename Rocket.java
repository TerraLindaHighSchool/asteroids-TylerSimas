import greenfoot.*;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 5;         // The minimum delay between firing the gun.
    
    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private int xPosition;
    private int yPosition;
    
    private GreenfootImage rocket = new GreenfootImage("Orange Spaceship.png");
    private GreenfootImage rocketWithThrust = new GreenfootImage("Orange Spaceship Engines.png");
    
    private static boolean dead = false;

    /**
     * Initialise this rocket.
     */
    public Rocket()
    {
        reloadDelayCount = 5;
    }
    
    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        move();
        checkKeys();
        checkCollision();
        reloadDelayCount++;
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
        
        if (Greenfoot.isKeyDown("left")) 
        {
            turn(-5);
        }
        
        if (Greenfoot.isKeyDown("right")) 
        {
            turn(5);
        }
        
        ignite(Greenfoot.isKeyDown("up"));
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
    private void ignite(boolean boosterOn)
    {
        if(boosterOn)
        {
            setImage(rocketWithThrust);
            addToVelocity(new Vector((getRotation()), 0.05));
            move();
        }
        else
        {
            setImage(rocket);
        }
    }
    
    private void checkCollision()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        Alien alien = (Alien) getOneIntersectingObject(Alien.class);
        AlienBullet alienbullet = (AlienBullet) getOneIntersectingObject(AlienBullet.class);
        
        if( asteroid != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            setDead(true);
            space.removeObject(asteroid);
            space.removeObject(this);
        }
        
        if( alien != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            setDead(true);
            space.removeObject(alien);
            space.removeObject(this);
        }
        
        if( alienbullet != null)
        {
            Space space = (Space ) getWorld();
            space.addObject(new Explosion(),getX(),getY());
            setDead(true);
            space.removeObject(alienbullet);
            space.removeObject(this);
        }
    }
    
    public static boolean getDead()
    {
        return dead;
    }
    
    public void setDead(boolean dead)
    {
        this.dead = dead;
    }

} 
