import java.util.*;
import java.io.*;
public class Day10
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day10.txt"));
        int x = 1;
        int i=1;
        int total=0;
        boolean pause = false;
        int value = 0;
        String[][] screen = new String[6][40];
        while(fileIn.hasNextLine())
        {

            //System.out.println("\ni: "+i);
            screen = updateScreen(screen,x,i);
            if((i+20)%40==0)
            {
                //System.out.println("Total: "+total);
            }
            if(pause)
            {
                pause = false;
                i++;
                x+=value;
                //System.out.println("Value: "+value);
                //System.out.println("X: "+x);
                continue;
            }
            String line = fileIn.nextLine();
            if(line.charAt(0)=='n')
            {
                i++;
                continue;
            }
            else
            {
                pause = true;
                value = Integer.valueOf(line.substring(5));
                i++;
            }
        }
        for(String[] q:screen)
        {
            System.out.println(Arrays.toString(q));
        }
    }
    public static String[][] updateScreen(String[][] screen, int x, int i)
    {
        if(Math.abs(x-(i-1)%40)<=1)
        {
            screen[(i-1)/40][(i-1)%40]="#";
        }
        else
        {
            screen[(i-1)/40][(i-1)%40]=".";
        }
        return screen;
    }
}