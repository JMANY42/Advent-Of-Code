import java.util.*;
import java.io.*;
public class Day2Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day2.txt"));

        int idSum = 0;
        while(in.hasNextLine())
        {
            //in.next();
            System.out.println(in.next());
            String ids = in.next();
            int id = Integer.valueOf(ids.substring(0,ids.length()-1));
            System.out.println(id);
            String line = in.nextLine();
            System.out.println(line);
            
            String[] arr = line.split(";");


            int redSum = 0;
            int blueSum = 0;
            int greenSum = 0;
                            boolean valid = true;

            for(String a : arr)
            {
                a = a.trim();
                String[] b = a.split(", ");
                System.out.println(Arrays.toString(b));
                for(int i=0;i<b.length;i++)
                {
                    int num = Integer.valueOf(b[i].split(" ")[0]);
                    char c = b[i].split(" ")[1].charAt(0);
                    if(c=='r'&&num>12)
                    {
                        //redSum += num;
                        valid = false;
                    }
                    else if(c=='g'&&num>13)
                    {
                        //greenSum += num;
                        valid = false;
                    }
                    else if(c=='b'&&num>14)
                    {
                        //blueSum += num;
                        valid = false;
                    }
                }
            }

            if(valid)
            {
                idSum+=id;
            }
            System.out.println(idSum);
            if(redSum <=12 &&blueSum<= 14&&greenSum<= 13)
            {
                //idSum+=id;
            }
        }
        System.out.println(idSum);
    }
}