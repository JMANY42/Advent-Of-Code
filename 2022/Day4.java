import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day4.txt"));

        int sum = 0;
        while(fileIn.hasNextLine())
        {
            String[] line = fileIn.nextLine().split(",");

            String[] sFirst = line[0].split("-");
            String[] sSecond = line[1].split("-");

            int[] data = new int[4];
            data[0]=Integer.valueOf(sFirst[0]);
            data[1]=Integer.valueOf(sFirst[1]);
            data[2]=Integer.valueOf(sSecond[0]);
            data[3]=Integer.valueOf(sSecond[1]);

            Set<Integer> set1 = new HashSet<Integer>();
            Set<Integer> set2 = new HashSet<Integer>();

            for(int i=data[0];i<=data[1];i++)
            {
                set1.add(i);
            }
            for(int i=data[2];i<=data[3];i++)
            {
                set2.add(i);
            }
            
            set1.retainAll(set2);



            if(!set1.isEmpty())
            {
                sum++;
            }
            set1.clear();
            set2.clear();

        }
        System.out.println(sum);


    }
}