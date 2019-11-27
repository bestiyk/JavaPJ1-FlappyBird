package logic;
/**
 * @author Matyas Dedek
 * @version 0.1
 */
import javax.swing.JFrame;
import assets.Keyboard;

public class Run {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
