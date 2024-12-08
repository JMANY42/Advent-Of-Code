import java.io.*;
import java.util.*;

public class Day9Part2 {
    public static void main(String[] args) throws IOException
    {
        //get the data from the file day1.txt
        Scanner in = new Scanner(new File("Day9.txt"));
        
        int total = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            ArrayList<int[]> distances = new ArrayList<int[]>();

            int[] data = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] addToDistance = new int[data.length+1];
            for(int i = 0; i < data.length; i++)
            {
                addToDistance[i+1] = data[i];
            }
            
            distances.add(addToDistance);
            addDistances(distances);
            while(!checkZeros(distances))
            {
                addDistances(distances);
                //System.out.println(distances);
                
            }

            System.out.println(distances);
            for(int i=distances.size()-2;i>=0;i--)
            {
                distances.get(i)[0] = distances.get(i)[1]-distances.get(i+1)[0];
            }
            System.out.println(distances.get(0)[0]);
            total += distances.get(0)[0];
        }
        
        System.out.println(total);
    }

    public static boolean checkZeros(ArrayList<int[]> d)
    {
        for(int q:d.get(d.size()-1))
        {
            System.out.println(q);
            if(q!=0)
            {
                return false;
            }
        }
        return true;
    }

    public static void addDistances(ArrayList<int[]> d)
    {
        int[] addArr = new int[d.get(d.size()-1).length-1];
        for(int i=1;i<addArr.length;i++)
        {
            addArr[i] = d.get(d.size()-1)[i+1]-d.get(d.size()-1)[i];
        }
        System.out.println(Arrays.toString(addArr));
        d.add(addArr);
    }
}

