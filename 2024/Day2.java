import java.io.*;
import java.util.*;

public class Day2 {
    public static void main(String[]args) throws IOException {
        part1();
        part2();
    }

    public static void part1() throws IOException{
        Scanner in = new Scanner(new File("data/day2.txt"));
        int safe = 0;
        while(in.hasNextLine()){
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            if(checkIncreasing(arr) || checkDecreasing(arr)){
                safe++;
            }
        }
        in.close();
        System.out.println(safe);
    }

    public static boolean checkIncreasing(int[] arr){
        for(int i=1; i<arr.length; i++){
            if(! (arr[i] > arr[i-1] && arr[i] <= arr[i-1]+3) ){
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkDecreasing(int[] arr){
        for(int i=1; i<arr.length; i++){
            if(! (arr[i] < arr[i-1] && arr[i-1] <= arr[i]+3) ){
                return false;
            }
        }
        return true;
    }


    public static void part2() throws IOException{
        Scanner in = new Scanner(new File("data/day2.txt"));
        int safe = 0;
        while(in.hasNextLine()){
            String line = in.nextLine();
            int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            if(checkIncreasing2(arr) || checkDecreasing2(arr)){
                safe++;
            }
        }
        in.close();
        System.out.println(safe);
    }

    public static boolean checkIncreasing2(int[] arr){
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)));

        for(int i=1; i<arr.length; i++){
            if(! (arr[i] > arr[i-1] && arr[i] <= arr[i-1]+3) ){
                return checkIncreasingSkip(list);
            }
        }
        return true;
    }

    public static boolean checkIncreasingSkip(ArrayList<Integer> list){
        int removed = -1;
        skipLoop: for(int skip=0; skip<list.size(); skip++){
            removed = list.remove(skip);
            for(int i=0; i<list.size()-1; i++){
                if(! (list.get(i+1) > list.get(i) && list.get(i+1) <= list.get(i)+3) ){
                    list.add(skip,removed);
                    continue skipLoop;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean checkDecreasing2(int[] arr){
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)));

        for(int i=1; i<arr.length; i++){
            if(! (arr[i] < arr[i-1] && arr[i-1] <= arr[i]+3) ){
                return checkDecreasingSkip(list);
            }
        }
        return true;
    }

    public static boolean checkDecreasingSkip(ArrayList<Integer> list){
    int removed = -1;
        skipLoop: for(int skip=0; skip<list.size(); skip++){
            removed = list.remove(skip);
            for(int i=0; i<list.size()-1; i++){
                if(! (list.get(i+1) < list.get(i) && list.get(i) <= list.get(i+1)+3)){
                    list.add(skip,removed);
                    continue skipLoop;
                }
            }
            return true;
        }
        return false;
    }
}