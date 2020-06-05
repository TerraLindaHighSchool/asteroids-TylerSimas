import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @description A bullet that an alien will fire.
 * 
 * @author Tyler S 
 * @version 2.0
 */
public class AlienBullet extends SmoothMover
{ 
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 90;
    
    /**
     * Create a bullet with given speed and direction of movement.
     */
    public AlienBullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 5));
        GreenfootImage image = getImage();
        image.scale(50, 50);
        Greenfoot.playSound("AlienGun.wav");
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
