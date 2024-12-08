import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day9.txt"));
        String data = in.nextLine();
        char[] arr = data.toCharArray();



        Stack<Character> stack = new Stack<Character>();
        int score = 0;
        boolean in_garbage = false;
        boolean negated = false;
        for(char c:arr)
        {
            if(c=='!')
            {
                negated = !negated;
                continue;
            }



            if(in_garbage&&!negated)
            {
                if(c=='>')
                {
                    in_garbage = false;
                }
            }
            else if(!negated)
            {
                switch(c)
                {
                    case '{':
                        stack.push(c);
                        break;
                    case '}':
                        score+=stack.size();
                        stack.pop();
                        break;
                    case '<':
                        in_garbage = true;
                        break;
                }
            }
            negated = false;
        }

        System.out.println(score);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day9.txt"));
        String data = in.nextLine();
        char[] arr = data.toCharArray();



        Stack<Character> stack = new Stack<Character>();
        int num_chars = 0;
        boolean in_garbage = false;
        boolean negated = false;
        for(char c:arr)
        {

            if(in_garbage&&!negated&&c!='!'&&c!='>')
            {
                num_chars++;
            }

            if(c=='!')
            {
                negated = !negated;
                continue;
            }

            if(in_garbage&&!negated)
            {
                if(c=='>')
                {
                    in_garbage = false;
                }
            }
            else if(!negated)
            {
                switch(c)
                {
                    case '{':
                        stack.push(c);
                        break;
                    case '}':
                        stack.pop();
                        break;
                    case '<':
                        in_garbage = true;
                        break;
                }
            }
            negated = false;
        }

        System.out.println(num_chars);
    }
}