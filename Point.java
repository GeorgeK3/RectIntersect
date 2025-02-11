import java.lang.*;;


public class Point {

    protected int x = 0;
    protected int y = 0;

    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int x(){
        return x;
    } // return the x-coordinate

    public int y(){
        return y;
    } // return the y-coordinate

    public double distanceTo(Point z){
        return Math.sqrt(squareDistanceTo(z) );
    } // Euclidean distance between two points

    public int squareDistanceTo(Point z){
        return (z.x() - this.x())*(z.x() - this.x()) + (z.y() - this.y())*(z.y() - this.y());
    } // square of the Euclidean distance
    // between two points

    public String toString(){
        return "("+ this.x +", "+ this.y +")";
    } // string representation: (x, y)

    public boolean equals(Point p1){
        return this.x()==p1.x() &&  this.y()==p1.y();
    }
}