
import java.awt.*;
import javax.swing.*;
import java.io.File;

public class Obstacle {
    int x, y, width, height;
    private static final Image rockSprite;

    static {
        Image temp = null;
        File f = new File("assets/rock.png");

        System.out.println("Absolute path: " + f.getAbsolutePath());
        System.out.println("Exists? " + f.exists());

        if (f.exists()) {
            temp = new ImageIcon(f.getPath())
                    .getImage()
                    .getScaledInstance(30, 50, Image.SCALE_SMOOTH);
        }

        rockSprite = temp;
    }

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x -= 5;
    }

    public void draw(Graphics g) {
        if (rockSprite != null) {
            g.drawImage(rockSprite, x, y, width, height, null);
        } else {
            g.setColor(Color.MAGENTA);
            g.drawLine(x, y, x + width, y + height);
            g.drawLine(x + width, y, x, y + height);
        }
    }
}

