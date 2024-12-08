import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day10.txt"));
        String line = in.nextLine();
        String[] starr = line.split(",");
        int[] lengths = Arrays.stream(starr).mapToInt(Integer::parseInt).toArray();

        int[] rope = new int[256];
        for(int i=0;i<rope.length;i++)
        {
            rope[i] = i;
        }


        int current_position = 0;
        int skip_size = 0;

        for(int i=0;i<lengths.length;i++)
        {
            reverse(rope,current_position,current_position+lengths[i]-1);
            current_position += lengths[i] + skip_size;
            skip_size++;
        }

        System.out.println(rope[0] * rope[1]);
    }

    public static void reverse(int[] arr, int l, int r)
    {
        for(int i=0;i<(r-l)/2+1;i++)
        {
            int temp = arr[(l+i)%arr.length];
            arr[(l+i)%arr.length] = arr[(r-i)%arr.length];
            arr[(r-i)%arr.length] = temp;
        }
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day10.txt"));
        String line = in.nextLine();
        String[] starr = line.split("");
        int[] lengths = new int[starr.length+5];

        for(int i=0;i<starr.length;i++)
        {
            lengths[i] = starr[i].charAt(0);
        }
        lengths[starr.length] = 17;
        lengths[starr.length+1] = 31;
        lengths[starr.length+2] = 73;
        lengths[starr.length+3] = 47;
        lengths[starr.length+4] = 23;

        int[] rope = new int[256];
        for(int i=0;i<rope.length;i++)
        {
            rope[i] = i;
        }


        int current_position = 0;
        int skip_size = 0;

        for(int round = 0; round < 64; round++)
        {
            for(int i=0;i<lengths.length;i++)
            {
                reverse(rope,current_position,current_position+lengths[i]-1);
                current_position += lengths[i] + skip_size;
                skip_size++;
            }
        }

        int[] dense_hash = new int[16];
        for(int i=0;i<16;i++)
        {
            int num = 0;
            for(int j=0;j<16;j++)
            {
                num ^= rope[i*16+j];
            }
            dense_hash[i] = num;
        }

        for(int q:dense_hash)
        {
            System.out.printf("%02x",q);
        }
    }
}