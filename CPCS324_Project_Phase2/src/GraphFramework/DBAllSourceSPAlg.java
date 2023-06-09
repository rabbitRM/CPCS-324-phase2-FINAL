package GraphFramework;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {

    // Defining the data feilds
    private Graph graph;

    
    //-----------------------------------------------------------------------------
    // Defining the constructor
    public DBAllSourceSPAlg(Graph g) {
        graph = g;
    }

    //-----------------------------------------------------------------------------
    // Method to implement  the Dijkstra-based shortest path algorithm for 
    // computing the shortest path from each vertex to the rest of the vertices
    public void computeDijkstraBasedSPAlg() {
        
        // creating a SingleSourceSPAlg object
        SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(graph);
        
        // loop to go through each vertex 
        for (Vertex source : graph.getVertices()) {
            
            
            System.out.println("The starting point location is " + (char)(source.getLabel()+'A')
                    + "\n\nThe routes from location " + (char)(source.getLabel()+'A') + " to the rest of the locations are:");

            // invoking the dijkstra algorithm while sending the source  
            dijkstra.computeDijkstraAlg(source);
            
            for (int i = 0; i < graph.getVertices().size(); i++) {
                graph.getVertices().get(i).setIsVisited(false);
            }
            System.out.println("");

        }

    }
    
}
