import java.util.*;
import java.io.*;

public class Day19part2
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day19.txt"));
        HashMap<String,String> map = new HashMap<String,String>();

        String line = in.nextLine();
        while(!line.equals(""))
        {
            String[] arr = line.split(" => ");
            map.put(arr[1],arr[0]);
            line = in.nextLine();
        }

        String str = in.nextLine();

        System.out.println(map.keySet());
        for(String q:map.keySet())
        {
            System.out.println(q);
            System.out.println(map.get(q));
            System.out.println();
        }

        System.out.println(recurse(str,map,0));
    }

    public static int recurse(String str, HashMap<String,String> map,int depth)
    {
        //System.out.println(str+" "+depth);
        if(str.equals("e"))
        {
            System.out.println("found "+depth);
            return depth;
        }
        int min = Integer.MAX_VALUE;
        for(int l=0;l<str.length();l++)
        {
            for(int r=l+1;r<str.length()+1;r++)
            {
                //System.out.println(l+" "+r);
                if(map.containsKey(str.substring(l,r)))
                {
                    
                    String left = str.substring(0,l);
                    String right = str.substring(r);
                    String temp = left+map.get(str.substring(l,r))+right;

                    min = Math.min(min, recurse(temp,map,depth+1));
                
                }
            }
        }
        return min;
    }

}