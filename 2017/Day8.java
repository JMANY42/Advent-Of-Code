import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day8.txt"));
        HashMap<String, Integer> register = new HashMap<String, Integer>();

        int num_execs = 0;
        while(in.hasNextLine())
        {
            num_execs++;
            if(num_execs==852)
            {
                System.out.println("break");
            }
            String line = in.nextLine();
            String[] arr = line.split(" ");

            System.out.println("before");
            System.out.println("line: "+line);
            System.out.println("4: "+register.get(arr[4]));
            System.out.println("0: "+register.get(arr[0]));
            System.out.println();

            if(!register.containsKey(arr[4]))
            {
                register.put(arr[4],0);
            }
            

            boolean cont = false;
            switch(arr[5])
            {
                case ">":
                    cont = register.get(arr[4]) > Integer.valueOf(arr[6]);
                    break;
                case "<":
                    cont = register.get(arr[4]) < Integer.valueOf(arr[6]);
                    break;
                case ">=":
                    cont = (register.get(arr[4]) >= Integer.valueOf(arr[6]));
                    break;
                case "<=":
                    cont = (register.get(arr[4]) <= Integer.valueOf(arr[6]));
                    break;
                case "==":

                    if((int)register.get(arr[4]) == (int)Integer.valueOf(arr[6]) != Integer.toString(register.get(arr[4])).equals(arr[6]))
                    {
                        System.out.println("discrepency");
                        System.out.println(register.get(arr[4]));
                        System.out.println(Integer.valueOf(arr[6]));
                        System.out.println("\n"+Integer.toString(register.get(arr[4])));
                        System.out.println(arr[6]);
                    }
                    cont = register.get(arr[4]).equals(Integer.valueOf(arr[6]));
                    break;
                case "!=":
                    cont = !register.get(arr[4]).equals(Integer.valueOf(arr[6]));
                    break;
            }

            if(!cont) continue;

           

            if(!register.containsKey(arr[0]))
            {
                register.put(arr[0],0);
            }

            
            if(arr[1].equals("inc"))
            {
                register.put(arr[0],register.get(arr[0])+Integer.valueOf(arr[2]));
            }
            else if(arr[1].equals("dec"))
            {
                register.put(arr[0],register.get(arr[0])-Integer.valueOf(arr[2]));
            }

            System.out.println("after");
            System.out.println("4: "+register.get(arr[4]));
            System.out.println("0: "+register.get(arr[0]));
            System.out.println("\n\n");
        }
        Set<String> key_set = register.keySet();

        int max = Integer.MIN_VALUE;
        for(String q:key_set)
        {
            max = Math.max(max,register.get(q));
            System.out.println(q+" "+register.get(q));
        }
        System.out.println(max);
    }

    public static void partTwo() throws IOException
    {
   
    }
}