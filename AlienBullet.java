import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AlienBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlienBullet extends SmoothMover
{ 
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 300;
    
    /**
     * Create a bullet with given speed and direction of movement.
     */
    public AlienBullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 1));
        GreenfootImage image = getImage();
        image.scale(50, 50);
        Greenfoot.playSound("EnergyGun.wav");
    }
    
    /**
     * Act - do whatever the AlienBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            life--;
            move();
        }
    }    
}
