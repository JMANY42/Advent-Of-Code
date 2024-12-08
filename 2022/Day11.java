import java.util.*;
import java.io.*;
public class Day11
{
    public static void main(String[]args) throws IOException
    {
        Scanner numMonkeys = new Scanner(new File("Day11.txt"));
        int nM=1;
        int multiple = 1;
        while(numMonkeys.hasNextLine())
        {
            nM++;
            numMonkeys.nextLine();
        }
        Monkey[] monkeyArr = new Monkey[nM/7];

        Scanner fileIn = new Scanner(new File("Day11.txt"));
        for(int i=0;i<monkeyArr.length;i++)
        {
            fileIn.nextLine();
            monkeyArr[i] = new Monkey(fileIn.nextLine(),fileIn.nextLine(),fileIn.nextLine(),fileIn.nextLine(),fileIn.nextLine());
            if(i!=monkeyArr.length-1)
            {
                fileIn.nextLine();
            }
            multiple*=monkeyArr[i].getTestNumber();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("write.txt"));

        for(int i=0;i<monkeyArr.length;i++)
        {
            monkeyArr[i].setMultiple(multiple);
            monkeyArr[i].setWriter(writer);
        }
        for(int i=0;i<10000;i++)
        {
            System.out.println("loop: "+i);
            for(int j=0;j<monkeyArr.length;j++)
            {
                while(monkeyArr[j].getItems().size()!=0)
                {
                    long[] addArr = monkeyArr[j].inspect();
                    monkeyArr[(int) addArr[1]].addItem(addArr[0]);
                }
            }
            for(int j=0;j<monkeyArr.length;j++)
            {
                System.out.println(monkeyArr[j].getInspected());
            }
            System.out.println();
        }

        long max1 = 0;
        long max2 = 0;
        for(int i=0;i<monkeyArr.length;i++)
        {
            if(monkeyArr[i].getInspected()>max1)
            {
                max2=max1;
                max1=monkeyArr[i].getInspected();
            }
            else if(monkeyArr[i].getInspected()>max2)
            {
                max2=monkeyArr[i].getInspected();
            }
        }
        System.out.println(max1);
        System.out.println(max2);
        System.out.println(max1*max2);
    }
}

class Monkey
{
    private ArrayList<Long> items;
    private String operationString;
    private String testString;
    private String trueString;
    private String falseString;
    private int inspected;
    private int multiple;
    private BufferedWriter writer;

    public Monkey(String itemString, String os, String testS, String ts, String fs)
    {
        inspected = 0;
        items = new ArrayList<Long>();
        itemString = itemString.substring(18);
        String[] itemStringArr = itemString.split(", ");
        for(String q:itemStringArr)
        {
            items.add(Long.valueOf(q));
        }
        operationString = os;
        testString = testS;
        trueString = ts;
        falseString = fs;
        multiple = 1;
    }

    public long[] inspect() throws IOException
    {
        inspected++;
        if(operationString.charAt(23)=='*')
        {
            if(operationString.charAt(25)!='o')
            {
                items.set(0,(items.get(0)*Integer.valueOf(operationString.substring(25))));
            }
            else
            {
                items.set(0,(items.get(0)*items.get(0)));
            }
        }
        else
        {
            if(operationString.charAt(25)!='o')
            {
                items.set(0,(items.get(0)+Integer.valueOf(operationString.substring(25))));
            }
            else
            {
                items.set(0,(items.get(0)+items.get(0)));
            }
        }
        int testNumber = Integer.valueOf(testString.substring(21));
        //System.out.println("multiple: "+multiple);
        //System.out.println("before: "+items.get(0));
        //writer.write("before: "+items.get(0)+"\n");

        while(items.get(0)>multiple)
        {
            //System.out.println(items.get(0));
            //writer.write(String.valueOf(items.get(0))+"\n");
            items.set(0,items.get(0)-multiple);
        }
        //items.set(0,items.get(0)+multiple);

        //items.set(0,(items.get(0)-maxMultiply*testNumber));
        //System.out.println("after: "+items.get(0));
        //writer.write("after: "+items.get(0)+"\n");

        //System.out.println(maxMod);
        if((items.get(0))%(testNumber)==0)
        {
            long[] returnArr = {items.remove(0),Long.valueOf(trueString.substring(29))};
            return returnArr;
        }
        else
        {
            long[] returnArr = {items.remove(0),Long.valueOf(falseString.substring(30))};
            return returnArr;
        }
    }

    public void addItem(long x)
    {
        items.add(x);
    }

    public ArrayList<Long> getItems()
    {
        return items;
    }

    public int getInspected()
    {
        return inspected;
    }

    public int getTestNumber()
    {
        return Integer.valueOf(testString.substring(21));
    }

    public void setMultiple(int x)
    {
        multiple = x;
    }

    public void setWriter(BufferedWriter x)
    {
        writer = x;
    }
}