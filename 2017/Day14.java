import java.util.*;
import java.io.*;

public class Day14
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        String input = "stpzcrnm";


        int num_used = 0;
        for(int i=0;i<128;i++)
        {
            String line = computeHash(input+"-"+i);

            for(char c:line.toCharArray())
            {
                if(c=='1')
                {
                    num_used++;
                }
            }
        }
        System.out.println(num_used);
    }

    public static void partTwo() throws IOException
    {
        String input = "stpzcrnm";

        char[][] mat = new char[128][128];
        for(int i=0;i<128;i++)
        {
            String line = computeHash(input+"-"+i);
            line = line.replace("0",".");
            line = line.replace("1","#");
            mat[i] = line.toCharArray();
        }

        int num_regions = 0;
        for(int r=0;r<mat.length;r++)
        {
            for(int c=0;c<mat.length;c++)
            {
                if(mat[r][c] == '#')
                {
                    propogate(mat,r,c,(char)num_regions);
                    num_regions++;
                }
            }
        }
        System.out.println(num_regions);
    }

    public static void propogate(char[][] mat, int r, int c, char group)
    {
        if(r<0||c<0||r>=mat.length||c>=mat.length||mat[r][c]!='#')
        {
            return;
        }
        mat[r][c] = group;

        propogate(mat,r-1,c,group);
        propogate(mat,r+1,c,group);
        propogate(mat,r,c-1,group);
        propogate(mat,r,c+1,group);



    }


    public static String computeHash(String key)
    {
        String[] starr = key.split("");
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

        
        String output = "";
        for(int q:dense_hash)
        {
            String str = Integer.toBinaryString(q);
            for(int i=0;i<8-str.length();i++)
            {
                output+="0";
            }
            output+= str;
        }
        return output;
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
}