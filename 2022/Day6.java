import java.util.*;
import java.io.*;
public class Day6
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day6.txt"));
        String line = fileIn.nextLine();
        int size = 14;
        char[] arr = new char[size];
        for(int i=0;i<size-1;i++)
        {
            arr[i]=line.charAt(i);
        }
        Set<Character> used = new HashSet<Character>();
        for(int i=3;i<line.length();i++)
        {
            arr[arr.length-1]=line.charAt(i);
            for(int j=0;j<arr.length;j++)
            {
                used.add(arr[j]);
            }
            if(used.size()==arr.length)
            {
                System.out.println(i+1);
                break;
            }
            for(int j=1;j<arr.length;j++)
            {
                arr[j-1]=arr[j];
            }
            used.clear();
        }
    }
}