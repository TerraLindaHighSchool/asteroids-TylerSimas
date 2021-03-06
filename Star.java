import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @description Stars for the background
 * 
 * @author Tyler S 
 * @version 2.0
 */
public class Star extends Actor
{
    private int x, y, speed, size;
    private Color color;
    
    public Star(int speed, Color color, int worldWidth, int worldHeight)
    {
      size = 2 + Greenfoot.getRandomNumber(1);
      GreenfootImage image = new GreenfootImage (size, size);
      image.setColor (color);
      image.fillOval (0, 0, size, size); 
      setImage(image);
      this.speed = speed;
      
      x = Greenfoot.getRandomNumber(worldWidth);
      y = Greenfoot.getRandomNumber(worldHeight);
    
   }

   public void move()
   {
       x = speed + x;
       setLocation(x + speed, y);
       if(x < 1)
       {
          x = getWorld().getWidth();
       }
       
   }
   
   public  int getX()
   {
       return x;
   }
   
   public  int getY()
   {
       return y;
   }    
}
