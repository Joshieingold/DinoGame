import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingSquare extends JPanel implements KeyListener, ActionListener {
    private int x = 10;
    private int y = 400 - 125;
    private int speed = 10;

    public MovingSquare() {
        Timer timer = new Timer(16, this); // roughly 60 FPS
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            x -= speed;
        } else if (key == KeyEvent.VK_RIGHT) {
            x += speed;
        } else if (key == KeyEvent.VK_UP) {
            y -= speed;
        } else if (key == KeyEvent.VK_DOWN) {
            y += speed;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Square");
        MovingSquare square = new MovingSquare();
        frame.add(square);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // <- fixed typo here
    }
}

