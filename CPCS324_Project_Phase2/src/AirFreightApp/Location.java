package AirFreightApp;

import GraphFramework.Vertex;

public class Location extends Vertex {

    
    // Defining the data feilds
    private String city;

    
    //-----------------------------------------------------------------------------
    // Defining the constructor 
    public Location(int lable) {
        
        // Calling the parent class
        super(lable);
        
        // Storing a unique number in city
        city = 1+lable+"";
    }

   
    //-----------------------------------------------------------------------------
    // Method to display the information of the class attributes
    @Override
    public String displayInfo() {
      
        return "Loc " + (char) (super.getLabel() + 'A') + " :city " + city;
    
    }

}
