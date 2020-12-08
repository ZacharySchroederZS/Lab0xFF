package com.company;
import java.util.*;
import java.util.Arrays;
import java.lang.management.*;
public class Main {


    static void Greedy(int[][] tsp){
        int sum = 0, counter = 0, j=0, i=0, min=Integer.MAX_VALUE;List<Integer> visitedRouteList
                = new ArrayList<>();
        visitedRouteList.add(0);
        int[] route = new int[tsp.length];

        while(i < tsp.length && j < tsp[i].length){
            if(counter >= tsp[i].length-1){
                break;
            }
            if(j != i && !(visitedRouteList.contains(j))){
                if(tsp[i][j] < min){
                    min = tsp[i][j];
                    route[counter] = j + 1;
                }
            }
            j++;
            if(j==tsp[i].length){
                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter] - 1);
                j = 0;
                i = route[counter] - 1;
                counter++;
            }
        }
        i = route[counter - 1] -1;
        for(j=0; j<tsp.length; j++){
            if((i != j) && tsp[i][j]<min){
                min = tsp[i][j];
                route[counter] = j + 1;
            }
        }
        sum += min;
        //System.out.print("The shortest path is: ");
        //System.out.println(sum);
        System.out.print("");
        System.out.print(sum);
    }







    static int V = 4;
    static int DynamicProgramming(int graph[][],int s)
    {

        ArrayList<Integer> vertex = new ArrayList<Integer>();

        for (int i = 0; i < V; i++)
            if (i != s) vertex.add(i);

        int min_path = Integer.MAX_VALUE;
        do
        {

            int current_pathweight = 0;

            int k = s;

            for (int i = 0;
                 i < vertex.size(); i++)
            {
                current_pathweight += graph[k][vertex.get(i)];
                k = vertex.get(i);
            }
            current_pathweight += graph[k][s];


            min_path = Math.min(min_path, current_pathweight);

        } while (findNextPermutation(vertex));

        return min_path;
    }

    public static ArrayList<Integer> swap(ArrayList<Integer> data, int left, int right)
    {

        int temp = data.get(left);
        data.set(left, data.get(right));
        data.set(right, temp);

        return data;
    }

    public static ArrayList<Integer> reverse(ArrayList<Integer> data, int left, int right)
    {
        // Reverse the sub-array
        while (left < right)
        {
            int temp = data.get(left);
            data.set(left++, data.get(right));
            data.set(right--, temp);
        }

        return data;
    }

    public static boolean findNextPermutation(ArrayList<Integer> data)
    {

        if (data.size() <= 1)
            return false;

        int last = data.size() - 2;

        while (last >= 0)
        {
            if (data.get(last) < data.get(last + 1))
            {
                break;
            }
            last--;
        }

        if (last < 0)
            return false;

        int nextGreater = data.size() - 1;

        for (int i = data.size() - 1; i > last; i--) {
            if (data.get(i) > data.get(last))
            {
                nextGreater = i;
                break;
            }
        }

        data = swap(data, nextGreater, last);

        data = reverse(data, last + 1, data.size() - 1);

        return true;
    }









    static int ants(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans) {

        if (count == n && graph[currPos][0] > 0)
        {
            ans = Math.min(ans, cost + graph[currPos][0]);
            return ans;
        }

        for (int i = 0; i < n; i++)
        {
            if (v[i] == false && graph[currPos][i] > 0)
            {

                v[i] = true;
                ans = ants(graph, v, i, n, count + 1,
                        cost + graph[currPos][i], ans);

                v[i] = false;
            }
        }
        return ans;
    }


    public static void main(String[] args) {



        long timeStampBefore = getCpuTime();
        long timeStampAfter = getCpuTime();
        long timeMeasureForNothing = timeStampAfter - timeStampBefore;
        System.out.println(timeMeasureForNothing);

        timeStampBefore = getCpuTime();

        System.out.printf("Shortest Path: \t Matrix Size: \t Time (Milliseconds): \t Doubling Ratio: ");
        int N = 64;
        int sizeOfArray = 100000; // This changes the size of tha array
        for(N = 128; N < sizeOfArray; N*=2){ //Doubles
            //System.out.print(i +"\n");

        final int rowWidth = N;
        final int colHeight = N;

        Random rand = new Random();

        int [][] board = new int [rowWidth][colHeight];

        //fill the grid
        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[row].length; col++) {

                board[row][col] = rand.nextInt(100); // Max size of numbers in the grid matrix
            }
        }

        //display output
        for(int i = 0; i < board.length; i++) {

            for(int j = 0; j < board[i].length; j++) {

                //System.out.print(board[i][j] + " "); //This prints out the numbers used in the array

            }
            //System.out.println(); //This adds a new line for the array


        }
        timeStampAfter = getCpuTime();
        long timeMeasureForPrintln = timeStampAfter - timeStampBefore;
        System.out.println();
            int s = 0;
            //Doubling ratio
            long doubleRatio = (timeMeasureForPrintln * N) / (timeMeasureForPrintln * (N / 2));



            //******************** Greedy ********************
/*
            Greedy(board); //output
            System.out.printf("\t\t\t\t %d \t\t\t %d \t\t\t\t %d",N, timeMeasureForPrintln,doubleRatio); // Matrix size and the time it takes.
            //System.out.println();

*/

            //******************** DynamicProgramming ********************

            System.out.printf("%d\t\t\t\t %d\t\t\t %d \t\t\t\t %d",DynamicProgramming(board, s),N,timeMeasureForPrintln, doubleRatio);



            //******************** Back Tracking Ants ********************
           /*
            boolean[] v = new boolean[N];

            v[0] = true;
            int ans = Integer.MAX_VALUE;

            ans = ants(board, v, 0, N, 1, 0, ans);
            System.out.println(ans);
            //System.out.printf("%d\t\t\t\t %d\t\t\t %d \t\t\t\t %d",board,N,timeMeasureForPrintln, doubleRatio);



            */

        }
/*
        timeStampAfter = getCpuTime();
        long timeMeasureForPrintln = timeStampAfter - timeStampBefore;
        System.out.println("\nmilliseconds: "+timeMeasureForPrintln);
*/
    }
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }
}
