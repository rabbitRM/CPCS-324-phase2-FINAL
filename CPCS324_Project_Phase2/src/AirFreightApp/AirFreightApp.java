/* Title : Implementing the Dijkstra algorithm to compute the length of the shortest paths 
           from all locations to the rest of the locations
  
   Name                     ID
   Nujud Abdullah Almaleki  2105148 
   Rama Ahmad Alsefri  	    2105895
   Areej Omar Baeshen  	    2105759
   Furat Jamel Alfarsi 	    2009624

  input requirements : The user will decide to get shortest paths from a graph its information is written in a file 
                       OR enter the number of locations , routes , if the graph is directed or not 
                       and let the program randomly generate a graph 
  output results : printing all the shortest paths awith their cost
  Cites :https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/ 

 */

package AirFreightApp;

import GraphFramework.DBAllSourceSPAlg;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AirFreightApp {

    // Defining the data feilds
    static AFRouteMap map;

    public static void main(String[] args) throws FileNotFoundException {

        // scanner opject to read from the user
        Scanner input = new Scanner(System.in);

        // creating the object that will contain the graph
        map = new AFRouteMap();

        // long variables to store the strating time and ending time 
        //of processing all-pair dijkstra algorithm
        long startTime, endTime;

        // double variable to store differenece of time 
        //in millisecond between startTime and endTime variables
        double result;

        // letting the user pick a choice
        System.out.print(">> Enter your choice (1 or 2): ");

        int choice = input.nextInt();

        // if he pick 1 --> the graph will be read from a file 
        if (choice == 1) {
        
            // invoking the method that will read a graph from a specified file 
            map.readGraphFromFile("input.txt");
       
        // if he pick 2 --> the graph will be random
        }else if (choice == 2) {

            // getting the number of locations 
            System.out.print("Enter the number of Locations : ");
            int locationNO = input.nextInt();

            // getting the number of Routes 
            System.out.print("Enter the number of Routes : ");
            int routesNO = input.nextInt();

            // getting to know if if directed or not 
            System.out.print("Enter 0 if the graph is undirected or 1 if directed : ");
            int isdiagraph = input.nextInt();

            System.out.println("");

            // invoking the method that will create a random graph 
            map.makeGraph(locationNO, routesNO, isdiagraph);

        }

        // creating a DBAllSourceSPAlg object , and send the graph to it 
        DBAllSourceSPAlg multiDijkstra = new DBAllSourceSPAlg(map);
        
        // computing the starting time for the algorithm
        startTime = System.currentTimeMillis();
        
        // invoking the method that will get all-pair shortest paths
        multiDijkstra.computeDijkstraBasedSPAlg();
       
        // computing the ending time for the algorithm  
        endTime = System.currentTimeMillis();
       
        // computing the time it took the algorithm to process
        result = (double) (endTime - startTime)/ 1000;
        
        //printing a message
        System.out.println("It took me " + result + " ms\n");
    
    }

}
