import java.io.*;
import java.util.*;
public class Day6Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day6.txt"));
        String line = in.nextLine();
        String[] parts = line.substring(line.indexOf(":")+1).split(" ");
        line = in.nextLine();
        String[] parts2 = line.substring(line.indexOf(":")+1).split(" ");
        int[] times = new int[4];
        int[] distances = new int[4];
        
        int timeIndex = 0;
        for(int i=0;i<parts.length;i++)
        {
            if(!parts[i].equals(""))
            {
                times[timeIndex]=(Integer.parseInt(parts[i]));
                timeIndex++;
            }
        }
        int distanceIndex = 0;
        for(int i=0;i<parts2.length;i++)
        {
            if(!parts2[i].equals(""))
            {
                distances[distanceIndex]=(Integer.parseInt(parts2[i]));
                distanceIndex++;
            }
        }


        int outerProduct = 1;
        for(int i=0;i<times.length;i++)
        {
            int innerSum = 0;
            for(int time = 0;time<times[i];time++)
            {
                int launchSpeed = times[i]-time;
                if(launchSpeed * time> distances[i])
                {
                    innerSum++;
                }
            }
            outerProduct*=innerSum;
        }

        System.out.println(outerProduct);
    }
}