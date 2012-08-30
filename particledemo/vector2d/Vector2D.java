package particledemo.vector2d;

import java.awt.Point;

/**
 * A representation of a vector in 2D space.
 * @author Darin Beaudreau
 */
public class Vector2D extends Point.Double {
    public Vector2D() {
        super();
    }
    
    public Vector2D(double x_, double y_) {
        super(x_, y_);
    }
    
    public Vector2D(Vector2D v_) {
        super(v_.x, v_.y);
    }
    
    public double getLength() {
        return Math.sqrt(x*x + y*y);
    }
    
    public Vector2D normalize() {
        if(getLength() > 1) return new Vector2D((x / getLength()), (y / getLength()));
        else return null;
    }
}
