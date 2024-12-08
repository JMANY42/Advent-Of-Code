import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }    

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day4.txt"));
        int numValid = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] arr = line.split(" ");
            HashSet<String> set = new HashSet<String>();
            for(String q:arr)
            {
                set.add(q);
            }
            if(set.size()==arr.length)
            {
                numValid++;
            }
        }
        System.out.println(numValid);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day4.txt"));
        int numValid = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] arr = line.split(" ");
            HashSet<String> set = new HashSet<String>();
            for(String q:arr)
            {
                char[] chars = q.toCharArray();
                Arrays.sort(chars);

                String word = "";
                for(char qq:chars)
                {
                    word+=qq;
                }

                set.add(word);
            }
            if(set.size()==arr.length)
            {
                numValid++;
            }
        }
        System.out.println(numValid);
    }
}
