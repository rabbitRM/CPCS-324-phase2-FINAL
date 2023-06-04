package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import AirFreightApp.AFRouteMap;
import java.util.Collections;

public abstract class Graph {

    // Defining the data feilds
    private int vertexNo;
    private int edgeNo;
    private boolean isDiagraph;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private LinkedList<Edge>[] adjacencylist;

    //-----------------------------------------------------------------------------
    // Defining the constructor
    public Graph() {

    }

    public Graph(int edgeNo, int vertexNo, boolean isDiagraph) {

        this.vertexNo = vertexNo;
        this.edgeNo = edgeNo;
        this.isDiagraph = isDiagraph;

        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertexNo; i++) {
            vertices.add(createVertex(i));
        }

        // creating the adjacency list for each
        adjacencylist = new LinkedList[vertexNo];

        for (int i = 0; i < vertexNo; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

    }

    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public int getVertexNo() {
        return vertexNo;
    }

    public void setVertexNo(int vertexNo) {
        this.vertexNo = vertexNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public boolean isIsDiagraph() {
        return isDiagraph;
    }

    public void setIsDiagraph(boolean isDiagraph) {
        this.isDiagraph = isDiagraph;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }

    public void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        this.adjacencylist = adjacencylist;
    }

    //-----------------------------------------------------------------------------
    // methods need to be overriden 
    public abstract Vertex createVertex(int label);

    public abstract Edge createEdge(Vertex source, Vertex target, int weight);

    //-----------------------------------------------------------------------------
    // method n takes as parameters the number of vertices and the number of edges
    // It is responsible for creating a graph object with the specified parameters 
    // and randomly initializes the vertices’ labels, creating edges that connects the created vertices randomly
    // and assigning them random weights alson Makeing sure that the resulting graph is connected
    public void makeGraph(int vertexNum, int edgeNum, int isdirected) {

        // checking if the graph is directed or not 
        this.isDiagraph = isdirected == 0 ? false : true;

        // creating the adjacency list
        adjacencylist = new LinkedList[vertexNum];

        for (int i = 0; i < vertexNum; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

        // Adding the vertices to its list in the graph
        for (int i = 0; i < vertexNum; i++) {

            vertices.add(createVertex(i));

            // incrementing the vertices number in the graph
            vertexNo++;
        }

        // creating random object to get random weights
        Random random = new Random();

        // loop to connect vertices together
        for (int i = 0; i < vertexNum - 1; i++) {

            // getting random weights between 1-40
            int weight = random.nextInt(40) + 1;

            // adding the new edge 
            addEdge(vertices.get(i), vertices.get(i + 1), weight);

        }

        // getting the number of remining edges we still have to create and connect
        int reminingEdges = edgeNum - (vertexNum - 1);

        // loop to create the remining edges
        for (int i = 0; i < reminingEdges; i++) {

            // using the random object to get random source and target to connects
            // the random numbers will be between 0 - vertexNo
            int source = random.nextInt(vertexNo);
            int target = random.nextInt(vertexNo);

            // checking if the random target and source are the same or the these two vertices are already connected
            if (target == source || areConnected(vertices.get(source), vertices.get(target))) {

                // because we dont want this iteration to go to waste , we decrement as if nothing happened
                i--;

                // go back to the begining of the loop 
                continue;
            }

            // getting random weights between 1-40
            int weight = random.nextInt(40) + 1;

            // add edge to the graph
            addEdge(vertices.get(source), vertices.get(target), weight);

        }

    }

    //-----------------------------------------------------------------------------
    // Method to check if two vetices are connected 
    // It will get a sources vertex and a target vertex 
    public static boolean areConnected(Vertex v1, Vertex v2) {

        // getting the size of the adjList that store the edges to ba able to go through it 
        int size = v1.getAdjLists().size();

        // loop to got through the edges of a specefic vertex 
        for (int j = 0; j < size; j++) {

            // if one if the vertices is a source and the other is a target then they are connected 
            if ((v1.getAdjLists().get(j).getSource() == v1 && v1.getAdjLists().get(j).getTarget() == v2)
                    || (v1.getAdjLists().get(j).getSource() == v2 && v1.getAdjLists().get(j).getTarget() == v1)) {

                return true;
            }

        }

        // if there is no connection between them 
        return false;

    }

    //-----------------------------------------------------------------------------
    // Method that reads the edges and vertices from a text file, and place the data in the Graph
    // It will get the text file name 
    public void readGraphFromFile(String fileName) throws FileNotFoundException {

        // initializing the variables with 0 
        int edgeNum = 0, vertexNum = 0;

        // checking if the file exist 
        File f = new File(fileName);

        if (!f.exists()) {

            System.out.println("File Does not exist !");

        }

        // creating 2 scanner object
        // scanner object to read the data of each edge
        Scanner input = new Scanner(f);

        // scanner object to read the labels of each vertex 
        Scanner input2 = new Scanner(f);

        // skipping to the part needed to be read which is( vertices labels )
        input2.nextLine();
        input2.nextLine();

        // skipping the word diagraph in the input file , because there is no meaning in reading it !
        input.next();

        // checking if the graph is directed or not 
        isDiagraph = input.nextInt() == 0 ? false : true;

        // reading the edges number 
        edgeNum = input.nextInt();

        //reading the vertices number 
        vertexNum = input.nextInt();

        // creating the adjacency list 
        adjacencylist = new LinkedList[vertexNum];

        for (int i = 0; i < vertexNum; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

        // creating a list to store the vertices labels 
        int[] listLabels = new int[vertexNum];

        // creating a counter to be the index for the labels list
        int counter = 0;

        // saving label 0 in list of labels because we can not save in the loop  
        listLabels[0] = 0;

        // loop to store the distinct labels of each edge 
        for (int i = 0; i < edgeNum; i++) {

            // reading the character labels fron the file 
            char ch1 = input2.next().charAt(0);
            char ch2 = input2.next().charAt(0);

            // a flag to know wether to add the labels to the list or not 
            boolean canAdd1 = true;
            boolean canAdd2 = true;

            // loop to go through the list labels
            for (int j = 0; j < listLabels.length; j++) {

                // checking if the first label is already stored 
                // because we store the labels as integers , when comparing we need to convert the char nito integer
                // by substracting 'A' from it we will get a number, hence valid comparison
                if (listLabels[j] == (ch1 - 'A')) {

                    // if the first label is already stored , then make the flag1 = false 
                    canAdd1 = false;

                }

                // checking if the second label is already stored 
                // because we store the labels as integers , when comparing we need to convert the char nito integer
                // by substracting 'A' from it we will get a number, hence valid comparison
                if (listLabels[j] == (ch2 - 'A')) {

                    // if the second label is already stored , then make the flag2 = false 
                    canAdd2 = false;
                }
            }

            // moving the pointer to the next line , 
            // because we dont need to read the weights of the edges with this pointer 
            input2.nextLine();

            // if the flag1 = true , then we can add the first label 
            // and increment the index to the next place for another label 
            if (canAdd1) {

                listLabels[counter] = ch1 - 'A';

                counter++;
            }

            // if the flag2 = true , then we can add the first label 
            // and increment the index to the next place for another label 
            if (canAdd2) {

                listLabels[counter] = ch2 - 'A';

                counter++;
            }

        }

        // After getting the labels , now we create the vertices with these labels 
        // Adding the vertices to its list in the graph
        for (int i = 0; i < vertexNum; i++) {

            vertices.add(createVertex(listLabels[i]));

            // incrementing the vertices number in the graph 
            vertexNo++;
        }

        // connecting the vertices together ( creating edges ) 
        // creating and initializing the needed variables 
        char label1, label2;

        Vertex vertex1 = null, vertex2 = null;

        // loop to go through each edge in the file 
        for (int i = 0; i < edgeNum; i++) {

            // reading the labels for the source and target vetices 
            label1 = input.next().charAt(0);
            label2 = input.next().charAt(0);

            // loop to go through the vertices list 
            // to get the vetices objects with same readed labels
            for (int j = 0; j < vertices.size(); j++) {

                // if the current vertex label is the same as the first readed labels 
                if (vertices.get(j).getLabel() == label1 - 'A') {

                    // save in first vertex
                    vertex1 = vertices.get(j);

                    // if the current vertex label is the same as the second readed labels 
                } else if (vertices.get(j).getLabel() == label2 - 'A') {

                    // save in second vertex
                    vertex2 = vertices.get(j);
                }

            }

            // checking if both vertices are not null , to not cause null pointer exeption !
            if (vertex1 != null && vertex2 != null) {

                // adding the new edge   
                addEdge(vertex1, vertex2, input.nextInt());

            }

        }

        // sorting the vertices in their list in the graph
        Collections.sort(vertices, Comparator.comparingInt(vertex -> vertex.getLabel()));

    }

    //-----------------------------------------------------------------------------
    // Method to add an edge to the graph 
    // It will get the source vertex , target vertex and the weight
    public void addEdge(Vertex source, Vertex target, int weight) {

        // creating the edge needed using the overriden method 
        Edge edge = createEdge(source, target, weight);

        // checking if the graph is directed
        if (isDiagraph) {

            // adding the edge to the list of edges in the source vertex  S --> T
            source.getAdjLists().add(edge);

            // adding the edge to the list of edges of the graph 
            adjacencylist[source.getLabel()].addFirst(edge);

            // incrementing the number of edges in the graph by 1 
            edgeNo++;

            // if the graph is not dirceted 
            // and from taget to source then both assign from source to taget
        } else {

            // creating another edge , so the edge will b from both sides S --> <-- T
            Edge edge2 = createEdge(target, source, weight);

            // adding the edge to the list of edges in the source vertex  S --> T
            source.getAdjLists().add(edge);

            // adding the edge to the list of edges in the source vertex  S <-- T
            target.getAdjLists().add(edge2);

            // adding the edge to the list of edges of the graph S --> T 
            adjacencylist[source.getLabel()].addFirst(edge);

            // adding the edge to the list of edges of the graph S <-- T 
            adjacencylist[target.getLabel()].addFirst(edge2);

            // incrementing the number of edges in the graph by 2
            edgeNo += 2;

        }
    }

    //-----------------------------------------------------------------------------
    // Method to get the weight of a specific edge connecting specific source and specific target 
    public int getEdgeWeight(Vertex source, Vertex target) {

        // loop to go through the edges of the source vertex
        for (int i = 0; i < source.getAdjLists().size(); i++) {

            // if the target of the current edge is the same as the specified target 
            // that means we got the needed edge 
            if (source.getAdjLists().get(i).getTarget() == target) {

                return source.getAdjLists().get(i).getWeight();

            }

        }

        return 0;
    }

    //-----------------------------------------------------------------------------
    // Method to get the weight of a specific edge connecting specific source and specific target 
    public boolean allVerticesVisited() {
        int counter = 0;
        for (int i = 0; i < vertexNo; i++) {
            if (vertices.get(i).isIsVisited()) {
                counter++;
            }
        }

        return counter == vertexNo;
    }

}
