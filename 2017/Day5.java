import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }    

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day5.txt"));
        ArrayList<Integer> list = new ArrayList<Integer>();

        while(in.hasNextLine())
        {
            list.add(Integer.valueOf(in.nextLine()));
        }

        int[] arr = new int[list.size()];
        for(int i=0;i<arr.length;i++)
        {
            arr[i] = list.get(i);
        }
        
        int index = 0;
        int numJumps = 0;
        while(true)
        {
            if(index<0 || index>=arr.length)
            {
                break;
            }

            int oldIndex = index;
            index+=arr[index];
            arr[oldIndex]++;
            numJumps++;
        }

        System.out.println(numJumps);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day5.txt"));
        ArrayList<Integer> list = new ArrayList<Integer>();

        while(in.hasNextLine())
        {
            list.add(Integer.valueOf(in.nextLine()));
        }

        int[] arr = new int[list.size()];
        for(int i=0;i<arr.length;i++)
        {
            arr[i] = list.get(i);
        }
        
        int index = 0;
        int numJumps = 0;
        while(true)
        {
            if(index<0 || index>=arr.length)
            {
                break;
            }

            int oldIndex = index;
            int offset = arr[index];
            index+=offset;

            if(offset>=3)
            {
                arr[oldIndex]--;
            }
            else
            {
                arr[oldIndex]++;
            }
            numJumps++;
        }

        System.out.println(numJumps);
    }
}
