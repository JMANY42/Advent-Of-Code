import java.util.*;
import java.io.*;

public class Day13Attempt2
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day13.txt"));

        int index = 1;
        int indexSum = 0;
        outerLoop: while(fileIn.hasNextLine())
        {
            String one = fileIn.nextLine();
            String two = fileIn.nextLine();
            ArrayList<String> line1 = toArrayList(one.substring(1,one.length()-1));
            ArrayList<String> line2 = toArrayList(two.substring(1,two.length()-1));
            System.out.println(line1);
            System.out.println(line2);
            int runLength = Math.min(line1.size(),line2.size());
            boolean answer = false;
            l: for(int i=0;i<runLength;i++)
            {
                //loop through and run one method called check that handels everything
                int b = check(line1.get(i),line2.get(i));

                switch(b){
                    case 1:
                        System.out.println("in order");
                        answer = true;
                        indexSum+=index;
                        break l;
                    case 0:
                        System.out.println("cannot determine");
                        break;
                    case -1:
                        System.out.println("not in order");
                        answer = true;
                        break l;
                }
            }
            if(!answer)
            {
                if(line2.size()>line1.size())
                {
                    System.out.println("in order");
                    indexSum+=index;
                }
                else if(line1.size()>line2.size())
                {
                    System.out.println("not in order");
                }
                else
                {
                    System.out.println("cannot determine");
                }
            }
            index++;
            fileIn.nextLine();
        }
        System.out.println(indexSum);
    
    }

    public static int check(String x, String y)
    {
        boolean xIsInt = !x.contains("[");
        boolean yIsInt = !y.contains("[");
        //System.out.println(xIsInt);
        //System.out.println(yIsInt);

        if(xIsInt&&yIsInt)
        {
            int xint = Integer.valueOf(x);
            //System.out.println(y);
            int yint = Integer.valueOf(y);

            if(xint>yint)
            {
                return -1;
            }
            else if(xint<yint)
            {
                return 1;
            }
            return 0;
        }
        if(!xIsInt&&!yIsInt)
        {
            //System.out.println("here");
            ArrayList<String> xList = toArrayList(x.substring(1,x.length()-1));
            ArrayList<String> yList = toArrayList(y.substring(1,y.length()-1));
            int runLength = Math.min(xList.size(),yList.size());
            for(int i=0;i<runLength;i++)
            {
                int b = check(xList.get(i),yList.get(i));

                switch(b){
                    case 1:
                        //System.out.println("in order");
                        return 1;
                    case 0:
                        //System.out.println("cannot determine");
                        break;
                    case -1:
                        //System.out.println("not in order");
                        return -1;
                }
            }
            //System.out.println("here");
            if(xList.size()<yList.size())
            {
                return 1;
            }
            else if(xList.size()>yList.size())
            {
                return -1;
            }
        }
        if(!xIsInt&&yIsInt)
        {
            //x is a list but y is an integer
            
            ArrayList<String> xList = toArrayList(x.substring(1,x.length()-1));
            if(xList.size()==0)
            {
                return 1;
            }
            int c = check(xList.get(0),String.valueOf(y));

            switch(c){
                case 1:
                    return 1;
                case 0:
                    if(xList.size()>1){
                        return -1;
                    }
                    else if(xList.size()<1){
                        return 1;
                    }
                    return 0;
                case -1:
                    return -1;
            }
        }
        if(xIsInt&&!yIsInt)
        {
            //x is an Integer but y is a list
            ArrayList<String> yList = toArrayList(y.substring(1,y.length()-1));
            //System.out.println("a  "+yList);
            //System.out.println(yList.get(0));
            if(yList.size()==0)
            {
                return -1;
            }
            int c = check(String.valueOf(x),yList.get(0));

            switch(c){
                case 1:
                    return 1;
                case 0:
                    if(yList.size()>1){
                        return 1;
                    }
                    else if(yList.size()<1){
                        return -1;
                    }
                    return 0;
                case -1:
                    return -1;
            }
        }
        return 0;

    }
    public static ArrayList<String> toArrayList(String x)
    {
        ArrayList<String> sal = new ArrayList<String>();
        int salIndex = 0;
        //sal.add("");
        ArrayList<Integer> closeBracketList = new ArrayList<Integer>();
        int closeBracketIndex = 0;
        for(int i=0;i<x.length();i++)
        {
            if(x.charAt(i)==']')
            {
                closeBracketList.add(i);
            }
        }
        for(int i=0;i<x.length();i++)
        {
            if(x.charAt(i)==',')
            {
                salIndex++;
            }
            else if(x.charAt(i)=='[')
            {
                int openBrackets = 0;
                int bracketIndex = -1;
                for(int j=i+1;j<x.length();j++)
                {
                    if(x.charAt(j)=='[')
                    {
                        openBrackets++;
                    }
                    if(x.charAt(j)==']'&&openBrackets>0)
                    {
                        openBrackets--;
                    }
                    else if(x.charAt(j)==']'&&openBrackets==0)
                    {
                        bracketIndex=j;
                        break;
                    }
                }

                sal.add(x.substring(i,bracketIndex+1));
                i=bracketIndex+1;
                //closeBracketIndex++;
            }
            else
            {
                int end = x.length();
                //System.out.println(end);
                if(x.substring(i).indexOf(",")!=-1)
                {
                    end = x.substring(i).indexOf(",")+i;
                    //System.out.println(x.substring(i));
                }
                //System.out.println(end);
                sal.add(x.substring(i,end));
                i+=end-i;
            }
        }
        //System.out.println(sal);
        return sal;
    }
}