import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Day5
{
    public static void main(String[]args) throws IOException { 
        part1();
        part2();
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("data/day5.txt"));
        HashMap<Integer,Set<Integer>> map = new HashMap<Integer,Set<Integer>>();

        while(in.hasNextLine()) {
            String line = in.nextLine();
            if(line.length() == 0) {
                break;
            }
            int key = Integer.valueOf(line.substring(0,line.indexOf("|")));
            int value = Integer.valueOf(line.substring(line.indexOf("|")+1));
            if(!map.containsKey(key)) {
                Set<Integer> s = new HashSet<Integer>();
                s.add(value);
                map.put(key,s);
            }
            else {
                map.get(key).add(value);
            }
        }

        int total = 0;
  next: while(in.hasNextLine()) {
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> update = Arrays.stream(arr).boxed().collect(Collectors.toSet());

            for(Integer key:map.keySet()){
                int first = key;
                Set<Integer> values = map.get(key);

                for(Integer value:values) {
                    if(update.contains(first) && update.contains(value)) {
                        if(indexOf(arr,first) > indexOf(arr,value)) {
                            continue next;
                        }
                    }
                }
            }
            total += arr[arr.length/2];
        }
        in.close();
        System.out.println(total);
    }

    public static int indexOf(int[] arr, int value) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("data/day5.txt"));
        HashMap<Integer,Set<Integer>> map = new HashMap<Integer,Set<Integer>>();

        while(in.hasNextLine()) {
            String line = in.nextLine();
            if(line.length() == 0) {
                break;
            }
            int key = Integer.valueOf(line.substring(0,line.indexOf("|")));
            int value = Integer.valueOf(line.substring(line.indexOf("|")+1));
            if(!map.containsKey(key)) {
                Set<Integer> s = new HashSet<Integer>();
                s.add(value);
                map.put(key,s);
            }
            else {
                map.get(key).add(value);
            }
        }

        int total = 0;
        
        while(in.hasNextLine()) {
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> update = Arrays.stream(arr).boxed().collect(Collectors.toSet());

            boolean swapped = false;
            loop: while(true) {
                for(Integer key:map.keySet()){
                    Set<Integer> values = map.get(key);

                    for(Integer value:values) {
                        if(update.contains(key) && update.contains(value)) {
                            if(indexOf(arr,key) > indexOf(arr,value)) {
                                System.out.println("swap");
                                swap(arr,indexOf(arr,key),indexOf(arr,value));
                                swapped = true;
                                continue loop;
                            }
                        }
                    }
                }
                    break;
                
            }
            System.out.println();
            if(swapped) {
                total += arr[arr.length/2];
            }
        }
        in.close();
        System.out.println(total);
    }

    public static void swap(int[] arr, int index1, int index2) {
        arr[index1] ^= arr[index2];
        arr[index2] ^= arr[index1];
        arr[index1] ^= arr[index2];
    }
}