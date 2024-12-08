import java.util.*;
import java.io.*;
public class Day14
{
    private static ArrayList<Rock> rarl;
    public static void main(String[]args) throws IOException
    {
        rarl = new ArrayList<Rock>();

        Scanner fileIn = new Scanner(new File("Day14.txt"));

        while(fileIn.hasNextLine())
        {
            String line = fileIn.nextLine();
            String[] prevalues = line.split(" -> ");
            int[] values = new int[prevalues.length*2];
            for(int i=0;i<prevalues.length;i++)
            {
                String[] temp = prevalues[i].split(",");
                //System.out.println("temp: "+Arrays.toString(temp));
                values[i*2]=Integer.valueOf(temp[0]);
                values[i*2+1]=Integer.valueOf(temp[1]);
            }
            //System.out.println("values: "+Arrays.toString(values));
            for(int i=0;i<values.length-2;i+=2)
            {
                int x = values[i];
                int y = values[i+1];
                int x2 = values[i+2];
                int y2 = values[i+3];

                if(x==x2)
                {
                    for(int j=Math.min(y,y2);j<=Math.max(y,y2);j++)
                    {
                        rarl.add(new Rock(x,j));
                    }
                }
                else
                {
                    for(int j=Math.min(x,x2);j<=Math.max(x,x2);j++)
                    {
                        rarl.add(new Rock(j,y));
                    }
                }
            }
            //System.out.println("rarl: "+rarl);
        }

        int numRocks = 0;
        boolean hasSand = true;
        Sand x = new Sand(500,0);

        while(!rarl.isEmpty())
        {
            if(!hasSand)
            {
                x = new Sand(500,0);
                hasSand = true;
            }

            ArrayList<Rock> nrarl = x.move(rarl);
            //System.out.println(nrarl);
            //System.out.println(x.getX());
            //System.out.println(x.getY());
            if(nrarl.size()!=rarl.size())
            {
                rarl=nrarl;
                hasSand = false;
                numRocks++;
            }
        }

        System.out.println("numRocks: "+(numRocks-1));
    }
}

class Rock
{
    private int x;
    private int y;

    public Rock(int xx, int yy)
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
        return x+" "+y;
    }
}

class Sand
{
    private int x;
    private int y;

    public Sand(int xx, int yy)
    {
        x=xx;
        y=yy;
        //System.out.println(x);
        //System.out.println(y);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public ArrayList<Rock> move(ArrayList<Rock> orarl)
    {
        ArrayList<Rock> rarl = new ArrayList<Rock>();
        for(Rock q:orarl)
        {
            rarl.add(q);
        }


        //ArrayList<Rock> rarl = Day14.getRockAL();
        boolean canMoveDown = true;
        boolean canMoveDownLeft = true;
        boolean canMoveDownRight = true;
        boolean infiniteFall = true;

        for(int i=0;i<rarl.size();i++)
        {
            if(rarl.get(i).getX()==x&&rarl.get(i).getY()==y+1)
            {
                canMoveDown=false;
            }
            if(rarl.get(i).getX()==x-1&&rarl.get(i).getY()==y+1)
            {
                canMoveDownLeft=false;
            }
            if(rarl.get(i).getX()==x+1&&rarl.get(i).getY()==y+1)
            {
                canMoveDownRight=false;
            }
            if(rarl.get(i).getX()==x&&rarl.get(i).getY()>y)
            {
                infiniteFall = false;
            }
        }
        if(canMoveDown&&!infiniteFall)
        {
            //System.out.println("down");
            y+=1;
        }
        else if(canMoveDownLeft&&!infiniteFall)
        {
            //System.out.println("left");

            y+=1;
            x-=1;
        }
        else if(canMoveDownRight&&!infiniteFall)
        {
            //System.out.println("right");

            y+=1;
            x+=1;
        }
        else if(!infiniteFall)
        {
            rarl.add(new Rock(x,y));
        }
        else
        {
            rarl.clear();
        }
        return rarl;
    }
}