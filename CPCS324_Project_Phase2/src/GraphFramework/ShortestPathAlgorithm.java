package GraphFramework;


public class ShortestPathAlgorithm {
    
   
    // Defining the data feilds
    protected static Graph graph  ;

    
    //-----------------------------------------------------------------------------
    // Defining the constructor
    public ShortestPathAlgorithm() {
    }
   
    
    //-----------------------------------------------------------------------------
    // Defining the setters and getterss
    public static Graph getGraph() {
        return graph;
    }

    public static void setGraph(Graph graph) {
        ShortestPathAlgorithm.graph = graph;
    }

}
