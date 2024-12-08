import java.util.*;
import java.io.*;

public class Day12
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner get_length = new Scanner(new File("day12.txt"));
        int n = 0;
        while(get_length.hasNextLine())
        {
            get_length.nextLine();
            n++;
        }


        int[][] mat = new int[n][n];

        Scanner in = new Scanner(new File("day12.txt"));

        for(int i=0;i<n;i++)
        {
            String line = in.nextLine();
            line = line.substring(line.indexOf(">")+2);
            String[] arr = line.split(", ");

            for(String q:arr)
            {
                mat[i][Integer.valueOf(q)] = 1;
            }
        }

        HashSet<Integer> visited = new HashSet<Integer>();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(0);
        visited.add(0);

        while(!pq.isEmpty())
        {
            int m = pq.poll();
            for(int i=0;i<mat[m].length;i++)
            {
                if(mat[m][i]==0) continue;

                if(!visited.contains(i))
                {
                    pq.add(i);
                    visited.add(i);
                }
            }
        }
        System.out.println(visited.size());
    }

    public static void partTwo() throws IOException
    {
        Scanner get_length = new Scanner(new File("day12.txt"));
        int n = 0;
        while(get_length.hasNextLine())
        {
            get_length.nextLine();
            n++;
        }


        int[][] mat = new int[n][n];

        Scanner in = new Scanner(new File("day12.txt"));

        for(int i=0;i<n;i++)
        {
            String line = in.nextLine();
            line = line.substring(line.indexOf(">")+2);
            String[] arr = line.split(", ");

            for(String q:arr)
            {
                mat[i][Integer.valueOf(q)] = 1;
            }
        }

        HashSet<Integer> visited = new HashSet<Integer>();


        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int num_groups = 0;
        while(visited.size()!=n)
        {
            num_groups++;
            for(int i=0;i<n;i++)
            {
                if(!visited.contains(i))
                {
                    pq.add(i);
                    visited.add(i);
                    break;
                }
            }

            while(!pq.isEmpty())
            {
                int m = pq.poll();
                for(int i=0;i<mat[m].length;i++)
                {
                    if(mat[m][i]==0) continue;

                    if(!visited.contains(i))
                    {
                        pq.add(i);
                        visited.add(i);
                    }
                }
            }
        }
        System.out.println(num_groups);
    }
}