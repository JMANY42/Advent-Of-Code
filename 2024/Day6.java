import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[]args) throws IOException {      
        part1();
        part2();
    }

    public static void part1() throws IOException {
        Scanner test = new Scanner(new File("data/day6.txt"));

        int m = 0, n = 0;
        while(test.hasNextLine()) {
            n = test.nextLine().length();
            m++;
        }

        char[][] mat = new char[m][n];
        test.close();
        Scanner in = new Scanner(new File("data/day6.txt"));


        int guardR = 0, guardC = 0;
        for(int r=0; r<m; r++){
            String line = in.nextLine();
            for(int c=0; c<n; c++) {
                mat[r][c] = line.charAt(c);
                if(mat[r][c] == '^') {
                    guardR = r;
                    guardC = c;
                }
            }
        }
        in.close();

        move(mat,guardR,guardC, 0);

        int count = 0;
        for(int r=0; r<mat.length; r++) {
            for(int c=0; c<mat[r].length; c++) {
                if(mat[r][c] == 'X') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void move(char[][] mat, int r, int c, int dir) {
        switch(dir) {
            case 0:
                mat[r][c] = 'X';
                if(r == 0) {
                    return;
                }
                if(mat[r-1][c] == '#') {
                    move(mat, r, c, 1);
                }
                else {
                    move(mat, r-1, c, 0);
                }
                break;
            case 1:
                mat[r][c] = 'X';
                if(c == mat[0].length-1) {
                    return;
                }
                if(mat[r][c+1] == '#') {
                    move(mat, r, c, 2);
                }
                else {
                    move(mat, r, c+1, 1);
                }
                break;
            case 2:
                mat[r][c] = 'X';
                if(r == mat.length-1) {
                    return;
                }
                if(mat[r+1][c] == '#') {
                    move(mat, r, c, 3);
                }
                else {
                    move(mat, r+1, c, 2);
                }
                break;
            case 3:
                mat[r][c] = 'X';
                if(c == 0) {
                    return;
                }
                if(mat[r][c-1] == '#') {
                    move(mat, r, c, 0);
                }
                else {
                    move(mat, r, c-1, 3);
                }
                break;
        }
    }

    public static void part2() throws IOException {
        Scanner test = new Scanner(new File("data/day6.txt"));

        int m = 0, n = 0;
        while(test.hasNextLine()) {
            n = test.nextLine().length();
            m++;
        }

        char[][] mat = new char[m][n];
        char[][] matCopy = new char[m][n];
        test.close();
        Scanner in = new Scanner(new File("data/day6.txt"));

        int[][] dirMat = new int[m][n];
        int guardR = 0, guardC = 0;
        for(int r=0; r<m; r++){
            String line = in.nextLine();
            for(int c=0; c<n; c++) {
                mat[r][c] = line.charAt(c);
                dirMat[r][c] = -1;
                if(mat[r][c] == '^') {
                    guardR = r;
                    guardC = c;
                    mat[r][c] = '.';
                }
                matCopy[r][c] = mat[r][c];
            }
        }
        in.close();
        int count = 0;
        
        for(int r=0; r<m; r++) {
            for(int c=0; c<n; c++) {
                if((r == guardR && c == guardC) || mat[r][c] == '#') {
                    continue;
                }
                mat[r][c] = '#';
                if(move2(mat, dirMat, guardR, guardC, 0)) {
                    count++;
                }
                copy(mat, matCopy, dirMat);
            }
        }
        System.out.println(count);
    }

    public static boolean move2(char[][] mat, int[][] dirMat, int r, int c, int dir) {
        if(dirMat[r][c] == dir || mat[r][c] == (char)(48 + dir)) {
            return true;
        }
        if(mat[r][c] == '.') {
            mat[r][c] = (char)(48+dir);
        }
        else {
            dirMat[r][c] = dir;
        }

        switch(dir) {
            case 0:
                if(r == 0) {
                    return false;
                }
                if(mat[r-1][c] == '#') {
                    return move2(mat, dirMat, r, c, 1);
                }
                else {
                    return move2(mat, dirMat, r-1, c, 0);
                }
            case 1:
                if(c == mat[0].length-1) {
                    return false;
                }
                if(mat[r][c+1] == '#') {
                    return move2(mat, dirMat, r, c, 2);
                }
                else {
                    return move2(mat, dirMat, r, c+1, 1);
                }
            case 2:
                if(r == mat.length-1) {
                    return false;
                }
                if(mat[r+1][c] == '#') {
                    return move2(mat, dirMat, r, c, 3);
                }
                else {
                    return move2(mat, dirMat, r+1, c, 2);
                }
            case 3:
                if(c == 0) {
                    return false;
                }
                if(mat[r][c-1] == '#') {
                    return move2(mat, dirMat, r, c, 0);
                }
                else {
                    return move2(mat, dirMat, r, c-1, 3);
                }
        }
        return false;
    }

    public static void copy(char[][] to, char[][] from, int[][] dirMat)
    {
        for(int r=0; r<to.length; r++) {
            for(int c=0; c<to[r].length; c++) {
                to[r][c] = from[r][c];
                dirMat[r][c] = -1;
            }
        }
    }
}