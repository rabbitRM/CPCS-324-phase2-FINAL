package AirFreightApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;

public class AFRouteMap extends Graph {

    // Defining the overriden method to create Route in the application framework instead of creating an edge
    @Override
    public Route createEdge(Vertex source, Vertex target, int weight) {

        return new Route(source, target, weight);

    }

    //-----------------------------------------------------------------------------
    // Defining the overriden method to create location in the application framework instead of creating a vertex 
    @Override
    public Location createVertex(int lable) {

        return new Location(lable);

    }
    
}
