import java.util.*;
import java.io.*;
public class Day9
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day9.txt"));
        Point[] rope = new Point[10];
        for(int i=0;i<rope.length;i++)
        {
            rope[i]=new Point(0,0);
        }
        Set<Point> visited = new HashSet<Point>();


        while(in.hasNextLine())
        {
            String line = in.nextLine();
            char direction = line.charAt(0);
            int number = Integer.valueOf(line.substring(2));

            if(direction=='L')
            {
                for(int i=0;i<number;i++)
                {
                    rope[0].setX(rope[0].getX()-1);
                    for(int j=0;j<rope.length-1;j++)
                    {
                        check(rope[j],rope[j+1]);
                        visited.add(new Point(rope[rope.length-1].getX(),rope[rope.length-1].getY()));
                    }
                }
            }
            else if(direction=='R')
            {
                for(int i=0;i<number;i++)
                {
                    rope[0].setX(rope[0].getX()+1);
                    for(int j=0;j<rope.length-1;j++)
                    {
                        check(rope[j],rope[j+1]);
                        visited.add(new Point(rope[rope.length-1].getX(),rope[rope.length-1].getY()));
                    }               
                }
            }
            else if(direction=='U')
            {
                for(int i=0;i<number;i++)
                {
                    rope[0].setY(rope[0].getY()+1);
                    for(int j=0;j<rope.length-1;j++)
                    {
                        check(rope[j],rope[j+1]);
                        visited.add(new Point(rope[rope.length-1].getX(),rope[rope.length-1].getY()));
                    }                }            
            }
            else if(direction=='D')
            {
                for(int i=0;i<number;i++)
                {
                    rope[0].setY(rope[0].getY()-1);
                    for(int j=0;j<rope.length-1;j++)
                    {
                        check(rope[j],rope[j+1]);
                        visited.add(new Point(rope[rope.length-1].getX(),rope[rope.length-1].getY()));
                    }                
                }
            }
        }
        System.out.println(visited.size());
    }
    public static int[] distance(Point h, Point t)
    {
        int[] arr = {h.getX()-t.getX(),h.getY()-t.getY()};
        return arr;
    }
    public static void check(Point p1,Point p2)
    {
        int[] distance = distance(p1,p2);
        if(Math.abs(distance[0])<=1&&Math.abs(distance[1])<=1)
        {
            
        }
        else if(distance[0]==-2&&distance[1]==0)
        {
            p2.setX(p2.getX()-1);
        }
        else if(distance[0]==2&&distance[1]==0)
        {
            p2.setX(p2.getX()+1);
        }
        else if(distance[0]==0&&distance[1]==2)
        {
            p2.setY(p2.getY()+1);
        }
        else if(distance[0]==0&&distance[1]==-2)
        {
            p2.setY(p2.getY()-1);
        }
        else if(distance[0]<=-1&&distance[1]<=-1)
        {
            p2.setX(p2.getX()-1);
            p2.setY(p2.getY()-1);
        }
        else if(distance[0]>=1&&distance[1]>=1)
        {
            p2.setX(p2.getX()+1);
            p2.setY(p2.getY()+1);
        }
        else if(distance[0]<=-1&&distance[1]>=1)
        {
            p2.setX(p2.getX()-1);
            p2.setY(p2.getY()+1);
        }
        else if(distance[0]>=1&&distance[1]<=-1)
        {
            p2.setX(p2.getX()+1);
            p2.setY(p2.getY()-1);
        }
    }
}


class Point
{
    private int x;
    private int y;

    public Point(int xx, int yy)
    {
        x=xx;
        y=yy;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int xx)
    {
        x = xx;
    }
    public void setY(int yy)
    {
        y = yy;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + y;
        result = prime * result + x;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (y != other.y)
            return false;
        if (x != other.x)
            return false;
        return true;
    }
}