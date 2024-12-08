import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day16.txt"));
        String line = in.nextLine();
        String[] moves = line.split(",");
        
        char[] letters = new char[16];
        for(int i=0;i<letters.length;i++)
        {
            letters[i] = (char)(i + 97);
        }


        for(int i=0;i<moves.length;i++)
        {
            switch(moves[i].charAt(0))
            {
                case 's':
                    letters = swap(letters,Integer.valueOf(moves[i].substring(1)));
                    break;
                case 'x':
                    int first = Integer.valueOf(moves[i].substring(1,moves[i].indexOf("/")));
                    int second = Integer.valueOf(moves[i].substring(moves[i].indexOf("/")+1));
                    
                    char temp = letters[first];
                    letters[first] = letters[second];
                    letters[second] = temp;
                    break;
                case 'p':
                    int c1 = indexOf(letters,moves[i].charAt(1));
                    int c2 = indexOf(letters,moves[i].charAt(3));

                    char t = letters[c1];
                    letters[c1] = letters[c2];
                    letters[c2] = t;
                    break;
            }
        }

        for(char c:letters)
        {
            System.out.print(c);
        }
        System.out.println();
    }

    public static int indexOf(char[] arr, char c)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==c)
            {
                return i;
            }
        }
        return -1;
    }

    public static char[] swap(char[] arr, int n)
    {
        char[] ret = new char[arr.length];

        for(int i=0;i<arr.length;i++)
        {
            ret[i] = arr[(i + (arr.length-n)) % arr.length];
        }
        return ret;
    }

    public static void partTwo() throws IOException
    {
        ArrayList<char[]> previous_positions = new ArrayList<char[]>();

        Scanner in = new Scanner(new File("day16.txt"));
        String line = in.nextLine();
        String[] moves = line.split(",");
        
        char[] letters = new char[16];
        for(int i=0;i<letters.length;i++)
        {
            letters[i] = (char)(i + 97);
        }

        char[] letter_copy = new char[letters.length];
        for(int i=0;i<letters.length;i++)
        {
            letter_copy[i] = letters[i];
        }
        previous_positions.add(letter_copy);
        for(int l=0;l<1_000_000_000;l++)
        for(int i=0;i<moves.length;i++)
        {
            switch(moves[i].charAt(0))
            {
                case 's':
                    letters = swap(letters,Integer.valueOf(moves[i].substring(1)));
                    break;
                case 'x':
                    int first = Integer.valueOf(moves[i].substring(1,moves[i].indexOf("/")));
                    int second = Integer.valueOf(moves[i].substring(moves[i].indexOf("/")+1));
                    
                    char temp = letters[first];
                    letters[first] = letters[second];
                    letters[second] = temp;
                    break;
                case 'p':
                    int c1 = indexOf(letters,moves[i].charAt(1));
                    int c2 = indexOf(letters,moves[i].charAt(3));

                    char t = letters[c1];
                    letters[c1] = letters[c2];
                    letters[c2] = t;
                    break;
            }

            for(int j=0;j<letters.length;j++)
            {
                letter_copy[j] = letters[j];
            }

            if(!contains(previous_positions,letter_copy))
            {
                previous_positions.add(letter_copy);
                System.out.println("contains!");
            }
        }

        for(char c:letters)
        {
            System.out.print(c);
        }
    }

    public static boolean contains(ArrayList<char[]> list, char[] arr)
    {
        outer_loop: for(char[] q:list)
        {
            for(int i=0;i<q.length;i++)
            {
                if(q[i]!=arr[i])
                {
                    continue outer_loop;
                }
            }
            return true;
        }
        return false;
    }
}