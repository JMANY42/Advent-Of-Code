import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }    

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day6.txt"));
        String line = in.nextLine();
        String[] starr = line.split(" ");

        int[] arr = Arrays.stream(starr).mapToInt(Integer::parseInt).toArray();
        ArrayList<int[]> prevArrays = new ArrayList<int[]>();

        int num = 0;
        while(!contains(prevArrays,arr))
        {
            int[] arrCopy = new int[arr.length];
            for(int i=0;i<arr.length;i++)
            {
                arrCopy[i] = arr[i];
            }
            prevArrays.add(arrCopy);

            int max = arr[0];
            int maxIndex  = 0;
            for(int i=1;i<arr.length;i++)
            {
                if(arr[i]>max)
                {
                    max = arr[i];
                    maxIndex = i;
                }
            }
            arr[maxIndex] = 0;
            for(int i=0;i<max;i++)
            {
                arr[(maxIndex+1+i)%arr.length]++;
            }
            num++;
        }
        System.out.println(num);
    }

    public static boolean contains(ArrayList<int[]> list, int[] arr)
    {
        l: for(int[] q:list)
        {
            for(int i=0;i<q.length;i++)
            {
                if(q[i] != arr[i])
                {
                    continue l;
                }
            }
            return true;
        }
        return false;
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day6.txt"));
        String line = in.nextLine();
        String[] starr = line.split(" ");

        int[] arr = Arrays.stream(starr).mapToInt(Integer::parseInt).toArray();
        ArrayList<int[]> prevArrays = new ArrayList<int[]>();

        while(!contains(prevArrays,arr))
        {
            int[] arrCopy = new int[arr.length];
            for(int i=0;i<arr.length;i++)
            {
                arrCopy[i] = arr[i];
            }
            prevArrays.add(arrCopy);

            int max = arr[0];
            int maxIndex  = 0;
            for(int i=1;i<arr.length;i++)
            {
                if(arr[i]>max)
                {
                    max = arr[i];
                    maxIndex = i;
                }
            }
            arr[maxIndex] = 0;
            for(int i=0;i<max;i++)
            {
                arr[(maxIndex+1+i)%arr.length]++;
            }
        }

        System.out.println(Arrays.toString(arr));


        int[] arrCopy = new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            arrCopy[i] = arr[i];
        }

        int max = arr[0];
        int maxIndex  = 0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]>max)
            {
                max = arr[i];
                maxIndex = i;
            }
        }
        arr[maxIndex] = 0;
        for(int i=0;i<max;i++)
        {
            arr[(maxIndex+1+i)%arr.length]++;
        }

        int num = 1;
        while(!equals(arrCopy,arr))
        {
            max = arr[0];
            maxIndex  = 0;
            for(int i=1;i<arr.length;i++)
            {
                if(arr[i]>max)
                {
                    max = arr[i];
                    maxIndex = i;
                }
            }
            arr[maxIndex] = 0;
            for(int i=0;i<max;i++)
            {
                arr[(maxIndex+1+i)%arr.length]++;
            }
            num++;
        }
        System.out.println(num);
    }

    public static boolean equals(int[] arr1, int[] arr2)
    {
        for(int i=0;i<arr1.length;i++)
        {
            if(arr1[i]!=arr2[i])
            {
                return false;
            }
        }
        return true;
    }

}
