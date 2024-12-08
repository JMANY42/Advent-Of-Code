import java.util.*;
import java.io.*;

public class Day18
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day18.txt"));

        ArrayList<String> input = new ArrayList<String>();
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            input.add(line);
        }


        HashMap<Character,Long> register = new HashMap<Character,Long>();
        for(int j=0;j<26;j++)
        {
            register.put((char)('a'+j),0l);
        }
        long sound = -1;
  loop: for(int i=0;i<input.size();i++)
        {
            if(i<0) break;
            String[] arr = input.get(i).split(" ");


            
            switch(arr[0])
            {
                case "snd":
                    if(arr[1].matches("-?\\d+"))
                        sound = Long.valueOf(arr[1]);
                    else
                        sound = register.get(arr[1].charAt(0));
                    break;
                case "set":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[2].charAt(0)));
                    break;
                case "add":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) + Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) + register.get(arr[2].charAt(0)));
                    break;
                case "mul":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) * Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) * register.get(arr[2].charAt(0)));
                    break;
                case "mod":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) % Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) % register.get(arr[2].charAt(0)));
                    break;
                case "rcv":
                    if(arr[1].matches("-?\\d+") && Long.valueOf(arr[1]) != 0)
                    {
                        System.out.println(sound);
                        break loop;
                    }
                    else if(arr[1].matches("\\w") && register.get(arr[1].charAt(0)) != 0)
                    {
                        System.out.println(sound);
                        break loop;
                    }
                    break;
                case "jgz":
                    if(arr[1].matches("-?\\d+") && Long.valueOf(arr[1]) <= 0)
                    {
                        break;
                    }
                    else if(arr[1].matches("\\w") && register.get(arr[1].charAt(0)) <= 0)
                    {
                        break;
                    }

                    if(arr[2].matches("-?\\d+"))
                        i += (Long.valueOf(arr[2])-1);
                    else
                        i += (register.get(arr[2].charAt(0))-1);
                    break;
            }
        }
        
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day18.txt"));

        ArrayList<String> input = new ArrayList<String>();
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            input.add(line);
        }


        HashMap<Character,Long> register0 = new HashMap<Character,Long>();
        for(int j=0;j<26;j++)
        {
            register0.put((char)('a'+j),0l);
        }
        register0.put('p',0l);

        HashMap<Character,Long> register1 = new HashMap<Character,Long>();
        for(int j=0;j<26;j++)
        {
            register1.put((char)('a'+j),0l);
        }
        register1.put('p',0l);



        boolean terminated0 = false;
        boolean terminated1 = false;

        boolean waiting0 = false;
        boolean waiting1 = false;

        int index0 = 0;
        int index1 = 0;

        Queue<Long> queue0 = new LinkedList<Long>();
        Queue<Long> queue1 = new LinkedList<Long>();

        boolean turn0 = true;
        while(!terminated0 && !terminated1)
        {
            if(turn0)
            {
                boolean condition = turn(index0,register0,queue0);
                if(condition) //terminated
                {
                    terminated0 = true;
                    turn0 = false;
                    break;
                }
                else //waiting for signal
                {
                    waiting1 = true;
                    turn0 = false;
                    break;
                }
            }
        }
  loop: for(int i=0;i<input.size();i++)
        {
            if(i<0) break;
            String[] arr = input.get(i).split(" ");


            
            switch(arr[0])
            {
                case "snd":
                    if(arr[1].matches("-?\\d+"))
                        sound = Long.valueOf(arr[1]);
                    else
                        sound = register.get(arr[1].charAt(0));
                    break;
                case "set":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[2].charAt(0)));
                    break;
                case "add":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) + Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) + register.get(arr[2].charAt(0)));
                    break;
                case "mul":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) * Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) * register.get(arr[2].charAt(0)));
                    break;
                case "mod":
                    if(arr[2].matches("-?\\d+"))
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) % Long.valueOf(arr[2]));
                    else
                        register.put(arr[1].charAt(0),register.get(arr[1].charAt(0)) % register.get(arr[2].charAt(0)));
                    break;
                case "rcv":
                    if(arr[1].matches("-?\\d+") && Long.valueOf(arr[1]) != 0)
                    {
                        System.out.println(sound);
                        break loop;
                    }
                    else if(arr[1].matches("\\w") && register.get(arr[1].charAt(0)) != 0)
                    {
                        System.out.println(sound);
                        break loop;
                    }
                    break;
                case "jgz":
                    if(arr[1].matches("-?\\d+") && Long.valueOf(arr[1]) <= 0)
                    {
                        break;
                    }
                    else if(arr[1].matches("\\w") && register.get(arr[1].charAt(0)) <= 0)
                    {
                        break;
                    }

                    if(arr[2].matches("-?\\d+"))
                        i += (Long.valueOf(arr[2])-1);
                    else
                        i += (register.get(arr[2].charAt(0))-1);
                    break;
            }
        }
    }

    public static void turn(int index, HashMap<Character,Long> register, Queue<Long> queue)
    {

    }
}