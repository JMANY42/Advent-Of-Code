import java.util.*;
import java.io.*;

public class Day17
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        int steps = 386;
        
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        buffer.add(0);

        int current_position = 0;
        for(int i=1;i<=2017;i++)
        {
            current_position = (current_position + steps) % buffer.size();
            buffer.add(current_position + 1,i);
            current_position = (current_position + 1) % buffer.size();
        }

        System.out.println(buffer.get((buffer.indexOf(2017)+1) % buffer.size()));
    }

    public static void partTwo() throws IOException
    {

        int steps = 386;
        
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        buffer.add(0);

        int current_position = 0;
        int buffer_size = 1;
        for(int i=1;i<=50000000;i++)
        {
            current_position = (current_position + steps) % buffer_size;
            if(current_position == 0)
            {
                buffer.add(current_position + 1,i);
            }
            buffer_size++;
            current_position = (current_position + 1) % buffer_size;
        }

        System.out.println(buffer.get((buffer.indexOf(0)+1) % buffer_size));


        //bad solution... though of above solution while this one was running
        //still gave correct answer
        
        // int steps = 386;
        
        // Node head = new Node(0,null);
        // Node curr = head;
        // curr.next = curr;
        // for(int i=1;i<=50000000;i++)
        // {
        //     if(i%10000==0) System.out.println(i);
        //     for(int j=0;j<steps;j++)
        //     {
        //         curr = curr.next;
        //     }

        //     Node temp = new Node(i,curr.next);
        //     curr.next = temp;
        //     curr = curr.next;
        // }

        // curr = head;

        // while(curr.value != 0)
        // {
        //     curr = curr.next;
        // }
        // System.out.println(curr.next.value);
    }
}



class Node
{
    int value;
    Node next;

    public Node(int value, Node next)
    {
        this.value = value;
        this.next = next;
    }


    public int getValue()
    {
        return value;
    }

    public Node getNext()
    {
        return next;
    }
}