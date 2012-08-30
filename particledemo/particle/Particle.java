package particledemo.particle;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * A rectangle representing a particle.
 * @author Darin Beaudreau
 */
public class Particle extends Rectangle2D.Double {
    // Static variables.
    // Member variables.
    private Point target;
    
    public Particle() {
        super();
        target = new Point();
    }
    
    public Particle(double x_, double y_, double w_, double h_) {
        super(x_, y_, w_, h_);
        target = new Point();
    }
    
    public Particle(double x_, double y_, double w_, double h_, Point target_) {
        super(x_, y_, w_, h_);
        target = target_;
    }
    
    public Point getTarget() { return target; }
    public void setTarget(Point target_) { target = target_; }
}
