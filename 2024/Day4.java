import java.io.*;
import java.util.*;


public class Day4 {
    public static void main(String[]args) throws IOException {
        part1();
        part2();
    }

    public static void part1() throws IOException {
        Scanner test = new Scanner(new File("data/day4.txt"));

        int m = 0, n = 0;
        while(test.hasNextLine()) {
            n = test.nextLine().length();
            m++;
        }

        char[][] mat = new char[m][n];
        test.close();
        Scanner in = new Scanner(new File("data/day4.txt"));

        for(int r=0; r<m; r++){
            String line = in.nextLine();
            for(int c=0; c<n; c++) {
                mat[r][c] = line.charAt(c);
            }
        }
        in.close();

        int total = 0;
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++) {
                total += numMatch("XMAS", r, c, mat);
            }
        }
        System.out.println(total);

    }

    public static int numMatch(String word, int r, int c, char[][] mat) {
        if(r < 0 || r > mat.length-1 || c < 0 || c > mat[0].length-1 || word.length() == 0 || word.charAt(0) != mat[r][c]) {
            return 0;
        }
        String word2 = word.substring(1);
        return  match(word2, r-1, c, mat, 0) +
                match(word2, r-1, c+1, mat, 1) +
                match(word2, r, c+1, mat, 2) +
                match(word2, r+1, c+1, mat, 3) +
                match(word2, r+1, c, mat, 4) +
                match(word2, r+1, c-1, mat, 5) +
                match(word2, r, c-1, mat, 6) +
                match(word2, r-1, c-1, mat, 7);

    }

    public static int match(String word, int r, int c, char[][] mat, int dir) {
        if(r < 0 || r > mat.length-1 || c < 0 || c > mat[0].length-1 || word.length() == 0 || word.charAt(0) != mat[r][c]) {
            return 0;
        }
        if(word.length() == 1 && word.charAt(0) == mat[r][c]) {
            return 1;
        }
        String word2 = word.substring(1);
        switch(dir){
            case 0:
                return match(word2, r-1, c, mat, 0);
            case 1:
                return match(word2, r-1, c+1, mat, 1);
            case 2:
                return match(word2, r, c+1, mat, 2);
            case 3:
                return match(word2, r+1, c+1, mat, 3);
            case 4:
                return match(word2, r+1, c, mat, 4);
            case 5:
                return match(word2, r+1, c-1, mat, 5);
            case 6:
                return match(word2, r, c-1, mat, 6);
            case 7:
                return match(word2, r-1, c-1, mat, 7);
        }
        return -1000;
    }

    public static void part2() throws IOException{
        Scanner test = new Scanner(new File("data/day4.txt"));

        int m = 0, n = 0;
        while(test.hasNextLine()) {
            n = test.nextLine().length();
            m++;
        }

        char[][] mat = new char[m][n];
        test.close();
        Scanner in = new Scanner(new File("data/day4.txt"));

        for(int r=0; r<m; r++){
            String line = in.nextLine();
            for(int c=0; c<n; c++) {
                mat[r][c] = line.charAt(c);
            }
        }
        in.close();

        int total = 0;
        for(int r=1; r<m-1; r++){
            for(int c=1; c<n-1; c++) {
                total += checkXMAS(mat, r, c);
            }
        }
        System.out.println(total);
    }

    public static int checkXMAS(char[][] mat, int r, int c) {
        if(mat[r][c] == 'A' && (mat[r-1][c-1] == 'M' && mat[r+1][c+1] == 'S' || mat[r-1][c-1] == 'S' && mat[r+1][c+1] == 'M') && (mat[r-1][c+1] == 'M' && mat[r+1][c-1] == 'S' || mat[r-1][c+1] == 'S' && mat[r+1][c-1] == 'M')) {
            return 1;
        }
        return 0;
    }
}
