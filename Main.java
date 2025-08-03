import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DinosaurGame");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

