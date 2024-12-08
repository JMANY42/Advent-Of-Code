import java.util.*;
import java.io.*;
public class Day7
{
    public static void main(String[]args) throws IOException
    {
        Scanner in = new Scanner(new File("Day7.txt"));
        Dir outside = new Dir("/");
        Dir currentDir = outside;
        Stack<Dir> prevDir = new Stack<Dir>();
        prevDir.push(currentDir);
        ArrayList<Dir> allDir = new ArrayList<Dir>();

        while(in.hasNextLine())
        {
            String line = in.nextLine();

            if(line.charAt(0)=='$')
            {
                if(line.charAt(2)=='c')
                {
                    if(line.charAt(5)=='.')
                    {
                        prevDir.pop();
                        currentDir = prevDir.peek();
                    }
                    else
                    {
                        String dirName = line.substring(5);
                        ArrayList<Dir> dirList= currentDir.getDirList();

                        for(int i=0;i<dirList.size();i++)
                        {
                            if(dirList.get(i).getName().equals(dirName))
                            {
                                currentDir = dirList.get(i);
                                break;
                            }
                        }
                        prevDir.push(currentDir);
                        allDir.add(currentDir);
                    }
                }
                else if(line.charAt(2)=='l')//L not one
                {
                    //nothing here?
                }
            }
            else if(line.substring(0,3).equals("dir"))
            {
                currentDir.addDir(new Dir(line.substring(4)));
            }
            else
            {
                String[] sArr = line.split(" ");
                currentDir.addFile(new Filex(Integer.valueOf(sArr[0]),sArr[1]));
            }
        }
        int totalSpace = 70000000;
        int freeSpace = totalSpace-getSizeOfDir(allDir.get(0));
        int spaceNeeded = 30000000-freeSpace;

        int answer = Integer.MAX_VALUE;
        for(int i=0;i<allDir.size();i++)
        {
            int tempSize = getSizeOfDir(allDir.get(i));
            if(tempSize>=spaceNeeded&&tempSize<answer)
            {
                answer = tempSize;
            }
        }
        System.out.println(answer);
    }

    public static int getSizeOfDir(Dir x)
    {
        if(x.getDirList().size()==0)
        {
            return x.getSize();
        }
        else
        {
            int temp = 0;
            for(int i=0;i<x.getDirList().size();i++)
            {
                temp+=getSizeOfDir(x.getDirList().get(i));
            }
            return x.getSize()+temp;
        }
    }
}

class Dir
{
    private ArrayList<Dir> dirList;
    private ArrayList<Filex> fileList;
    private String name;
    private int size;
    public Dir(String n)
    {
        dirList = new ArrayList<Dir>();
        fileList = new ArrayList<Filex>();
        name = n;
    }

    public void addFile(Filex x)
    {
        fileList.add(x);
        updateSize();
    }
    public void addDir(Dir x)
    {
        dirList.add(x);
    }
    public void updateSize()
    {
        int s = 0;
        for(int i=0;i<fileList.size();i++)
        {
            s+=fileList.get(i).getSize();
        }
        size = s;
    }
    public ArrayList<Dir> getDirList()
    {
        return dirList;
    }
    public ArrayList<Filex> getFileList()
    {
        return fileList;
    }
    public String getName()
    {
        return name;
    }
    public int getSize()
    {
        return size;
    }
    public String toString()
    {
        return name;
    }
}

class Filex
{
    private int size;
    private String name;
    public Filex(int x, String n)
    {
        size = x;
        name = n;
    }

    public int getSize()
    {
        return size;
    }
    public String getName()
    {
        return name;
    }
    public String toString()
    {
        return name;
    }
}