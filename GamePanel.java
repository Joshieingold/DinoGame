import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private int score = 0;
    private boolean gameover = false;
    private int obstacleTimer = 0;
    private boolean upPressed = false;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        new Timer(6, this).start();

        player = new Player();
        obstacles = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);

        for (Obstacle obs : obstacles) {
            obs.draw(g);
        }


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (!gameover) {
           g.drawString("Score: " + score, 20, 30);
        }
        else {
            g.drawString("Game Over: Final Score: " + score, 20, 30);
        }

        g.setColor(Color.ORANGE);
        g.drawLine(0, 350, getWidth(), 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (upPressed) player.jump();

        player.update();

        obstacleTimer++;
        if (obstacleTimer > 100) {
            obstacles.add(new Obstacle(350, 300, 30, 50));
            obstacleTimer = 0;
        }

        Iterator<Obstacle> it = obstacles.iterator();
        while (it.hasNext()) {
            Obstacle obs = it.next();
            obs.move();
            if (obs.x + obs.width < 0) it.remove();
        }

        Rectangle playerBounds = player.getBounds();
        for (Obstacle obs : obstacles) {
            if (playerBounds.intersects(obs.getBounds())) {
                System.out.println("Hit!");
                gameover = true;
            }
        }
        if (!gameover) {
            score += 500;
        }
        else {
            player.die();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.increaseGravity();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}

