import java.util.*;
import java.io.*;

public class Day19
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day19.txt"));
        HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();

        String line = in.nextLine();
        while(!line.equals(""))
        {
            String[] arr = line.split(" => ");
            if(map.containsKey(arr[0]))
            {
                map.get(arr[0]).add(arr[1]);
            }
            else
            {
                HashSet<String> set = new HashSet<String>();
                set.add(arr[1]);
                map.put(arr[0],set);
            }
            line = in.nextLine();
        }

        String str = in.nextLine();

        System.out.println(map.keySet());
        for(String q:map.keySet())
        {
            System.out.println(q);
            for(String qq:map.get(q))
            {
                System.out.println(qq);
            }
            System.out.println();
        }


        HashSet<String> answers = new HashSet<String>();

        for(int l=0;l<str.length();l++)
        {
            for(int r=l+1;r<str.length()+1;r++)
            {
                //System.out.println(l+" "+r);
                if(map.containsKey(str.substring(l,r)))
                {
                    for(String q:map.get(str.substring(l,r)))
                    {
                        String left = str.substring(0,l);
                        String right = str.substring(r);
                        String temp = left+q+right;
                        answers.add(temp);
                        System.out.println(temp);
                    }
                }
            }
        }
        System.out.println(answers.size());
    }
}