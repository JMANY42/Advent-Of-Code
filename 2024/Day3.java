import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[]args) throws IOException {
        part1();
        part2();
    }

    public static void part1() throws IOException{
        Scanner in = new Scanner(new File("data/day3.txt"));
        int total = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            int index = indexOf(line,"mul\\(\\d+,\\d+\\)");
            
            do {
                line = line.substring(index);
                total += Integer.valueOf(line.substring(4,line.indexOf(","))) * Integer.valueOf(line.substring(line.indexOf(",")+1,line.indexOf(")")));

                line = line.substring(line.indexOf(")")+1);
                index = indexOf(line,"mul\\(\\d+,\\d+\\)");
            } while((index != -1));
        }
        in.close();
        System.out.println(total);
    }

    public static void part2() throws IOException{
        Scanner in = new Scanner(new File("data/day3.txt"));
        int total = 0;
        boolean enabled = true;
        while(in.hasNextLine()) {
            
            String line = in.nextLine();
            if(!enabled) {
                line = "don't()"+line;
            }
            int indexMul = indexOf(line,"mul\\(\\d+,\\d+\\)");
            int indexDo = line.indexOf("do()");
            int indexDont = line.indexOf("don't()");
            while((indexMul != -1)) {
                indexMul = indexOf(line,"mul\\(\\d+,\\d+\\)");
                indexDo = line.indexOf("do()");
                indexDont = line.indexOf("don't()");


                if(indexDont < indexMul && indexDont != -1) {
                    if(indexDo != -1) {
                        line = line.substring(indexDo+4);
                        enabled = true;
                        continue;
                    }
                    else {
                        enabled = false;
                        break;
                    }
                }
                if(indexMul == -1) break;

                line = line.substring(indexMul);
                total += Integer.valueOf(line.substring(4,line.indexOf(","))) * Integer.valueOf(line.substring(line.indexOf(",")+1,line.indexOf(")")));

                line = line.substring(line.indexOf(")")+1);
            }
        }
        in.close();

        System.out.println(total);
    }

    public static int indexOf(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1; 
        }
    }
}
