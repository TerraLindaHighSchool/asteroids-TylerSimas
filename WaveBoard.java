import greenfoot.*;

/**
 * The WaveBoard is used to display results on the screen. It can display some
 * text and the wave the player was on.
 * 
 * @author Tyler S
 * @version 2.0
 */
public class WaveBoard extends Actor
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    /**
     * Create a score board with dummy result for testing.
     */
    public WaveBoard()
    {
        this(100);
    }

    /**
     * Create a score board for the final result.
     */
    public WaveBoard(int wave)
    {
        makeImage("Game Over", "Wave: ", wave);
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title, String prefix, int wave)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(prefix + wave, 60, 200);
        setImage(image);
    }
}
