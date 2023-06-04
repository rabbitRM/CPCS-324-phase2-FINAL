package GraphFramework;

public abstract class Edge {

    
    // Defining the data feilds
    private Vertex source;
    private Vertex target;
    private Vertex parent;
    private int weight;

    
    //-----------------------------------------------------------------------------
    // Defining the constructor 
    public Edge() {

    }
    
    public Edge(Vertex source, Vertex target, int weight) {
       
        this.weight = weight;
        this.source = source;
        this.target = target;
        parent = source;

    }

    public Edge(Vertex source, int weight) {
       
        this.weight = weight;
        this.source = source;

    }


    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public void setSource(Vertex source) {
        this.source = source;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public Vertex getParent() {
        return parent;
    }
    
    public int getWeight() {
        return weight;
    }

    
    //-----------------------------------------------------------------------------
    // Defining the method that should be overriden in the application framework
    public abstract String displayInfo();

}
