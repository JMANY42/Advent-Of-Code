public class Day3
{
    public static void main(String[]args)
    {

        partOne();
        partTwo();
    }    

    public static void partOne()
    {
        int n=347991;
        int r = 0;
        int c = 0;
        int length = 0;
        int max_length = 1;
        int dir = 0;
        for(int i=0;i<n;i++)
        {
            switch(dir)
            {
                case 0:
                    c++;
                    break;
                case 1:
                    r--;
                    break;
                case 2:
                    c--;
                    break;
                case 3:
                    r++;
                    break;
            }
            length++;
            if(length==max_length&&dir%2==1)
            {
                max_length++;
                length = 0;
                dir = (dir+1)%4;
            }
            if(length==max_length)
            {
                dir = (dir+1)%4;
                length=0;
            }
        }

        System.out.println(Math.abs(r)+Math.abs(c)-1);
    }

    public static void partTwo()
    {
        int[][] mat = new int[25][25];

        mat[12][12] = 1;

        int n=347991;
        int r = 0;
        int c = 1;
        int length = 0;
        int max_length = 1;
        int dir = 0;
        for(int i=0;i<25*25-1;i++)
        {
            int num = getSumNeighbors(12+r,12+c,mat);
            mat[12+r][12+c] = num;
            if(num>n)
            {
                System.out.println(num);
                break;
            }
            length++;
            if(length==max_length&&dir%2==1)
            {
                max_length++;
                length = 0;
                dir = (dir+1)%4;
            }
            if(length==max_length)
            {
                dir = (dir+1)%4;
                length=0;
            }
            switch(dir)
            {
                case 0:
                    c++;
                    break;
                case 1:
                    r--;
                    break;
                case 2:
                    c--;
                    break;
                case 3:
                    r++;
                    break;
            }
            
        }



    }

    public static int getSumNeighbors(int r, int c, int[][] mat)
    {
        int sum = 0;
        if(r>0)
        {
            sum+=mat[r-1][c];
        }
        if(c>0)
        {
            sum+=mat[r][c-1];
        }
        if(r>0&&c>0)
        {
            sum+=mat[r-1][c-1];
        }
        if(r<mat.length-1)
        {
            sum+=mat[r+1][c];
        }
        if(c<mat.length-1)
        {
            sum+=mat[r][c+1];
        }
        if(c<mat.length-1&&r<mat.length-1)
        {
            sum+=mat[r+1][c+1];
        }
        if(r>0&&c<mat.length-1)
        {
            sum+=mat[r-1][c+1];
        }
        if(c>0&&r<mat.length-1)
        {
            sum+=mat[r+1][c-1];
        }
        return sum;
    }
}
