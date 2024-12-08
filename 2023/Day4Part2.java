import java.util.*;
import java.io.*;
public class Day4Part2
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day4.txt"));
        int[] instances = new int[192];
        Arrays.fill(instances, 1);
        int index = 0;
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

            for(int i=1;i<=numMatches;i++)
            {
                instances[index+i]+=instances[index];
            }
            index++;
        }
        int total = 0;
        for(int q:instances)
        {
            total+=q;
        }
        System.out.println(total);
    }
}