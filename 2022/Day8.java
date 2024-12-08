import java.util.*;
import java.io.*;
public class Day8
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day8.txt"));
        String line = in.nextLine();
        int[][] mat = new int[line.length()][line.length()];

        String[] lineArr = line.split("");
        for(int i=0;i<lineArr.length;i++)
        {
            mat[0][i]=Integer.valueOf(lineArr[i]);
        }

        for(int i=1;i<mat.length;i++)
        {
            line = in.nextLine();
            lineArr = line.split("");
            for(int j=0;j<mat[i].length;j++)
            {
                mat[i][j]=Integer.valueOf(lineArr[j]);
            }
        }

        int numVisible = mat.length*2+(mat.length-2)*2;
        //System.out.println(numVisible);
        int max = 0;
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                int s1 = 0;
                int s2 = 0;
                int s3 = 0;
                int s4 = 0;
                int tree = mat[i][j];
                for(int z=j-1;z>=0;z--)
                {
                    if(mat[i][z]>=tree)
                    {
                        s1 = Math.abs(j-z);
                        break;
                    }
                    else if(z==0)
                    {
                        s1 = Math.abs(j-z);
                        break;
                    }
                }
                for(int z=j+1;z<mat.length;z++)
                {
                    if(mat[i][z]>=tree)
                    {
                        s2 = Math.abs(j-z);
                        break;
                    }
                    else if(z==mat.length-1)
                    {
                        s2 = Math.abs(j-z);
                        break;
                    }
                }
                for(int z=i-1;z>=0;z--)
                {
                    if(mat[z][j]>=tree)
                    {
                        s3 = Math.abs(i-z);
                        break;
                    }
                    else if(z==0)
                    {
                        s3 = Math.abs(i-z);
                        break;
                    }
                }
                for(int z=i+1;z<mat.length;z++)
                {
                    if(mat[z][j]>=tree)
                    {
                        s4 = Math.abs(i-z);
                        break;
                    }
                    else if(z==mat.length-1)
                    {
                        s4 = Math.abs(i-z);
                        break;
                    }
                }
                /*System.out.print(s1+" ");
                System.out.print(s2+" ");
                System.out.print(s3+" ");
                System.out.println(s4);*/

                if(s1*s2*s3*s4>max)
                {
                    max = s1*s2*s3*s4;
                }
            }
        }
        System.out.println(max);
    }
}