import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day13.txt"));
        
        int severity = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] starr = line.split(": ");
            int[] arr = Arrays.stream(starr).mapToInt(Integer::parseInt).toArray();

            if(arr[0]%((arr[1]-1)*2)==0)
            {
                severity += arr[0]*arr[1];
            }
            
        }
        System.out.println(severity);
    }

    public static void partTwo() throws IOException
    {

        Scanner get_length = new Scanner(new File("day13.txt"));
        int n = 0;
        while(get_length.hasNextLine())
        {
            get_length.nextLine();
            n++;
        }

        int[][] mat = new int[n][2];

        Scanner in = new Scanner(new File("day13.txt"));
        
        for(int i=0;i<n;i++)
        {
            String line = in.nextLine();
            String[] starr = line.split(": ");
            mat[i] = Arrays.stream(starr).mapToInt(Integer::parseInt).toArray();
        }


        boolean solved = false;
        int offset = -1;
        while(!solved)
        {
            offset++;
            solved = true;
            for(int i=0;i<n;i++)
            {
                if((mat[i][0]+offset)%((mat[i][1]-1)*2)==0)
                {
                    solved = false;
                    break;
                }
            }
        }
        System.out.println(offset);
    }
}