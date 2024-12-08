import java.util.*;
import java.io.*;

public class Day3Part1
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
                //check if symbol is not period or number
                //check surrounding characters for numbers
                //find entire number, add to sum, change to periods
                System.out.println("arr[r][c]: "+arr[r][c]);
                if(arr[r][c] != '.' && !String.valueOf(arr[r][c]).matches("\\d"))
                {
                    boolean[] neighbors = checkNeighbors(r,c);
                    for(int i=0;i<neighbors.length;i++)
                    {
                        if(neighbors[i])
                        {
                            int[] lr = new int[2];
                            int rOffset = 0;
                            int cOffset = 0;
                            System.out.println("i: "+i);
                            switch(i)
                            {
                                case 0:
                                    lr = getNumberNeighbors(r-1,c-1);
                                    rOffset=-1;
                                    cOffset=-1;
                                break;
                                case 1:
                                    lr = getNumberNeighbors(r,c-1);
                                    cOffset=-1;
                                break;
                                case 2:
                                    lr = getNumberNeighbors(r+1,c-1);
                                    cOffset=-1;
                                    rOffset=1;
                                break;
                                case 3:
                                    lr = getNumberNeighbors(r+1,c);
                                    rOffset=1;
                                break;
                                case 4:
                                    lr = getNumberNeighbors(r+1,c+1);
                                    cOffset=+1;
                                    rOffset=1;
                                break;
                                case 5:
                                    lr = getNumberNeighbors(r,c+1);
                                    cOffset=1;
                                break;
                                case 6:
                                    lr = getNumberNeighbors(r-1,c+1);
                                    rOffset=-1;
                                    cOffset=1;
                                break;
                                case 7:
                                    lr = getNumberNeighbors(r-1,c);
                                    rOffset=-1;
                                break;
                            }

                            int numLength = lr[0]+lr[1]+1;
                            System.out.println("nl: " + numLength+"\tleft: "+lr[0]+"\tright: "+lr[1]);
                            int tempSum = 0;
                            System.out.println("left bound: "+(c+cOffset-lr[0]));
                            System.out.println("right bound: "+(c+cOffset+lr[1]));
                            for(int cx=(c+cOffset-lr[0]);cx<(c+cOffset+lr[1]+1);cx++)
                            {
                                if(arr[r+rOffset][cx]=='.')
                                {
                                    System.out.println("PERIOD FOUND!!");
                                    break;
                                }
                                System.out.println("ro co: "+(r+rOffset)+" "+(c+cOffset));
                                System.out.println("cx: "+cx);
                                System.out.println("r, cx: "+arr[r+rOffset][cx]);
                                System.out.println((r+rOffset)+" "+(cx));
                                tempSum += ((int)(arr[r+rOffset][cx]-48))*Math.pow(10,numLength-1);
                                System.out.println("tempSum: " + tempSum);

                                numLength--;
                                arr[r+rOffset][cx] = '.';
                                System.out.println("r, cx: "+arr[r+rOffset][cx]);

                            }
                            sum += tempSum;
                        }
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
    
    public static boolean[] checkNeighbors(int r,int c)
    {
        boolean[] neighbors = new boolean[8];
        if(r>0&&c>0&&String.valueOf(arr[r-1][c-1]).matches("\\d"))
        {
            neighbors[0]=true;
        }
        if(c>0&&String.valueOf(arr[r][c-1]).matches("\\d"))
        {
            neighbors[1]=true;
        }
        if(r<arr.length-1&&c>0&&String.valueOf(arr[r+1][c-1]).matches("\\d"))
        {
            neighbors[2]=true;
        }
        if(r<arr.length-1&&String.valueOf(arr[r+1][c]).matches("\\d"))
        {
            neighbors[3]=true;
        }
        if(r<arr.length-1&&c<arr[r].length-1&&String.valueOf(arr[r+1][c+1]).matches("\\d"))
        {
            neighbors[4]=true;
        }
        if(c<arr[r].length-1&&String.valueOf(arr[r][c+1]).matches("\\d"))
        {
            neighbors[5]=true;
        }
        if(r>0&&c<arr[r].length-1&&String.valueOf(arr[r-1][c+1]).matches("\\d"))
        {
            neighbors[6]=true;
        }
        if(r>0&&String.valueOf(arr[r-1][c]).matches("\\d"))
        {
            neighbors[7]=true;
        }
        return neighbors;
    }
}