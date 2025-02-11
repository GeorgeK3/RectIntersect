import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.*;

public class TwoDTree {
    private class TreeNode {
        TreeNode(Point p){
            item=p;
            this.l=null;
            this.r=null;
        }
        int height;
        Rectangle rect;
        Point item; // an object of the class Point
        TreeNode l; // pointer to left subtree
        TreeNode r;// pointer to right subtree
    }

    private TreeNode head=null; //root of the tree
    private int size=0;
    private int min;
    private TreeNode pos;
    

    boolean less(Point p1, Point p2, int height){
        if(height%2!=0){
            return p1.x()<p2.x();
        }else{
            return p1.y()<p2.y();
        }
    }

    public TwoDTree(Point p){
        this.head=new TreeNode(p) ;
        size++;
        head.height=1;
        head.rect = new Rectangle(0,100,0,100);
    } // construct an empty tree

    public TwoDTree(){
        this.head=null ;
    } // construct an empty tree



    public boolean isEmpty(){
        return head==null;
    } // is the tree empty?


    public int size(){
        return this.size;
    } // number of points in the tree


    public void insert(Point p){
        int height = 1;
        if(isEmpty()){
            System.out.println("The tree is empty");
            return;
        }

        if(search(p)){
            System.out.println("The point already exists");
            return;
        }

        TreeNode temp = head;
        TreeNode q = temp;

        while (q != null){
            if (less(p, q.item, height)) { 
                temp = q; 
                q = q.l; 
            }
            else { 
                temp = q; 
                q = q.r; 
            }
            height++;
        }
        height-=1;
        if (less(p, temp.item, height)) {
            temp.l = new TreeNode(p);
            size++;
            temp.l.height = height;

            if (height%2!=0){
                temp.l.rect = new Rectangle(temp.rect.xmin(),temp.item.x(),temp.rect.ymin(),temp.rect.ymax());
            }else{
                temp.l.rect = new Rectangle(temp.rect.xmin(),temp.rect.xmax(),temp.rect.ymin(),temp.item.y());
            }

        }
        else {
            temp.r = new TreeNode(p);
            size++; 
            temp.r.height = height;

            if (height%2!=0){
                temp.r.rect = new Rectangle(temp.item.x(),temp.rect.xmax(),temp.rect.ymin(),temp.rect.ymax());
            }else{
                temp.r.rect = new Rectangle(temp.rect.xmin(),temp.rect.xmax(),temp.item.y(),temp.rect.ymax());
            }

        }
        
    } // inserts the point p to the tree


    public boolean search(Point p) {
        if (head == null){
            return false;
        }

        int height=1;

        if(head.item.equals(p)){
            return true;
        }

        TreeNode temp = head;
        TreeNode q = temp;

        while (q != null){
            if (less(p, q.item, height)) { 
                temp = q; 
                q = q.l; 
            }
            else { 
                temp = q; 
                q = q.r; 
            }
            height++;
            if(temp.item.equals(p)){
                return true;
            }
        }
        return false;

    }// does the tree contain p?


    public Point nearestNeighbor(Point p){
        min=head.item.squareDistanceTo(p) ;
        pos=head;
        return nearestNeighborR(head, p);
    }


    public Point nearestNeighborR(TreeNode h, Point p){
        
        if(h==null){
            return null;
        }

        if(h.item.squareDistanceTo(p)<min){
            min=h.item.squareDistanceTo(p);

            pos=h;
        }
        if (h.l!=null){
            if (h.l.rect.squareDistanceTo(p) < min){
                nearestNeighborR( h.l,  p);
            }
        }
        if (h.r!=null){
            if (h.r.rect.squareDistanceTo(p) < min){
                nearestNeighborR( h.r,  p);
            }
        }

        return pos.item;

    } // point in the tree that is
    //closest to p (null if tree is empty)

    public List<Point> rangeSearch(Rectangle rect){
        List<Point> l=new List<>();
        rangeSearchR(head,  rect, l);
        
        return l;
    }

    public void rangeSearchR(TreeNode h, Rectangle rect, List<Point> l){
        
        if(isEmpty()){
            return;
        }

        if(h==null){
            return ;
        }

        if(rect.contains(h.item)){
            l.insert(h.item);
        }
        if (h.l!=null){
            if (h.l.rect.intersects(rect)){
                rangeSearchR( h.l,  rect, l);
            }
        }
        if (h.r!=null){
            if (h.r.rect.intersects(rect)){
                rangeSearchR( h.r,  rect, l);
            }
        }

        
        return ;
    } // Returns a list
    //with the Points that are contained in the rectangle


    public String toString(){
        s="";
        return  toStringR(head);
    }

    private String s;
    private String toStringR(TreeNode h){
        if(h==null){
            return null;
        }
        s+=(h.item.toString());
        toStringR(h.l);
        toStringR(h.r);

        return s;
    }



    public static void main(String[] args) {

        int n;
        BufferedReader reader = null;
        String line ;
        int x,y;
        Point p;
        String filename = args[0];
        Path file = Paths.get(args[0]);
        

        try{

            reader = new BufferedReader(new FileReader(new File(filename)));
            line = reader.readLine();
            long count= Files.lines(file).count()-1;
            n = Integer.parseInt(line);

            if(count!=n){
                reader.close();
                throw new NoSuchElementException();
            }

            line = reader.readLine();
            TwoDTree t=new TwoDTree();

            while(line!=null){        //trexei oles tis grammes
                
                String[] subs = line.split(" ",2);
                x = Integer.parseInt(subs[0]) ;
                y = Integer.parseInt(subs[1]);
                

                if(x>100 || x<0 || y>100 || y<0){
                    reader.close();
                    throw new NoSuchElementException();
                }
                
                p=new Point(x, y);
                if(t.isEmpty()){
                    t=new TwoDTree(p);
                }else{
                    t.insert(p);
                }
                line = reader.readLine(); 
            }

            while(true){
                System.out.println("\n1.Compute the size of the tree");
                System.out.println("2.Insert a new point");
                System.out.println("3.Search if a given point exists in the tree");
                System.out.println("4.Provide a query rectangle");
                System.out.println("5.Provide a query point");
                System.out.println("6.Exit");
                Scanner sc=new Scanner(System.in);  
                System.out.println("\nEnter a number: ");  
                int num = Integer.parseInt(sc.nextLine());  

                if(num<1 || num>6){
                    System.out.println("\nInvalid command");
                    sc.close();
                    break;
            // ====================="1"============================
                }else if(num==1){
                    System.out.println("\nThe size of the tree is: "+t.size());

            // ====================="2" & "3"=======================
                }else if (num==2 || num==3){  
                    System.out.println("\nGive the x and the y of the point (x, y): x y");
                    String str=sc.nextLine();
                    String[] subs = str.split(" ",2);
                    x = Integer.parseInt(subs[0]) ;
                    y = Integer.parseInt(subs[1]);
                    if(x>100 || x<0 || y>100 || y<0){
                        reader.close();
                        sc.close();
                        throw new NoSuchElementException();
                    }
                    p=new Point(x, y);
                    
                    if(num==2){
                        t.insert(p);
                    }else{
                        if(t.search(p)){
                            System.out.println("\nThe point "+p+" exists in the tree");
                        }else{
                            System.out.println("\nThe point "+p+" does not exist in the tree");
                        }
                    }

            // ====================="4"=========================    
                }else if(num==4){
                    System.out.println("\nGive the x_min and the x_max of the rectangle like that: x_min x_max");
                    String strx=sc.nextLine();
                    String[] subsx = strx.split(" ",2);
                    x = Integer.parseInt(subsx[0]) ;
                    y = Integer.parseInt(subsx[1]);
                    if(x>100 || x<0 || y>100 || y<0){
                        reader.close();
                        sc.close();
                        throw new NoSuchElementException();
                    }
                    Point px=new Point(x, y);

                    System.out.println("\nGive the y_min and the y_max of the rectangle like that: y_min y_max");
                    String stry=sc.nextLine();
                    String[] subsy = stry.split(" ",2);
                    x = Integer.parseInt(subsy[0]) ;
                    y = Integer.parseInt(subsy[1]);
                    if(x>100 || x<0 || y>100 || y<0){
                        reader.close();
                        sc.close();
                        throw new NoSuchElementException();
                    }
                    Point py=new Point(x, y);
                    List<Point> bob = t.rangeSearch(new Rectangle(px, py));
                    
                    System.out.println("\n" + "The points inside the rectangle are:\n" + bob);

            // ====================="5"========================= 
                }else if(num==5){ 
                    System.out.println("Give the x and the y of the point (x, y): x y");
                    String str=sc.nextLine();
                    String[] subs = str.split(" ",2);
                    x = Integer.parseInt(subs[0]) ;
                    y = Integer.parseInt(subs[1]);
                    if(x>100 || x<0 || y>100 || y<0){
                        reader.close();
                        sc.close();
                        throw new NoSuchElementException();
                    }
                    p=new Point(x, y);
                    System.out.println("\n"+"The nearest point to "+p+ " is:\n"+t.nearestNeighbor(p)); 
                }
                // ====================="6"========================= 
                else{
                    System.out.println("\nExiting ... ");
                    sc.close();
                    break;
                }
                
            }
        }catch (IOException e){  //For errors while reading the file
            System.err.println("Error reading File!!!");
        }

        catch (NoSuchElementException e){   //For false information while reading
            System.err.println("False information detected");
        }
    }
}