import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day15.txt"));

        int numLines = 0;
        //System.out.println("a");
        //System.out.println(fileIn.hasNextLine());
        while(fileIn.hasNextLine())
        {
            //System.out.println("asdf");
            numLines++;
            fileIn.nextLine();
        }
        //System.out.println("b");
        Sensor[] scarr = new Sensor[numLines];
        Beacon[] barr = new Beacon[numLines];
        //System.out.println(numLines);
        Scanner fileIn2 = new Scanner(new File("Day15.txt"));
        for(int i=0;i<numLines;i++)
        {
            String[] data = fileIn2.nextLine().split(" ");
            //System.out.println(data[2]);
            //System.out.println(data[2].substring(2,data[2].length()-1));
            scarr[i] = new Sensor(Integer.valueOf(data[2].substring(2,data[2].length()-1)),Integer.valueOf(data[3].substring(2,data[3].length()-1)));
            barr[i] = new Beacon(Integer.valueOf(data[8].substring(2,data[8].length()-1)),Integer.valueOf(data[9].substring(2)));
            scarr[i].setClosestBeacon(barr[i]);
        }
        //System.out.println(Arrays.toString(scarr));
        //System.out.println(Arrays.toString(barr));
        Set<NoBeacon> nbal = new HashSet<NoBeacon>();
        int row = 2000000;

        for(int i=0;i<scarr.length;i++)
        {
            //System.out.println("i: "+i);
            int x = scarr[i].getClosestBeacon().getX();
            int y = scarr[i].getClosestBeacon().getY();

            int distance = Math.abs(x-scarr[i].getX())+Math.abs(y-scarr[i].getY());
            //System.out.println("distance "+distance);
            int min = 0;
            int max = 1;

            for(int a=0;a<2*distance+1;a++)
            {
                System.out.println("i: "+i+" a: "+a);
                for(int b=0;b<0-(min-max);b++)
                {
                    /*System.out.println("min: "+min);
                    System.out.println("max: "+max);
                    System.out.println("b: "+b);
                    System.out.println(new NoBeacon(scarr[i].getX()+min+b,a-distance));*/
            
                    nbal.add(new NoBeacon(scarr[i].getX()+min+b,a-distance+scarr[i].getY()));

                    
                }
                if(a<distance)
                {
                    min--;
                    max++;
                }
                else
                {
                    min++;
                    max--;  
                }
                /*System.out.println(min);
                System.out.println(max);
                System.out.println("a: "+a);
                System.out.println(0-(min-max));
                System.out.println(distance);*/
            }
        }

        System.out.println(nbal);
        System.out.println(nbal.size());

        int numFree = 0;
        Iterator<NoBeacon> x = nbal.iterator();
        for(int i=0;i<nbal.size();i++)
        {
            //System.out.println(x.next());
            NoBeacon temp = x.next();
            if(temp.getY()==row)
            {
                boolean add = true;
                for(int j=0;j<scarr.length;j++)
                {
                    if(scarr[j].getX()==temp.getX()&&scarr[j].getY()==temp.getY()||barr[j].getX()==temp.getX()&&barr[j].getY()==temp.getY())
                    {
                        add = false;
                    }
                }
                if(add)
                {
                    numFree++;
                }
                //System.out.println(temp);
            }
        }
        System.out.println(numFree);
    }
}

class Sensor
{
    private int x;
    private int y;
    private Beacon closestBeacon;
    public Sensor(int xx, int yy)
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
    public void setClosestBeacon(Beacon x)
    {
        closestBeacon = x;
    }
    public Beacon getClosestBeacon()
    {
        return closestBeacon;
    }
    public String toString()
    {
        return "("+x+","+y+")";
    }

}

class Beacon
{
    private int x;
    private int y;
    public Beacon(int xx, int yy)
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
    public String toString()
    {
        return "("+x+","+y+")";
    }
}

class NoBeacon
{
    private int x;
    private int y;
    public NoBeacon(int xx, int yy)
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
    public String toString()
    {
        return "("+x+","+y+")\n";
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
        NoBeacon other = (NoBeacon) obj;
        if (y != other.y)
            return false;
        if (x != other.x)
            return false;
        return true;
    }
}