import java.util.*;
import java.io.*;

public class Day3Part2
{
    static char[][] arr;
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day3.txt"));

        arr = new char[140][140];
        for(int i=0;i<arr.length;i++)
        {
            String line = in.nextLine();
            for(int j=0;j<arr.length;j++)
            {
                arr[i][j] = line.charAt(j);
            }
        }
        int sum = 0;
        for(int r=0;r<arr.length;r++)
        {
            for(int c=0;c<arr[r].length;c++)
            {
                //check if symbol is *
                //check surrounding characters for numbers
                //if two surrounding numbers
                //multiply two numbers add to sum
                System.out.println("arr[r][c]: "+arr[r][c]);
                if(arr[r][c] == '*')
                {
                    int[] neighbors = checkNeighbors(r,c);
                    int count = 0;
                    int product = 1;
                    for(int b:neighbors)
                    {
                        if(b!=0)
                        {
                            count++;
                            product*=b;
                        }
                    }
                    if(count==2)
                    {
                        sum+=product;
                    }
                }
            }
            System.out.println(sum);
        }
    }
    public static int[] getNumberNeighbors(int r, int c)
    {
        c--;
        int left = 0;
        int right = 0;
        while(c>=0&&String.valueOf(arr[r][c]).matches("\\d"))
        {
            left++;
            c--;
        }
        c+=left+2;
        while(c<arr[r].length&&String.valueOf(arr[r][c]).matches("\\d"))
        {
            right++;
            c++;
        }
        return new int[]{left,right};
    }

    public static int getFullNumber(int r,int c,int left, int right)
    {
        int tempSum = 0;
        int numLength = left+right+1;
        for(int cx=(c-left);cx<(c+right+1);cx++)
        {
            if(arr[r][cx]=='.')
            {
                System.out.println("PERIOD FOUND!!");
                break;
            }
            tempSum += ((int)(arr[r][cx]-48))*Math.pow(10,numLength-1);
            System.out.println("cx: "+cx);
            System.out.println("tempSum: " + tempSum);

            numLength--;
            arr[r][cx] = '.';
        }
        return tempSum;
    }
    
    public static int[] checkNeighbors(int r,int c)
    {
        int[] neighbors = new int[8];
        if(r>0&&c>0&&String.valueOf(arr[r-1][c-1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r-1,c-1);
            neighbors[0]=getFullNumber(r-1,c-1,bounds[0],bounds[1]);
        }
        if(c>0&&String.valueOf(arr[r][c-1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r,c-1);
            neighbors[1]=getFullNumber(r,c-1,bounds[0],bounds[1]);
        }
        if(r<arr.length-1&&c>0&&String.valueOf(arr[r+1][c-1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r+1,c-1);
            neighbors[2]=getFullNumber(r+1,c-1,bounds[0],bounds[1]);
        }
        if(r<arr.length-1&&String.valueOf(arr[r+1][c]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r+1,c);
            neighbors[3]=getFullNumber(r+1,c,bounds[0],bounds[1]);
        }
        if(r<arr.length-1&&c<arr[r].length-1&&String.valueOf(arr[r+1][c+1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r+1,c+1);
            neighbors[4]=getFullNumber(r+1,c+1,bounds[0],bounds[1]);
        }
        if(c<arr[r].length-1&&String.valueOf(arr[r][c+1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r,c+1);
            neighbors[5]=getFullNumber(r,c+1,bounds[0],bounds[1]);
        }
        if(r>0&&c<arr[r].length-1&&String.valueOf(arr[r-1][c+1]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r-1,c+1);
            neighbors[6]=getFullNumber(r-1,c+1,bounds[0],bounds[1]);
        }
        if(r>0&&String.valueOf(arr[r-1][c]).matches("\\d"))
        {
            int[] bounds = getNumberNeighbors(r-1,c);
            neighbors[7]=getFullNumber(r-1,c,bounds[0],bounds[1]);
        }
        return neighbors;
    }
}