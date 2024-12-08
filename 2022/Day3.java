import java.util.*;
import java.io.*;
public class Day3
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day3.txt"));

        int sum = 0;
        while(fileIn.hasNextLine())
        {
            String sack1 = fileIn.nextLine();
            String sack2 = fileIn.nextLine();
            String sack3 = fileIn.nextLine();

            Set<Character> setSack1 = new HashSet<Character>();
            for(int i=0;i<sack1.length();i++)
            {
                setSack1.add(sack1.charAt(i));
            }

            Set<Character> setSack2 = new HashSet<Character>();
            for(int i=0;i<sack2.length();i++)
            {
                setSack2.add(sack2.charAt(i));
            }

            Set<Character> setSack3 = new HashSet<Character>();
            for(int i=0;i<sack3.length();i++)
            {
                setSack3.add(sack3.charAt(i));
            }
            setSack2.retainAll(setSack3);
            setSack1.retainAll(setSack2);

            Character[] setArr = setSack1.toArray(new Character[setSack1.size()]);

            for(char q:setArr)
            {
                if(q>=97)
                {
                    sum+=(q-96);
                }
                else{
                    sum+=(q-38);
                }
            }          
        }
        System.out.println(sum);
    }
}