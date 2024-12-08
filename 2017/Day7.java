import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }    

    public static void partOne() throws IOException
    {
        
        System.out.println(getBase());
    }

    public static void partTwo() throws IOException
    {
        String base = getBase();

        Scanner in = new Scanner(new File("day7.txt"));

        HashMap<String, Integer> weights = new HashMap<String, Integer>();
        HashMap<String, String[]> supports = new HashMap<String, String[]>();

        while(in.hasNextLine())
        {
            String name = in.next();
            String weight = in.next();

            name.trim();
            weight = weight.replace("(","");
            weight = weight.replace(")","");
            weight.trim();

            weights.put(name,Integer.valueOf(weight));

            if(!in.hasNextLine())
            {
                break;
            }
            String line = in.nextLine();

            if(line.contains("->"))
            {
                line = line.replace(" -> ","");
                String[] arr = line.split(", ");
                supports.put(name,arr);
            }
        }

        System.out.println(find_outlier(base,weights,supports,0));
    }

    public static int find_outlier(String base, HashMap<String, Integer> weights, HashMap<String, String[]> supports, int other_weight)
    {

        if(!supports.containsKey(base))
        {
            return other_weight;
        }

        String[] arr = supports.get(base);

        int[] weight_arr = new int[arr.length];

        int total_weight = 0;
        for(int i=0;i<arr.length;i++)
        {
            weight_arr[i] = get_weight(arr[i],weights,supports);
            total_weight+=weight_arr[i];
        }

        int outlier_index = -1;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                for(int k=0;k<arr.length;k++)
                {
                    if(i==j||j==k||i==k) continue;
                    if(weight_arr[i]==weight_arr[j]&&weight_arr[j]!=weight_arr[k])
                    {
                        outlier_index = k;
                    }
                }
            }
        }

        if(outlier_index == -1)
        {
            return Math.abs(other_weight-total_weight);
        }
        
        if(outlier_index == 0)
        {
            other_weight = weight_arr[1];
        }
        else
        {
            other_weight = weight_arr[0];
        }
        return find_outlier(arr[outlier_index],weights,supports,other_weight);
    }

    public static int get_weight(String base, HashMap<String, Integer> weights, HashMap<String, String[]> supports)
    {
        if(!supports.containsKey(base))
        {
            return weights.get(base);
        }


        String[] arr = supports.get(base);
        int[] weight_arr = new int[arr.length];

        int sum = 0;
        for(int i=0;i<arr.length;i++)
        {
            weight_arr[i] = get_weight(arr[i],weights,supports);
            sum+=weight_arr[i];
        }

        return weights.get(base) + sum;
    }

    public static String getBase() throws IOException
    {
        Scanner in = new Scanner(new File("day7.txt"));

        HashSet<String> all_programs = new HashSet<String>();
        HashSet<String> supported_programs = new HashSet<String>();

        while(in.hasNextLine())
        {
            all_programs.add(in.next());

            String rest_of_line = in.nextLine();


            if(rest_of_line.contains("->"))
            {
                String[] arr = rest_of_line.split(" -> ");

                String[] arr2 = arr[1].split(", ");
                for(String q:arr2)
                {
                    supported_programs.add(q);
                }
            }
        }
        all_programs.removeAll(supported_programs);

        String[] arr = all_programs.toArray(new String[0]);
        return arr[0];
    }
}
