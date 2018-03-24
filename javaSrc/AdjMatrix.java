import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	private ArrayList<ArrayList<T>> adjMatrix;
	
	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
    	adjMatrix = new ArrayList<ArrayList<T>>();
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
        ArrayList<T> vertex = new ArrayList<T>();
        vertex.add(vertLabel);
        adjMatrix.add(vertex);
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        ArrayList<T> edgeList = new ArrayList<T>();
        // T edge = 1; // 1 to show there is an edge between srcLabel and tarLabel
        
        edgeList.add(srcLabel);
        edgeList.add(tarLabel);
        //   edgeList.add(edge);
        adjMatrix.add(edgeList);
        
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {  	
        for (int i = 0; i < adjMatrix.size(); i++) {
        	os.print(adjMatrix.get(i) + " ");
        }
        
        os.flush();
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjMatrix