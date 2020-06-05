import greenfoot.*;

/**
 * @description Space. Something for rockets to fly in.
 * 
 * @author Tyler S
 * @version 2.0
 */
public class Space extends World
{
    private Counter waveCounter;
    private Counter enemyCounter;
    public  int totalWaves = 1;
    private int startAsteroids = 1;
    
    private Star[] stars = new Star[210];
    Color color1 = new Color(255,247,247);
    Color color2 = new Color(255,245,227);
    Color color3 = new Color(255,235,235);

    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        setPaintOrder(Rocket.class, Bullet.class, AlienBullet.class, Earth.class, Asteroid.class, Star.class);

        star();

        Explosion.initializeImages();
        
        prepare();
        
    }

    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int sizeOfAsteroid = Greenfoot.getRandomNumber(5) * 5 + 50;
            addObject(new Asteroid(sizeOfAsteroid), x, 0);
        }
    }
    
    private void addAliens(int count)
    {
        for(int i = 0; i < count; i++)
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            addObject(new Alien(), x, 0);
        }
    }
    
private void star()
    {
        for(int i = 0; i < 210; i++){
            Star star;
            int deltaSpeed = Greenfoot.getRandomNumber(2);

            if(i < 70)
            {
                star = new Star(-1 - deltaSpeed, color1, getWidth(), getHeight());
                addObject(star, star.getX(), star.getY());
                stars[i] = star;
            }

            if(i >= 70 && i < 140)
            {
                star = new Star(-3 - deltaSpeed, color2, getWidth(), getHeight());
                addObject(star, star.getX(), star.getY());
                stars[i] = star;
            }

            if(i >= 140)
            {
                star = new Star(-5 - deltaSpeed, color3, getWidth(), getHeight());
                addObject(star, star.getX(), star.getY());
                stars[i] = star;
            }
        }
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void paintStars(int count) 
    {
        GreenfootImage background = getBackground();
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            int size = Greenfoot.getRandomNumber(2) + 2;
            int a = Greenfoot.getRandomNumber(155);
            background.setColor(new Color(a + 30, a, a));
            background.fillOval(x, y, size, size);
        }
    }
    
    public void act()
    {
        updateEnemies(getObjects(Asteroid.class).size() + getObjects(Alien.class).size());
        
        if(getObjects(Asteroid.class).size() == 0 && getObjects(Alien.class).size() == 0)
        {
            totalWaves++;
            updateWave(1);
            addAsteroids(totalWaves);
            addAliens(totalWaves / 3);
        }
        
        for(int i = 0; i < 210; i++)
        {
            if(stars[i] != null)
            {
                stars[i].move();
            }
        }
    }

    public void updateWave(int addToWave)
    {
        waveCounter.add(addToWave); 
    }
    
    public void updateEnemies(int enemiesInScene)
    {
        enemyCounter.setValue(enemiesInScene);
    }

    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int currentWave = waveCounter.getValue();
        addObject(new WaveBoard(currentWave),x ,y);
    }
    
    public int getWaves()
    {
        return totalWaves;
    }
    
    private void prepare()
    {
        Earth earth = new Earth();
        addObject(earth,295,260);
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);

        addAsteroids(startAsteroids);
        star();

        waveCounter = new Counter("Wave: ");
        addObject(waveCounter, 100, 480);
        
        enemyCounter = new Counter("Enemies: ");
        addObject(enemyCounter, 500, 480);
        
        updateWave(1);
    }
}