import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[]args) throws IOException
    {
        part1();
        part2();
    }

    public static void part1() throws IOException
    {
        Scanner in = new Scanner(new File("data/day1.txt"));
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>();
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split("   ")).mapToInt(Integer::parseInt).toArray();
            pq1.add(arr[0]);
            pq2.add(arr[1]);
        }
        in.close();


        int total = 0;
        while(!pq1.isEmpty())
        {
            total+=(Math.abs(pq1.poll()-pq2.poll()));
        }
        System.out.println(total);
    }

    public static void part2() throws IOException
    {
        Scanner in = new Scanner(new File("data/day1.txt"));
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        HashMap<Integer,Integer> hm2 = new HashMap<Integer,Integer>();

        while(in.hasNextLine())
        {
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split("   ")).mapToInt(Integer::parseInt).toArray();

            pq1.add(arr[0]);

            if(hm2.containsKey(arr[1])){
                hm2.put(arr[1],hm2.get(arr[1])+1);
            }
            else{
                hm2.put(arr[1],1);
            }

        }  
        in.close();

        int total = 0;
        while(!pq1.isEmpty())
        {
            if(hm2.containsKey(pq1.peek())){
                total+=hm2.get(pq1.peek())*pq1.peek();;
            }
            pq1.poll();
        }
        System.out.println(total);
    }
}