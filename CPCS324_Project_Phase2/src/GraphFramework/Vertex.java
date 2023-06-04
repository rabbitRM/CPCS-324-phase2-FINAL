package GraphFramework;

import java.util.LinkedList;

public abstract class Vertex {

    
    // Defining the data feilds
    private int label;
    private boolean isVisited;
    private LinkedList<Edge> adjLists = new LinkedList<>();

    
    //-----------------------------------------------------------------------------
    // Defining the constructor
    public Vertex() {

    }
 
    public Vertex(int label) {

        this.label = label;
        isVisited = false;

    }

    
    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public void setLabel(int label) {
        this.label = label;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setAdjLists(LinkedList<Edge> adjLists) {
        this.adjLists = adjLists;
    }

    public int getLabel() {
        return label;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public LinkedList<Edge> getAdjLists() {
        return adjLists;
    }

    
    //-----------------------------------------------------------------------------
    // Defining the method that should be overriden in the application framework
    public abstract String displayInfo();

}
