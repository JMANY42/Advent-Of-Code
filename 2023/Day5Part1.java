import java.util.*;
import java.io.*;

public class Day5Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day5.txt"));

        String line = in.nextLine();
        System.out.println(line);
        long[] seeds = Arrays.stream(line.substring(line.indexOf(":")+2).split(" ")).mapToLong(Long::parseLong).toArray();

        ArrayList<ArrayList<long[]>> arrays = new ArrayList<ArrayList<long[]>>();
        in.nextLine();

        for(int i=0;i<7;i++)
        {
            arrays.add(new ArrayList<long[]>());
            in.nextLine();
            line = in.nextLine();
            while(!line.equals("")&&line.substring(0,1).matches("\\d"))
            {
                long[] range = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
                arrays.get(i).add(range);

                if(in.hasNextLine())
                    line = in.nextLine();
                else
                    break;
            }
        }
        System.out.println(arrays);


        long[] locations = new long[seeds.length];
        for(int i=0;i<seeds.length;i++)
        {
            long next = seeds[i];
            System.out.println(next);
            for(int j=0;j<arrays.size();j++)
            {
                for(int k=0;k<arrays.get(j).size();k++)
                {
                    long source = arrays.get(j).get(k)[1];
                    long range = arrays.get(j).get(k)[2];
                    long destination = arrays.get(j).get(k)[0];
                    if(next>=source && next < range + source)
                    {
                        next = next + (destination-source);
                        System.out.println(next);
                        break;
                    }
                }
                System.out.println(next);
            }
            locations[i] = next;
            System.out.println();
        }
        System.out.println(Arrays.toString(locations));
        long min = locations[0];
        for(long q:locations)
        {
            if(q < min)
            {
                min = q;
            }
        }
        System.out.println(min);
    }
}