import java.util.*;
import java.io.*;
public class Day1Template
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("day1.txt"));
        ArrayList<String> arl = new ArrayList<String>();
        while(in.hasNextLine())
        {
            arl.add(in.nextLine());
        }
        int sum = 0;
        for(String q:arl)
        {
            int first = -1010;
            int last = -1010;
            for(int i=0;i<q.length();i++)
            {
                String x = q.substring(i,i+1);
                try{
                    first = Integer.valueOf(x);
                } catch (Exception e){
                    try{
                    if(q.length()-i>=3&&q.substring(i,i+3).equals("one")){
                        first = 1;
                    }
                    else if(q.length()-i>=3&&q.substring(i,i+3).equals("two")){
                        first = 2;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("three")){
                        first = 3;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("four")){
                        first = 4;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("five")){
                        first = 5;
                    }
                    else if(q.length()-i>=3&&q.substring(i,i+3).equals("six")){
                        first = 6;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("seven")){
                        first = 7;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("eight")){
                        first = 8;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("nine")){
                        first = 9;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("zero")){
                        first = 0;
                    }}catch(Exception ex){}
                }
                if(first != -1010)
                {
                    break;
                }
            }
            for(int i=q.length()-1;i>=0;i--)
            {
                String x = q.substring(i,i+1);
                try{
                    last = Integer.valueOf(x);
                } catch (Exception e){
                    try{
                    if(q.length()-i-1>=3&&q.substring(i,i+3).equals("one")){
                        last = 1;
                    }
                    else if(q.length()-i>=3&&q.substring(i,i+3).equals("two")){
                        last = 2;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("three")){
                        last = 3;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("four")){
                        last = 4;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("five")){
                        last = 5;
                    }
                    else if(q.length()-i>=3&&q.substring(i,i+3).equals("six")){
                        last = 6;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("seven")){
                        last = 7;
                    }
                    else if(q.length()-i>=5&&q.substring(i,i+5).equals("eight")){
                        last = 8;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("nine")){
                        last = 9;
                    }
                    else if(q.length()-i>=4&&q.substring(i,i+4).equals("zero")){
                        last = 0;
                    }} catch(Exception ex){}
                }
                if(last != -1010)
                {
                    break;
                }
            }
            System.out.println(first+""+last);
            sum+=(first*10+last);
            System.out.println(sum);
        }
        System.out.println(sum);
    }
}