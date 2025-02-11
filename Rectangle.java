public class Rectangle {

    private int x_min;
    private int y_min;
    private int x_max;
    private int y_max;

    // Constructor using two points: lowerLeft and upperRight.
    public Rectangle(Point lowerLeft, Point upperRight) {
        x_min = lowerLeft.x();
        y_min = lowerLeft.y();
        x_max = upperRight.x();
        y_max = upperRight.y();
    }

    // Alternative constructor using coordinate values.
    public Rectangle(int x_min, int x_max, int y_min, int y_max) {
        this.x_min = x_min;
        this.x_max = x_max;
        this.y_min = y_min;
        this.y_max = y_max;
    }

    // Accessor methods.
    public int xmin() {
        return x_min;
    } // minimum x-coordinate of rectangle

    public int ymin() {
        return y_min;
    } // minimum y-coordinate of rectangle

    public int xmax() {
        return x_max;
    } // maximum x-coordinate of rectangle

    public int ymax() {
        return y_max;
    } // maximum y-coordinate of rectangle

    // Check if the rectangle contains the point p.
    public boolean contains(Point p) {
        return (p.x() >= x_min && p.x() <= x_max && p.y() >= y_min && p.y() <= y_max);
    }

    // Check if this rectangle intersects with another rectangle.
    // Two axis-aligned rectangles intersect if they are not separated
    // along the x-axis or the y-axis.
    public boolean intersects(Rectangle that) {
        return (this.x_max >= that.x_min && this.x_min <= that.x_max &&
                this.y_max >= that.y_min && this.y_min <= that.y_max);
    }

    // Euclidean distance from point p to the closest point in the rectangle.
    public double distanceTo(Point p) {
        if (contains(p)) {
            return 0;
        }
        
        int closestX, closestY;
        
        // Determine closest x-coordinate.
        if (p.x() < this.x_min) {
            closestX = this.x_min;
        } else if (p.x() > this.x_max) {
            closestX = this.x_max;
        } else {
            closestX = p.x();
        }
        
        // Determine closest y-coordinate.
        if (p.y() < this.y_min) {
            closestY = this.y_min;
        } else if (p.y() > this.y_max) {
            closestY = this.y_max;
        } else {
            closestY = p.y();
        }
        
        return p.distanceTo(new Point(closestX, closestY));
    }

    // Square of the Euclidean distance from point p to the closest point in the rectangle.
    public int squareDistanceTo(Point p) {
        int closestX, closestY;
        
        // Determine closest x-coordinate.
        if (p.x() < this.x_min) {
            closestX = this.x_min;
        } else if (p.x() > this.x_max) {
            closestX = this.x_max;
        } else {
            closestX = p.x();
        }
        
        // Determine closest y-coordinate.
        if (p.y() < this.y_min) {
            closestY = this.y_min;
        } else if (p.y() > this.y_max) {
            closestY = this.y_max;
        } else {
            closestY = p.y();
        }
        
        return p.squareDistanceTo(new Point(closestX, closestY));
    }

    // String representation: [xmin, xmax] x [ymin, ymax]
    public String toString() {
        return "[" + x_min + ", " + x_max + "] x [" + y_min + ", " + y_max + "]";
    }
}
