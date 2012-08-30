package particledemo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import particledemo.particle.Particle;
import particledemo.vector2d.Vector2D;

/**
 * The panel used to draw the particles to the screen
 * and handle user input.
 * @author Darin Beaudreau
 */
public class ParticlePanel extends javax.swing.JPanel implements Runnable {
    // Static variables.
    private static final Dimension DEFAULT_SIZE = new Dimension(640,480);
    private static final Point.Double ORIGIN = new Point.Double((DEFAULT_SIZE.width / 2), (DEFAULT_SIZE.height / 2));
    private static final int SLEEP_TIME = 20; // Time in ms.
    
    private static final int PARTICLE_DENSITY = 20; // Particles per sleep cycle.
    private static final int PARTICLE_OFFSET_X_MIN = 5;
    private static final int PARTICLE_OFFSET_X_MAX = 30;
    private static final int PARTICLE_OFFSET_Y_MIN = 5;
    private static final int PARTICLE_OFFSET_Y_MAX = 30;
    
    private static final int DEFAULT_PARTICLE_SIZE = 4;
    // Member variables.
    private Thread animate;
    private boolean running;
    
    private boolean[] keys;
    
    private Point mouse;
    
    private ArrayList<Particle> particles;
    private ArrayList<Particle> firedParticles;
    
    /**
     * Creates new form ParticlePanel
     */
    public ParticlePanel() {
        keys = new boolean[2];
        keys[0] = false;
        keys[1] = false;
        
        mouse = new Point();
        
        particles = new ArrayList<>();
        firedParticles = new ArrayList<>();
        
        setBackground(Color.WHITE);
        setPreferredSize(DEFAULT_SIZE);
        setFocusable(true);
        requestFocus();
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent m) {
                mouse.x = m.getX();
                mouse.y = m.getY();
            }
            @Override
            public void mouseDragged(MouseEvent m) {
                mouse.x = m.getX();
                mouse.y = m.getY();
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent m) {
                int button = m.getButton();
                if(button == MouseEvent.BUTTON1) keys[0] = true;
                if(button == MouseEvent.BUTTON2) keys[1] = true;
            }
            @Override
            public void mouseReleased(MouseEvent m) {
                int button = m.getButton();
                if(button == MouseEvent.BUTTON1) keys[0] = false;
                if(button == MouseEvent.BUTTON2) keys[1] = false;
            }
        });
        
        initComponents();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        
        // Draw the particles to the screen.
        g2d.setPaint(Color.RED);
        for(Particle p : firedParticles) {
            g2d.fill(p);
        }
        
        // Draw a crosshair on the screen.
        g2d.setPaint(Color.BLACK);
        g2d.drawLine((w / 2), 0, (w / 2), h);
        g2d.drawLine(0, (h / 2), w, (h / 2));
    }
    
    public void update() {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        
        if(!particles.isEmpty()) {
            Particle p = particles.remove(0);
            firedParticles.add(p);
        } else {
            if(keys[0]) createParticles();
        }
        
        if(!firedParticles.isEmpty()) {
            for(int i = 0;i < firedParticles.size();i++) {
                Particle p = firedParticles.get(i);
                if(((p.x + (DEFAULT_PARTICLE_SIZE / 2)) == p.getTarget().x)&&((p.y + (DEFAULT_PARTICLE_SIZE / 2)) == p.getTarget().y)) {
                    firedParticles.remove(i);
                } else if(((p.x + (DEFAULT_PARTICLE_SIZE / 2)) < 0)||((p.y + (DEFAULT_PARTICLE_SIZE / 2)) < 0)||((p.x + (DEFAULT_PARTICLE_SIZE / 2)) > w)||((p.y + (DEFAULT_PARTICLE_SIZE / 2)) > h)) {
                    firedParticles.remove(i);
                } else {
                    Vector2D v = new Vector2D((p.getTarget().x - (p.x + (DEFAULT_PARTICLE_SIZE / 2))), (p.getTarget().y - (p.y + (DEFAULT_PARTICLE_SIZE / 2))));
                    Vector2D n = v.normalize();
                    if(n != null) {
                        p.x += n.x;
                        p.y += n.y;
                    } else {
                        firedParticles.remove(i);
                    }
                }
            }
        }
    }
    
    /**
     * Create a number of particles equal to the
     * PARTICLE_DENSITY constant.
     * @see PARTICLE_DENSITY
     **/
    public void createParticles() {
        for(int i = 0;i < PARTICLE_DENSITY;i++) {
            Point target = new Point(mouse.x, mouse.y);
            //System.out.println("New target: (" + target.x + "," + target.y + ")");
            
            // The X offset from the mouse position.
            int x_offset = PARTICLE_OFFSET_X_MIN + (int)(Math.random()*((PARTICLE_OFFSET_X_MAX - PARTICLE_OFFSET_X_MIN) + 1));
            //System.out.println("X Offset: " + x_offset);
            // The Y offset from the mouse position.
            int y_offset = PARTICLE_OFFSET_Y_MIN + (int)(Math.random()*((PARTICLE_OFFSET_Y_MAX - PARTICLE_OFFSET_Y_MIN) + 1));
            //System.out.println("Y Offset: " + y_offset);
            
            target.x += (x_offset * ((((int)(Math.random()*2 + 1)) == 1)?-1:1));
            target.y += (y_offset * ((((int)(Math.random()*2 + 1)) == 1)?-1:1));
            
            //System.out.println("New target after offset: (" + target.x + "," + target.y + ")");
            
            // Create the new particle and add it to the list.
            Particle p = new Particle((ORIGIN.x - (DEFAULT_PARTICLE_SIZE / 2)), 
                                      (ORIGIN.y - (DEFAULT_PARTICLE_SIZE / 2)), 
                                       DEFAULT_PARTICLE_SIZE, 
                                       DEFAULT_PARTICLE_SIZE);
            p.setTarget(target);
            particles.add(p);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void start() {
        if((animate == null)||(!running)) {
            animate = new Thread(this);
            animate.start();
        }
    }
    
    @Override
    public void run() {
        running = true;
        while(running) {
            update();
            repaint();
            
            try {
                Thread.sleep(SLEEP_TIME);
            } catch(InterruptedException e) {}
        }
        System.exit(0);
    }
    
    public void stop() {
        running = false;
    }
}
