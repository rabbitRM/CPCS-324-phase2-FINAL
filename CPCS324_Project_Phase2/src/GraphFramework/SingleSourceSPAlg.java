package GraphFramework;

import java.io.FileNotFoundException;
import java.util.*;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    //-----------------------------------------------------------------------------
    // Defining the constructor    
    public SingleSourceSPAlg(Graph graph) {

        this.graph = graph;

    }
    

//-----------------------------------------------------------------------------
// Method to perform the DIJKSTRA Algorithem ( computes the shortest path ).
// It takes a source of `Vertex` objects as its input parameter.
// The method doesn't return anything.
public static void computeDijkstraAlg(Vertex source) {

    // creating a map object to know the distance from the source vertex to all other vertices
    Map<Integer, Double> distances = new HashMap<>();
    
    // initializing the distances for all the vertices to infinity
    for (Vertex vertex : graph.getVertices()) {
        
        distances.put(vertex.getLabel(), Double.POSITIVE_INFINITY);
        
    }
    
    // setting the distance of the source to 0 
    distances.put(source.getLabel(), 0.0);

    // creating a map to store the paths from the source vertex to other vertices
    Map<Vertex, ArrayList<Vertex>> paths = new HashMap<>();
    
    // initializing the the map with the keys and their paths
    for (Vertex vertex : graph.getVertices()) {
        
        paths.put(vertex, new ArrayList<>());
        
    }
    
    // loop until there is still vertices need to be visited
    while (!graph.allVerticesVisited()) {
        
        // initialize the distance of the short path with infinity
        double shortestDistance = Double.POSITIVE_INFINITY;
        
        // loop to find the first unvisited vertex
        Vertex currentVertex = null;
        for (Vertex vertex : graph.getVertices()) {
            
            // checking if the vertex is unvisited
            if (!vertex.isIsVisited()) {
                
                //initializing the currentVertex with the current vertex
                currentVertex = vertex;

                break;
            }
        }
        
        // loop to find the first unvisited vertex with the shortest distance
        for (Vertex vertex : graph.getVertices()) {
            
            if (!vertex.isIsVisited() && distances.get(vertex.getLabel()) < shortestDistance) {
                
                currentVertex = vertex;
                
                shortestDistance = distances.get(vertex.getLabel());
            }
            
        }

        // creating a list of adjacent vertices of the currentVertex
        ArrayList<Vertex> adjacentVertices = new ArrayList<>();
        
        // loop to go through the edges of the currentVertex
        for (int i = 0; i < currentVertex.getAdjLists().size(); i++) {
            
            
            // checking if the adjacent vertex = currentVertex
            if(currentVertex.getAdjLists().get(i).getParent().equals(currentVertex))
                
                adjacentVertices.add(currentVertex.getAdjLists().get(i).getTarget());
          
            else 
                adjacentVertices.add(currentVertex.getAdjLists().get(i).getParent());
           
        }

        // loop to go through the adjacent vertices
        for (Vertex adjacent : adjacentVertices) {
            
            // saving the distance to the adjacent from the currentVertex
            double currentToAdjacent = distances.get(currentVertex.getLabel()) + graph.getEdgeWeight(currentVertex, adjacent);
            
            // checking if the distance to the adjacent from the currentVertex is less than the distance stored in the adjacent
            if (currentToAdjacent < distances.get(adjacent.getLabel())) {
               
                // updating the distance of the adjacent vertex
                distances.put(adjacent.getLabel(), currentToAdjacent);
                
                // updating the path to the adjacent vertex
                ArrayList<Vertex> path = new ArrayList<>(paths.get(currentVertex));
                path.add(currentVertex);
                paths.put(adjacent, path);
            }
        }

        // marking the current vertex as visited 
        currentVertex.setIsVisited(true);
        
    }

        // loop to go through the other vertices to print its shortest paths
        for (Vertex vertex : graph.getVertices()) {

            // getting the list of vertices needed in the shortest path in a specifice Destination
            ArrayList<Vertex> shortestPath = paths.get(vertex);

            // checking if the shortest path is not empty , in case there is no possible path or a path to the same vertex
            if (!shortestPath.isEmpty()) {

                shortestPath.add(vertex);

                // loop to go through the shortest path
                for (int i = 0; i < shortestPath.size() - 1; i++) {

                    System.out.print(shortestPath.get(i).displayInfo() + " -> ");
                }

                // displaying the info of the last vertex and the needed distance
                System.out.println(vertex.displayInfo() + " --- route length: " + distances.get(vertex.getLabel()).intValue());

            // if there is no path 
            }else {
                
            // print a message    
            System.out.print( graph.getVertices().get(source.getLabel()).displayInfo()+ " to " + vertex.displayInfo() + ": ");
            System.out.println("No path exists");
            
            }
        }

    }
}
