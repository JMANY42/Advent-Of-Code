import java.util.*;
import java.io.*;
public class Day4Part1
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day4.txt"));
        int total = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            line = line.substring(line.indexOf(":")+1);
            System.out.println(line);
            line = line.replaceAll("  ", " ");
            line.trim();


            String[] parts = line.split(" \\| ");
            String[] numsStr = parts[0].trim().split(" ");
            Set<Integer> numSet = new HashSet<Integer>();
            String[] checkStr = parts[1].trim().split(" ");

            for(int i=0;i<numsStr.length;i++)
            {
                numSet.add(Integer.valueOf(numsStr[i]));
            }
            int numMatches = 0;
            for(int i=0;i<checkStr.length;i++)
            {
                if(numSet.contains(Integer.parseInt(checkStr[i])))
                {
                    numMatches++;
                }
            }
            total+=(int)Math.pow(2,numMatches-1);

            
        }
        System.out.println(total);
    }
}