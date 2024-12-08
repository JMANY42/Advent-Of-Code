import java.util.*;
import java.io.*;

public class Day8Part2
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day8.txt"));

        String instructions = in.nextLine();

        in.nextLine();

        HashMap<String,Location2> Location2s = new HashMap<String,Location2>();
        while(in.hasNextLine())
        {
            String name = in.next();
            String rest = in.nextLine();
            rest = rest.substring(rest.indexOf("(")+1, rest.indexOf(")"));
            System.out.println(rest);
            String[] lr = rest.split(", ");

            Location2s.put(name, new Location2(name, lr[0], lr[1]));
        }

        ArrayList<Location2> currents = new ArrayList<Location2>();


        for(Location2 l:Location2s.values())
        {
            l.setLeftNode(Location2s.get(l.getLeftName()));
            l.setRightNode(Location2s.get(l.getRightName()));


            if(l.getName().charAt(2)=='A')
            {
                currents.add(l);
            }
        }
        int index = 0;
        int count = 0;

        //System.out.println(currents);

        while(!allEndInZ(currents))
        {
            //System.out.println(currents);
            
            for(int i=0;i<currents.size();i++)
            {
                if(instructions.charAt(index)=='L')
                {
                    currents.set(i, currents.get(i).getLeftNode());
                }
                else
                {
                    currents.set(i, currents.get(i).getRightNode());
                }
                //System.out.println("2: "+currents);
            }

            index++;
            if(index==instructions.length())
            {
                index=0;
            }
            count++;
        }
        System.out.println(count);
    }

    public static boolean allEndInZ(ArrayList<Location2> list)
    {
        for(Location2 l : list)
        {
            if(l.getName().charAt(2)!='Z')
            {
                return false;
            }
        }
        return true;
    }
}


class Location2
{
    private String name;
    private String leftName;
    private String rightName;
    private int hashCode;
    private Location2 leftNode, rightNode;

    public Location2(String n, String l, String r)
    {
        name = n;
        leftName = l;
        rightName = r;
        hashCode = Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location2 that = (Location2) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public String toString()
    {
        return name;
    }


    public void setLeftNode(Location2 ln)
    {
        leftNode = ln;
    }
    public void setRightNode(Location2 rn)
    {
        rightNode = rn;
    }
    public String getName()
    {
        return name;
    }
    public String getLeftName()
    {
        return leftName;
    }
    public String getRightName()
    {
        return rightName;
    }
    public Location2 getLeftNode()
    {
        return leftNode;
    }
    public Location2 getRightNode()
    {
        return rightNode;
    }
}