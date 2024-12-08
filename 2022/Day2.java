import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day2.txt"));
        int score = 0;
        ArrayList<String> opponentList = new ArrayList<String>();
        opponentList.add("A");
        opponentList.add("B");
        opponentList.add("C");
        
        ArrayList<String> yours = new ArrayList<String>();
        yours.add("X");
        yours.add("Y");
        yours.add("Z");

        while(fileIn.hasNextLine())
        {
            String[] line = fileIn.nextLine().split(" ");
            String opponent = line[0];
            String response = line[1];

            int opNum = opponentList.indexOf(opponent);
            int result = yours.indexOf(response);

            if(result ==0)
            {
                int addNum = 0;
                if(opNum == 0)
                {
                    addNum = 2;
                }
                else if(opNum == 1)
                {
                    addNum = 0;
                }
                else if(opNum==2)
                {
                    addNum=1;
                }
                score+=addNum+1;
            }
            else if(result == 1)
            {
                score+=opNum+4;
            }
            else if(result ==2)
            {
                int addNum = 0;
                if(opNum == 0)
                {
                    addNum = 1;
                }
                else if(opNum == 1)
                {
                    addNum = 2;
                }
                else if(opNum==2)
                {
                    addNum=0;
                }
                score+=addNum+7;
            }

            
        }
        System.out.println(score);
    }
}