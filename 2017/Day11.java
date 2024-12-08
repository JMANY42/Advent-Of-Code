import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day11.txt"));
        String line = in.nextLine();
        String[] arr = line.split(",");


        int[] steps = new int[6];

        for(String q:arr)
        {
            int n = -1;
            switch(q)
            {
                case "n":
                    n = 0;
                    break;
                case "ne":
                    n = 1;
                    break;
                case "se":
                    n = 2;
                    break;
                case "s":
                    n = 3;
                    break;
                case "sw":
                    n = 4;
                    break;
                case "nw":
                    n = 5;
                    break;
            }

            steps[n]++;
        }

        simplify(steps);
        int sum = 0;
        for(int q:steps)
        {
            sum+=q;
        }
        System.out.println(sum);
    }

    public static void simplify(int[] arr)
    {
        for(int i=0;i<3;i++)
        {
            if(arr[i] >= arr[i+3])
            {
                arr[i] -= arr[i+3];
                arr[i+3] = 0;
            }
            else
            {
                arr[i+3] -= arr[i];
                arr[i] = 0;
            }
        }

        for(int i=0;i<6;i++)
        {
            int next = (i+2)%arr.length;
            int middle = (i+1)%arr.length;
            if(arr[i] >= arr[next])
            {
                arr[i] -= arr[next];
                arr[middle] += arr[next];
                arr[next] = 0;
            }
            else
            {
                arr[next] -= arr[i];
                arr[middle] += arr[i];
                arr[i] = 0;
            }
        }

    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day11.txt"));
        String line = in.nextLine();
        String[] arr = line.split(",");


        int[] steps = new int[6];

        int max_distance = -1;

        for(String q:arr)
        {
            int n = -1;
            switch(q)
            {
                case "n":
                    n = 0;
                    break;
                case "ne":
                    n = 1;
                    break;
                case "se":
                    n = 2;
                    break;
                case "s":
                    n = 3;
                    break;
                case "sw":
                    n = 4;
                    break;
                case "nw":
                    n = 5;
                    break;
            }

            steps[n]++;

            simplify(steps);

            int sum = 0;
            for(int qq:steps)
            {
                sum+=qq;
            }

            max_distance = Math.max(max_distance,sum);
        }

        System.out.println(max_distance);
    }
}