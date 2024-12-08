import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String[]args) throws IOException {      
        part1();
        part2();
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("data/day8.txt"));

        int rows = 0;
        int cols = 0;
        ArrayList<Node> nodeList = new ArrayList<Node>();
        Set<Node> antiNodeSet = new HashSet<Node>();
        while(in.hasNextLine()) {
            String line = in.nextLine();
            cols = line.length();
            for(int c=0; c<cols; c++) {
                if(line.charAt(c) != '.') {
                    nodeList.add(new Node(rows,c,line.charAt(c)));
                }
            }
            rows++;
        }
        
        in.close();

        for(int i=0; i<nodeList.size(); i++) {
            for(int j=i+1; j<nodeList.size(); j++) {
                Node n1 = nodeList.get(i);
                Node n2 = nodeList.get(j);
                if(n1.getName() == n2.getName()) {
                    int diffX = n2.getX() - n1.getX();
                    int diffy = n2.getY() - n1.getY();

                    if(n1.getX() - diffX >= 0 && n1.getY() - diffy >= 0 && n1.getX() - diffX < cols && n1.getY() - diffy < rows) {
                        antiNodeSet.add(new Node(n1.getX() - diffX, n1.getY() - diffy,'a'));
                    }
                    if(n2.getX() + diffX < cols && n2.getY() + diffy < rows && n2.getX() + diffX >= 0 && n2.getY() + diffy >= 0) {
                        antiNodeSet.add(new Node(n2.getX() + diffX, n2.getY() + diffy,'a'));
                    }
                }
            }
        }
        System.out.println(antiNodeSet.size());
    }

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("data/day8.txt"));

        int rows = 0;
        int cols = 0;
        ArrayList<Node> nodeList = new ArrayList<Node>();
        Set<Node> antiNodeSet = new HashSet<Node>();
        while(in.hasNextLine()) {
            String line = in.nextLine();
            cols = line.length();
            for(int c=0; c<cols; c++) {
                if(line.charAt(c) != '.') {
                    nodeList.add(new Node(rows,c,line.charAt(c)));
                }
            }
            rows++;
        }
        
        in.close();

        for(int i=0; i<nodeList.size(); i++) {
            for(int j=i+1; j<nodeList.size(); j++) {
                Node n1 = nodeList.get(i);
                Node n2 = nodeList.get(j);
                if(n1.getName() == n2.getName()) {
                    int diffX = n2.getX() - n1.getX();
                    int diffy = n2.getY() - n1.getY();

                    int multiple = 0;
                    while(n1.getX() - diffX * multiple >= 0 && n1.getY() - diffy * multiple >= 0 && n1.getX() - diffX * multiple < cols && n1.getY() - diffy * multiple < rows) {
                        antiNodeSet.add(new Node(n1.getX() - diffX * multiple, n1.getY() - diffy * multiple,'a'));
                        multiple++;
                    }
                    multiple = 0;
                    while(n2.getX() + diffX * multiple < cols && n2.getY() + diffy * multiple< rows && n2.getX() + diffX * multiple>= 0 && n2.getY() + diffy * multiple>= 0) {
                        antiNodeSet.add(new Node(n2.getX() + diffX * multiple, n2.getY() + diffy * multiple,'a'));
                        multiple++;
                    }
                }
            }
        }
        System.out.println(antiNodeSet.size());
    }
}

class Node {
    private final int x;
    private final int y;
    private final char name;

    public Node(int x_, int y_, char name_) {
        x = x_;
        y = y_;
        name = name_;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Node other = (Node) obj;
        if(!(this.x == other.x && this.y == other.y)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return x + " "+ y;
    }

    @Override
    public final int hashCode() {
        return 31 * x + 37 * y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getName() {
        return name;
    }
}