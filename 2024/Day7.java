import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String[]args) throws IOException {      
        part1();
        part2();
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("data/day7.txt"));
        
        long count = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] strArr = line.split(": ");
            long value = Long.valueOf(strArr[0]);
            int[] numbers = Arrays.stream(strArr[1].split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean equals = false;
            for(int operations=0; operations < Math.pow(2,numbers.length-1); operations++) {
                long runningTotal = numbers[0];
                for(int i=1; i<numbers.length; i++) {
                    //0 - plus
                    //1 - multiply
                    if(((operations >> (i-1)) & 1) == 0) {
                        runningTotal += numbers[i];
                    }
                    else {
                        runningTotal *= numbers[i];
                    }
                }
                if(runningTotal == value) {
                    equals = true;
                }
            }
            if(equals) {
                count += value;
            }
        }
        in.close();
        System.out.println(count);
    }   

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("data/day7.txt"));
        
        long count = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] strArr = line.split(": ");
            long value = Long.valueOf(strArr[0]);
            int[] numbers = Arrays.stream(strArr[1].split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean equals = false;
            int[] operations = new int[numbers.length-1];
            for(int operationNum=0; operationNum < Math.pow(3,numbers.length-1); operationNum++) {
                long runningTotal = numbers[0];
                for(int i=1; i<numbers.length; i++) {
                    //0 - plus
                    //1 - multiply
                    if(operations[i-1] == 0) {
                        runningTotal += numbers[i];
                    }
                    else if(operations[i-1] == 1){
                        runningTotal *= numbers[i];
                    } 
                    else {
                        runningTotal = Long.valueOf(String.valueOf(runningTotal) + String.valueOf(numbers[i]));
                    }
                }
                if(runningTotal == value) {
                    equals = true;
                }
                increment(operations);
            }
            if(equals) {
                count += value;
            }
        }
        in.close();
        System.out.println(count);
    }

    public static void increment(int[] arr) {
        if(arr.length == 0) {
            return;
        }
        if(arr[0] < 2) {
            arr[0]++;
            return;
        }
        arr[0] = 0;
        int[] arr2 = new int[arr.length-1];
        for(int i=0; i<arr2.length; i++) {
            arr2[i] = arr[i+1];
        }
        increment(arr2);
        for(int i=0; i<arr2.length; i++) {
            arr[i+1] = arr2[i];
        }
        return;
    }
}