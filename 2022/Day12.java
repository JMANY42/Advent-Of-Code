import java.util.*;
import java.io.*;

public class Day12
{
    public static void main(String[]args) throws IOException
    {
        Scanner getLength = new Scanner(new File("Day12.txt"));
        Scanner fileIn = new Scanner(new File("Day12.txt"));

        int length = 0;
        String line = "";
        while(getLength.hasNextLine())
        {
            length++;
            line=getLength.nextLine();
        }
        char[][] carr = new char[length][line.length()];
        ArrayList<Point12> points = new ArrayList<Point12>();
        
        ArrayList<Point12> path = new ArrayList<Point12>();
        Point12 exit = new Point12(0,0,0,0);
        Point12 start = new Point12(0,0,0,0);
        System.out.println('z'-97);
        points.clear();
        for(int i=0;i<carr.length;i++)
        {
            line = fileIn.nextLine();
            line.replace("\n","");
            for(int j=0;j<line.length();j++)
            {
                carr[i][j]=line.charAt(j);
                if(carr[i][j]=='S')
                {
                    //System.out.println("i: "+i);
                    //System.out.println("j: "+j);
                    start = new Point12(i,j,0,0);
                    points.add(start);
                }
                else if(carr[i][j]=='E')
                {
                    //System.out.println("i: "+i);
                    //System.out.println("j: "+j);
                    exit = new Point12(i,j,25,-1);
                }
                else
                {
                    //System.out.println("i: "+i);
                    //System.out.println("j: "+j);
                    points.add(new Point12(i,j,carr[i][j]-97,0));
                }
            }
        }
        points.add(exit);

        for(char[] q:carr)
        {
            System.out.println(Arrays.toString(q));
        }
        System.out.println(points);

        ArrayList<Point12> aarr = new ArrayList<Point12>();
        for(int i=0;i<points.size();i++)
        {
            if(points.get(i).getHeight()==0)
            {
                aarr.add(points.get(i));
            }
        }
        int min = Integer.MAX_VALUE;
        System.out.println(aarr);
        System.out.println(aarr.size());
        for(int i=0;i<aarr.size();i++)
        {
            ArrayList<Point12> pointCopy = new ArrayList<Point12>();
            pointCopy.clear();
            path.clear();
            for(Point12 q:points)
            {
                pointCopy.add(q);
            }
            //System.out.println(pointCopy);
            //System.out.println(pointCopy.size());
            path.add(aarr.get(i));
            int temp = run(pointCopy,path,exit);
            System.out.println(temp);
            if(temp<min&&temp!=-1)
            {
                min=temp;
            }
            for(Point12 q:points)
            {
                q.setDistance(0);
            }
        }
        System.out.println(min);
        //run(points,path,exit);
       

    }

    public static int run(ArrayList<Point12> points, ArrayList<Point12> path, Point12 exit)
    {
        while(path.size()!=0)
        {
            //System.out.println(path);
            //System.out.println(exit);
            for(int i=0;i<points.size();i++)
            {
                if(path.get(0).adjacent(exit))
                {
                    return path.get(0).getDistance()+1;
                }
                if(path.get(0).adjacent(points.get(i)))
                {
                    points.get(i).setDistance(path.get(0).getDistance()+1);
                    path.add(points.get(i));
                    points.remove(i);
                    i--;
                }
            }
            path.remove(0);
        }
        return -1;
    }
}

class Point12
{
    private int row;
    private int column;
    private int height;
    private int distance;

    public Point12(int r, int c, int h, int d)
    {
        row=r;
        column=c;
        height=h;
        distance = d;
    }

    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
    public int getHeight()
    {
        return height;
    }
    public int getDistance()
    {
        return distance;
    }
    public void setDistance(int d)
    {
        distance = d;
    }
    public boolean adjacent(Point12 o)
    {
        return ((Math.abs(row-o.getRow())==1 && Math.abs(column-o.getColumn())==0) || (Math.abs(column-o.getColumn())==1 && Math.abs(row-o.getRow())==0)) && (o.getHeight()-height<=1);
    }
    public String toString()
    {
        return "row: "+row+" col: "+column+" height: "+height+" distance: "+distance+"\n";
        //return "h: "+height;
    }
}