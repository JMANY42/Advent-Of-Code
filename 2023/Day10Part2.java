import java.util.*;
import java.io.*;

public class Day10Part2
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

        HashSet<Point> emptyPoints = new HashSet<Point>();
        HashSet<Point> pipePoints = new HashSet<Point>();

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
                else if(mat[r][c]=='.')
                {
                    emptyPoints.add(new Point(r,c));
                }
                else
                {
                    pipePoints.add(new Point(r,c));
                }
            }
        }

        HashSet<Point> openPoints = new HashSet<Point>();
        HashSet<Point> enclosedPoints = new HashSet<Point>();



        for(Point p:emptyPoints)
        {
            if(openPoints.contains(p)||enclosedPoints.contains(p))
            {
                continue;
            }
            ArrayList<Point> connectedPoints = new ArrayList<Point>();
            HashSet<Point> usedPoints = new HashSet<Point>();
            connectedPoints.add(p);

            while(!connectedPoints.isEmpty())
            {
                Point point = connectedPoints.remove(0);
                
            }
        }

        /*while(!path.isEmpty())
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
        */
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

class Point
{
    private int row, col, hashcode;

    public Point(int r, int c)
    {
        row = r;
        col = c;
        hashcode = Objects.hash(row, col);
    }

    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point that = (Point) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return hashcode;
    }
}