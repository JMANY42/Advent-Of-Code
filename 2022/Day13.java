import java.util.*;
import java.io.*;
public class Day13
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day13.txt"));

        int outOfOrder = 0;
        int inOrder = 0;

        while(fileIn.hasNextLine())
        {
            String preline = fileIn.nextLine();
            //System.out.println(preline);
            //System.out.println(preline.substring(1,preline.length()));
            //System.out.println(preline.substring(1,preline.length()-1));
            String line=preline.substring(1,preline.length()-1);
            
            ArrayList<String> arl1 = intoArrayList(line);
            for(String q:arl1)
 {
                System.out.println(q);
            }
            System.out.println();

            String preline2 = fileIn.nextLine();
            //System.out.println(preline2.substring(1,preline2.length()-1));

            String line2=preline2.substring(1,preline2.length()-1);
            
            ArrayList<String> arl2 = intoArrayList(line2);
            for(String q:arl2)
            {
                System.out.println(q);
            }
            fileIn.nextLine();
            System.out.println();
            //System.out.println(arl1);
            arl1.remove(0);
            arl2.remove(0);
            /*for(int i=0;i<Math.min(arl1.size(),arl2.size());i++)
            {
                System.out.println(checkString(arl1.get(i),arl2.get(i)));
            }*/
            //System.out.println(arl1);
            //System.out.print
            System.out.println(checkArrayList(arl1,arl2));





        }
    }

    public static ArrayList<String> intoArrayList(String x)
    {
        ArrayList<String> sal = new ArrayList<String>();
        int salIndex = 0;
        sal.add("");
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
                sal.add(x.substring(i,i+1));
            }
        }
        sal.remove(0);
        return sal;
    }

    public static int checkString(String x, String y)
    {
        int xint = -1;
        int yint = -1;
        boolean xIsInt = true;
        boolean yIsInt = true;

        try{
            xint = Integer.valueOf(x);
        } catch(Exception e)
        {
            xIsInt = false;
            System.out.println("error x");
        }
        try{
            yint = Integer.valueOf(y);
        } catch(Exception e)
        {
            yIsInt = false;
            System.out.println("error y");

        }

        

        if(xIsInt&&yIsInt)
        {
            if(xint<yint)
            {
                return 1;
            }
            else if(xint>yint)
            {
                return -1;
            }
            return 0;
        }
        else if(xIsInt&&!yIsInt)
        {
            /*System.out.println("x: "+x);
            System.out.println("y: "+y);
            ArrayList<String> xNewList = intoArrayList(x.substring(1,x.length()-1));
            System.out.println("x new: "+xNewList);
            ArrayList<String> yNewList = intoArrayList(y.substring(1,y.length()-1));
            System.out.println("y new: "+yNewList);
            int c = checkArrayList(xNewList,yNewList);*/

            int c = checkListAndInt(intoArrayList(y),xint);

            return c;
        }
        else if(!xIsInt&&yIsInt)
        {
            /*System.out.println("x: "+x);
            System.out.println("y: "+y);
            ArrayList<String> xNewList = intoArrayList(x.substring(1,x.length()-1));
            System.out.println("x new: "+xNewList);
            ArrayList<String> yNewList = intoArrayList(y.substring(1,y.length()-1));
            System.out.println("y new: "+yNewList);
            int c = checkArrayList(xNewList,yNewList);*/

            int c = checkListAndInt(intoArrayList(x),yint);

            return c;
        }
        else if(!xIsInt&&!yIsInt)
        {
            System.out.println("x: "+x);
            System.out.println("y: "+y);
            ArrayList<String> xNewList = intoArrayList(x.substring(1,x.length()-1));
            System.out.println("x new: "+xNewList);
            ArrayList<String> yNewList = intoArrayList(y.substring(1,y.length()-1));
            System.out.println("y new: "+yNewList);
            int c = checkArrayList(xNewList,yNewList);

            return c;
        }
        return 0;
    }

    public static int checkArrayList(ArrayList<String> x, ArrayList<String> y)
    {
        for(int i=0;i<Math.min(x.size(),y.size());i++)
        {
            return checkString(x.get(i),y.get(i));
        }
        if(x.size()==y.size())
        {
            return 0;
        }
        if(x.size()<y.size())
        {
            return 1;
        }
        return -1;
    }

    public static int checkListAndInt(ArrayList<String> l, int x)
    {
        //check string list at 0 and x
        //if same then look at length
        System.out.println(l);
        System.out.println(l.get(0));
        int c = checkString(l.get(0),String.valueOf(x));

        if(c==1||c==-1)
        {
            return c;
        }
        else if(l.size()>1)
        {
            return -1;
        }
        return 0;
    }
}