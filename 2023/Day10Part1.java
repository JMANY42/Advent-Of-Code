import java.util.*;
import java.io.*;

public class Day10Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner getLines = new Scanner(new File("Day10.txt"));

        int numRows = 0;
        int numCols = 0;
        while(getLines.hasNextLine())
        {
            numRows++;
            numCols = getLines.nextLine().length();
        }

        char[][] mat = new char[numRows][numCols];

        Scanner in = new Scanner(new File("Day10.txt"));
        for(int i=0;i<numRows;i++)
        {
            mat[i] = in.nextLine().toCharArray();
        }

        int startRow = 0;
        int startCol = 0;
        for(int r=0;r<numRows;r++)
        {
            for(int c=0;c<numCols;c++)
            {
                if(mat[r][c]=='S')
                {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        ArrayList<int[]> path = new ArrayList<int[]>();
        int[] init = {startRow, startCol,0};
        path.add(init);

        int totalLength = 0;
        while(!path.isEmpty())
        {
            int[] arr = path.remove(0);

            int row = arr[0];
            int col = arr[1];
            int currentLength = arr[2];
            //System.out.println("r: "+row+" c: "+col+" depth: "+currentLength+" "+mat[row][col]);


            boolean check1 = checkNeighbor(mat, row, col, row-1, col);
            boolean check2 = checkNeighbor(mat, row, col,row+1,col);
            boolean check3 = checkNeighbor(mat, row, col,row,col-1);
            boolean check4 = checkNeighbor(mat, row, col,row,col+1);
            mat[row][col]='.';
            if(check1)
            {
                int[] temp = {row-1,col,currentLength+1};
                path.add(temp);
            }
            if(check2)
            {
                int[] temp = {row+1,col,currentLength+1};
                path.add(temp);    
            }
            if(check3)
            {
                int[] temp = {row,col-1,currentLength+1};
                path.add(temp);  
            }
            if(check4)
            {
                int[] temp = {row,col+1,currentLength+1};
                path.add(temp);  
            }

            if(path.isEmpty())
            {
                totalLength = currentLength;
            }
        }
        
        System.out.println(totalLength);
    }

    public static boolean checkNeighbor(char[][] mat, int row, int col, int checkRow, int checkCol)
    {

        if(checkRow<0||checkRow>=mat.length||checkCol<0||checkCol>=mat[checkRow].length||mat[checkRow][checkCol]=='.')
        {
            return false;
        }            
        char c1 = mat[row][col];
        char c2 = mat[checkRow][checkCol];

        boolean c1North = (c1=='|'||c1=='L'||c1=='J')||c1=='S';
        boolean c1South = (c1=='|'||c1=='7'||c1=='F')||c1=='S';
        boolean c1East = (c1=='-'||c1=='L'||c1=='F')||c1=='S';
        boolean c1West = (c1=='-'||c1=='J'||c1=='7')||c1=='S';

        boolean c2North = (c2=='|'||c2=='L'||c2=='J')||c2=='S';
        boolean c2South = (c2=='|'||c2=='7'||c2=='F')||c2=='S';
        boolean c2East = (c2=='-'||c2=='L'||c2=='F')||c2=='S';
        boolean c2West = (c2=='-'||c2=='J'||c2=='7')||c2=='S';

        //System.out.println(row-1+" "+checkRow);
        return (c1North&&c2South&&row-1==checkRow)||(c1South&&c2North&&row+1==checkRow)||(c1East&&c2West&&col+1==checkCol)||(c1West&&c2East&&col-1==checkCol);
    }
}