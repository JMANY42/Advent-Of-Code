import java.util.*;
import java.io.*;

public class Day7Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner getLines = new Scanner(new File("Day7.txt"));
        int lines = 0;
        while(getLines.hasNextLine())
        {
            getLines.nextLine();
            lines++;
        }
        System.out.println(lines);
        Scanner in = new Scanner(new File("Day7.txt"));
        String[] hands = new String[lines];
        int[] bids = new int[lines];
        int[] relativeValue = new int[lines];

        hands[0] = in.next();
        bids[0] = in.nextInt();
        for(int i=1;i<lines;i++)
        {
            in.nextLine();
            hands[i] = in.next();
            bids[i] = in.nextInt();
            System.out.println(hands[i]);
        }

        for(int i=0;i<hands.length;i++)
        {
            relativeValue[i] = analyzeHand(hands[i]);
        }

        bubbleSort(relativeValue, lines, hands, bids);
        modifiedBubbleSort(relativeValue, lines, hands, bids);
        int sum = 0;
        System.out.println(Arrays.toString(relativeValue));
        System.out.println(Arrays.toString(hands));
        for(int i=0;i<lines;i++)
        {
            sum+= (lines-i)*bids[i];
        }
        System.out.println(sum);
    }

    static void modifiedBubbleSort(int relativeValue[], int n, String[] hands, int[] bids)
    {
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                //swap if adjacent relative values are the same and card index of j is less than j+1
                if (relativeValue[j]==relativeValue[j+1]&&relativeHandStrength(hands[j],hands[j+1],0)){
                    // Swap arr[j] and arr[j+1]
                    swap(relativeValue,j,j+1);
                    swap(hands,j,j+1);
                    swap(bids,j,j+1);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    public static boolean relativeHandStrength(String str1, String str2, int depth)
    {
        
        if(handStrength(str1.charAt(depth))-handStrength(str2.charAt(depth))>0)
        {
            return false;
        }
        if(handStrength(str1.charAt(depth))-handStrength(str2.charAt(depth))<0)
        {
            return true;
        }
        if(handStrength(str1.charAt(depth))-handStrength(str2.charAt(depth))==0)
        {
            return relativeHandStrength(str1,str2,depth+1);
        }
    
        return false;
    }

    public static int handStrength(char c)
    {
        switch(c)
        {
            case 'A': return 15;
            case 'K': return 14;
            case 'Q': return 13;
            case 'J': return 12;
            case 'T': return 11;
            case '9': return 10;
            case '8': return 9;
            case '7': return 8;
            case '6': return 7;
            case '5': return 6;
            case '4': return 5;
            case '3': return 4;
        }
        return 3;

    }

    static void bubbleSort(int arr[], int n, String[] hands, int[] bids)
    {
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                
                    // Swap arr[j] and arr[j+1]
                    swap(arr,j,j+1);
                    swap(hands,j,j+1);
                    swap(bids,j,j+1);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    public static void swap(int[] arr, int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void swap(String[] arr, int a, int b)
    {
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static int analyzeHand(String hand)
    {
        HashSet<Character> handSet = new HashSet<Character>();
        for(int i=0;i<hand.length();i++)
        {
            handSet.add(hand.charAt(i));
        }

        //high card 0
        if(handSet.size()==5)
        {
            return 0;
        }

        //one pair 1
        if(handSet.size()==4)
        {
            return 1;
        }

        //five of a kind 6
        if(handSet.size()==1)
        {
            return 6;
        }

        //two pair 2
        int numPairs = 0;
        HashSet<Character> used = new HashSet<Character>();
        for(int i=0;i<hand.length();i++)
        {
            for(int j=i+1;j<hand.length();j++)
            {
                if(hand.charAt(j)==hand.charAt(i)&&!used.contains(hand.charAt(j)))
                {
                    numPairs++;
                    used.add(hand.charAt(j));
                }
            }
        }

        if(numPairs==2&&handSet.size()==3)
        {
            return 2;
        }

        //three of a kind 3 + full house 4
        for(int i=0;i<hand.length();i++)
        {
            for(int j=i+1;j<hand.length();j++)
            {
                for(int k=j+1;k<hand.length();k++)
                {
                    if(hand.charAt(j)==hand.charAt(i)&&hand.charAt(i)==hand.charAt(k))
                    {
                        if(handSet.size()==2&&numPairs==2)
                        {
                            return 4;
                        }
                        if(handSet.size()==3&&numPairs==1)
                        {
                            return 3;
                        }
                    }
                }
            }
        }


        //four of a kind 5
        for(int i=0;i<hand.length();i++)
        {
            for(int j=i+1;j<hand.length();j++)
            {
                for(int k=j+1;k<hand.length();k++)
                {
                    for(int m=k+1;m<hand.length();m++)
                    {
                        if(hand.charAt(j)==hand.charAt(i)&&hand.charAt(i)==hand.charAt(k)&&hand.charAt(k)==hand.charAt(m))
                        {
                            return 5;
                        }
                    }
                    
                }
            }
        }
        System.out.println("Something is wrong");
        return -1;
    }
}