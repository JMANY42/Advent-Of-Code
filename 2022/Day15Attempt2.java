import java.util.*;
import java.io.*;
public class Day15Attempt2
{
    public static void main(String[]args) throws IOException
    {
        Scanner fileIn = new Scanner(new File("Day15.txt"));
        int row = 2000000;
        Set<Integer> taken = new HashSet<Integer>();
        Set<Integer> invalid = new HashSet<Integer>();
        long time = System.currentTimeMillis();
        while(fileIn.hasNextLine())
        {
            String[] data = fileIn.nextLine().split(" ");
            //System.out.println(Arrays.toString(data));
            int[] sensor = {Integer.valueOf(data[2].substring(2,data[2].length()-1)),Integer.valueOf(data[3].substring(2,data[3].length()-1))};
            int[] beacon = {Integer.valueOf(data[8].substring(2,data[8].length()-1)),Integer.valueOf(data[9].substring(2))};


            if(sensor[1]==row)
            {
                invalid.add(sensor[0]);
            }
            if(beacon[1]==row)
            {
                invalid.add(beacon[0]);
            }
            int distance = Math.abs(sensor[0]-beacon[0])+Math.abs(sensor[1]-beacon[1]);
   
            if(sensor[1]+distance>=row)
            {
                int over = sensor[1]+distance-row;
                if(sensor[1]>row)
                {
                    over=sensor[1]-row;
                    //System.out.println(over);
                    int numAddToSet = (distance-over)*2+1;
                    for(int i=0;i<numAddToSet;i++)
                    {
                        //System.out.println(sensor[0]-(distance-over)+i);
                        taken.add(sensor[0]-(distance-over)+i);
                    }
                }
                else{
                    //System.out.println(over);
                    int numAddToSet = over*2+1;
                    for(int i=0;i<numAddToSet;i++)
                    {
                        //System.out.println(sensor[0]-over+i);
                        taken.add(sensor[0]-over+i);
                    }
                }
                
            }
        }
        taken.removeAll(invalid);
        System.out.println(taken.size()+"\n\n\n");
        System.out.println((System.currentTimeMillis()-time)/1000.0);
        /*Iterator<Integer> x = taken.iterator();

        while(x.hasNext())
        {
            System.out.println(x.next());
        }*/
    }
}