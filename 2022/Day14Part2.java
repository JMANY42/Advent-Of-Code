import java.util.*;
import java.io.*;
public class Day14Part2
{
    private static int maxYCoordinate;
    public static void main(String[]args) throws IOException
    {
        ArrayList<Rock2> rarl = new ArrayList<Rock2>();

        Scanner fileIn = new Scanner(new File("Day14.txt"));

        maxYCoordinate = 0;
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

                if(y>maxYCoordinate)
                {
                    maxYCoordinate = y;
                }
                if(y2>maxYCoordinate)
                {
                    maxYCoordinate = y2;
                }
                if(x==x2)
                {
                    for(int j=Math.min(y,y2);j<=Math.max(y,y2);j++)
                    {
                        rarl.add(new Rock2(x,j));
                    }
                }
                else
                {
                    for(int j=Math.min(x,x2);j<=Math.max(x,x2);j++)
                    {
                        rarl.add(new Rock2(j,y));
                    }
                }
            }
            //System.out.println("rarl: "+rarl);
        }

        int numRock2s = 0;
        boolean hasSand2 = true;
        Sand2 x = new Sand2(500,0);

        boolean continues = true;
        while(continues)
        {
            if(!hasSand2)
            {
                x = new Sand2(500,0);
                hasSand2 = true;
            }

            ArrayList<Rock2> nrarl = x.move(rarl);
            //System.out.println(nrarl);
            //System.out.println(x.getX());
            //System.out.println(x.getY());
            if(nrarl.size()!=rarl.size())
            {
                rarl=nrarl;
                hasSand2 = false;
                numRock2s++;
                System.out.println(numRock2s);
                System.out.println("x: "+x.getX()+" y: "+x.getY());
            }

            for(Rock2 q:rarl)
            {
                if(q.getX()==500&&q.getY()==0)
                {
                    continues = false;
                    break;
                }
            }
        }

        System.out.println("numRock2s: "+(numRock2s));
    }

    public static int getMaxY()
    {
        return maxYCoordinate+1;
    }
}

class Rock2
{
    private int x;
    private int y;

    public Rock2(int xx, int yy)
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

class Sand2
{
    private int x;
    private int y;

    public Sand2(int xx, int yy)
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
    public ArrayList<Rock2> move(ArrayList<Rock2> orarl)
    {
        ArrayList<Rock2> rarl = new ArrayList<Rock2>();
        for(Rock2 q:orarl)
        {
            rarl.add(q);
        }


        //ArrayList<Rock2> rarl = Day14.getRock2AL();
        boolean canMoveDown = true;
        boolean canMoveDownLeft = true;
        boolean canMoveDownRight = true;

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
        }
        if(y>=Day14Part2.getMaxY())
        {
            canMoveDown=false;
            canMoveDownLeft=false;
            canMoveDownRight=false;
        }
        if(canMoveDown)
        {
            //System.out.println("down");
            y+=1;
        }
        else if(canMoveDownLeft)
        {
            //System.out.println("left");

            y+=1;
            x-=1;
        }
        else if(canMoveDownRight)
        {
            //System.out.println("right");

            y+=1;
            x+=1;
        }
        else
        {
            rarl.add(new Rock2(x,y));
        }
        return rarl;
    }
}