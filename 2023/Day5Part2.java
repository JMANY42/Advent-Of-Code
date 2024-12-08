import java.util.*;
import java.io.*;

public class Day5Part2
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
            arrays.add(0,new ArrayList<long[]>());
            in.nextLine();
            line = in.nextLine();
            while(!line.equals("")&&line.substring(0,1).matches("\\d"))
            {
                long[] range = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
                arrays.get(0).add(range);

                if(in.hasNextLine())
                    line = in.nextLine();
                else
                    break;
            }
            System.out.println(i);
        }
        System.out.println(arrays);


        for(long i=0;i<Integer.MAX_VALUE;i++)
        {
            long next = i;
            //if(i%1000000==0)
            //{System.out.println(i);}
            for(int j=0;j<arrays.size();j++)
            {
                for(int k=0;k<arrays.get(j).size();k++)
                {
                    long source = arrays.get(j).get(k)[0];
                    long destination = arrays.get(j).get(k)[1];
                    long range = arrays.get(j).get(k)[2];
                    if(next>=source && next < range + source)
                    {
                        next = next + (destination-source);
                        break;
                    }
                }
            }
            for(int k=0;k<seeds.length;k+=2)
            {
                if(next >= seeds[k] && next <= seeds[k]+seeds[k+1])
                {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}