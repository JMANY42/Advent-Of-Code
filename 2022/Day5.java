import java.util.*;
import java.io.*;
public class Day5
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day5.txt"));
        String nextLine = fileIn.nextLine();
        Stack<String>[] arr1 =  new Stack[nextLine.length()/4];
        Stack<String>[] arr = new Stack[nextLine.length()/4];

        for(int i=0;i<arr1.length;i++)
        {
            arr1[i]=new Stack<String>();
            arr[i]=new Stack<String>();
        }

        for(int j=0;j<8;j++)
        {
            nextLine=fileIn.nextLine();
            for(int i=0;i<nextLine.length();i++)
            {
                if(!nextLine.substring(i,i+1).equals(" ")&&!nextLine.substring(i,i+1).equals("[")&&!nextLine.substring(i,i+1).equals("]"))
                {
                    arr1[i/4].push(nextLine.substring(i,i+1));
                }
            }
        }

        for(int i=0;i<arr1.length;i++)
        {
            while(!arr1[i].isEmpty())
            {
                arr[i].push((arr1[i].pop()));
            }
        }

        while(fileIn.hasNextLine())
        {
            nextLine = fileIn.nextLine();

            String[] line = nextLine.split(" ");

            System.out.println(Arrays.toString(arr));
            String[] temp = new String[Integer.valueOf(line[1])];
            for(int i=Integer.valueOf(line[1])-1;i>=0;i--)
            {
                temp[i]=arr[Integer.valueOf(line[3])-1].pop();
            }
            for(String q:temp)
            {
                arr[Integer.valueOf(line[5])-1].push(q);
            }
        }
    
    for(int i=0;i<arr.length;i++)
    {
        System.out.print(arr[i].pop());
    }
    }
}