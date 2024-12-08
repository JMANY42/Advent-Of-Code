import java.util.*;
import java.io.*;
public class Day2Part2
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day2.txt"));

        int powerSum = 0;
        while(in.hasNextLine())
        {
            //in.next();
            System.out.println(in.next());
            String ids = in.next();
            int id = Integer.valueOf(ids.substring(0,ids.length()-1));
            System.out.println(id);
            String line = in.nextLine();
            System.out.println(line);
            
            String[] arr = line.split(";");


            int redMax = Integer.MIN_VALUE;
            int blueMax = Integer.MIN_VALUE;
            int greenMax = Integer.MIN_VALUE;

            for(String a : arr)
            {
                a = a.trim();
                String[] b = a.split(", ");
                System.out.println(Arrays.toString(b));
                for(int i=0;i<b.length;i++)
                {
                    System.out.println(redMax);
            System.out.println(blueMax);
            System.out.println(greenMax);
                    int num = Integer.valueOf(b[i].split(" ")[0]);
                    char c = b[i].split(" ")[1].charAt(0);
                    if(c=='r')
                    {
                        redMax = Math.max(redMax,num);
                    }
                    else if(c=='g')
                    {
                        greenMax = Math.max(greenMax,num);
                    }
                    else if(c=='b')
                    {
                        blueMax = Math.max(blueMax,num);
                    }
                }
            }

            System.out.println(redMax);
            System.out.println(blueMax);
            System.out.println(greenMax);

            if(redMax==Integer.MIN_VALUE||blueMax==Integer.MIN_VALUE||greenMax==Integer.MIN_VALUE)
            {
                continue;
            }
            powerSum += (redMax*blueMax*greenMax);
        }
        System.out.println(powerSum);
    }
}