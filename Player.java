import javax.swing.*;
import java.awt.*;

public class Player {
    private int x = 20;
    private int y = 300;
    private final int width = 50;
    private final int height = 50;
    private final int jumpForce = -20;
    private double velocityY = 0;
    private int gravity = 1;
    private boolean onGround = true;
    private Image[] runFrames;
    private int currentFrame = 0;
    private int frameDelay = 0;
    private boolean isDead = false;

    public Player() {
        runFrames = new Image[5];
        for (int i = 0; i < 2; i++) {
            runFrames[i] = new ImageIcon("assets/run_" + (i + 1) + ".png")
                    .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        for (int i = 0; i < 2; i++) {
            runFrames[i + 2] = new ImageIcon("assets/jump_" + (i + 1) + ".png")
                    .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        runFrames[4] = new ImageIcon("assets/dead_1.png")
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void jump() {
        if (onGround && !isDead) {
            velocityY = jumpForce;
            onGround = false;
        }
    }

    public void die() {
        isDead = true;
        currentFrame = 4;
    }

    public void increaseGravity() {
        gravity += 2;
    }

    public void update() {
        velocityY += gravity;
        y += velocityY;

        if (y >= 300) {
            y = 300;
            velocityY = 0;
            onGround = true;
            gravity = 1;
        }

        if (!isDead) {
            if (!onGround) {
                currentFrame = (velocityY < 0) ? 2 : 3;
            } else {
                if (++frameDelay >= 7) {
                    currentFrame = (currentFrame == 0) ? 1 : 0;
                    frameDelay = 0;
                }
            }
        } else {
            currentFrame = 4;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(runFrames[currentFrame], x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

