package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

public class Route extends Edge {

    
    // Defining the constructor and calling the parent class
    public Route(Vertex source, Vertex target, int weight) {
        
        super(source, target, weight);

    }

    
    //-----------------------------------------------------------------------------
    @Override
    // Method to display the information of the length
    public String displayInfo() {
      
        return "route length: " + super.getWeight();
   
    }

}
