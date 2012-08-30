package particledemo;

import javax.swing.JFrame;

/**
 * A demo of particle effects and advanced graphics.
 * @author Darin Beaudreau
 **/
public class ParticleDemo extends JFrame {
    // Static variables.
    private static final float VERSION = 1.0f;

    /**
     * @param args the command line arguments
     **/
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ParticlePanel panel = new ParticlePanel();
        frame.setTitle("Particle Demo v" + VERSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
