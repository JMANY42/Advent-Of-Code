import java.util.*;
import java.io.*;
public class Day1
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day1.txt"));
        ArrayList<Integer> al = new ArrayList<Integer>();
        while(in.hasNextLine())
        {
            int sum = 0;
            while(in.hasNextLine())
            {
                String next = in.nextLine();
                if(next.equals(""))
                {
                    break;
                }
                sum+=Integer.valueOf(next);
            }
            al.add(sum);
        }
        Collections.sort(al);
        System.out.println(al.get(al.size()-1)+al.get(al.size()-2)+al.get(al.size()-3));
    }
}