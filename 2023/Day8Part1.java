import java.util.*;
import java.io.*;

public class Day8Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Day8.txt"));

        String instructions = in.nextLine();

        in.nextLine();

        HashMap<String,Location> locations = new HashMap<String,Location>();
        while(in.hasNextLine())
        {
            String name = in.next();
            String rest = in.nextLine();
            rest = rest.substring(rest.indexOf("(")+1, rest.indexOf(")"));
            System.out.println(rest);
            String[] lr = rest.split(", ");

            locations.put(name, new Location(name, lr[0], lr[1]));
        }

        for(Location l:locations.values())
        {
            l.setLeftNode(locations.get(l.getLeftName()));
            l.setRightNode(locations.get(l.getRightName()));
        }

        

        Location current = locations.get("AAA");
        int index = 0;
        int count = 0;

        while(!current.getName().equals("ZZZ"))
        {
            System.out.println(current);
            if(instructions.charAt(index)=='L')
            {
                current = current.getLeftNode();
            }
            else
            {
                current = current.getRightNode();
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
}


class Location
{
    private String name;
    private String leftName;
    private String rightName;
    private int hashCode;
    private Location leftNode, rightNode;

    public Location(String n, String l, String r)
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
        Location that = (Location) o;
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


    public void setLeftNode(Location ln)
    {
        leftNode = ln;
    }
    public void setRightNode(Location rn)
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
    public Location getLeftNode()
    {
        return leftNode;
    }
    public Location getRightNode()
    {
        return rightNode;
    }
}