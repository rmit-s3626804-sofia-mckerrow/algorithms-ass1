import java.io.*;
import java.util.*;

/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix<T extends Object> implements FriendshipGraph<T> {

	private int maxVert; // maximum number of vertices
	private int numVert; // number of vertices
	private String[] vertexList;
	private int[][] adjMatrix;

	/**
	 * Contructs empty graph.
	 */
	public AdjMatrix() {
		maxVert = 4000;
		numVert = 0;
		vertexList = new String[maxVert];
		adjMatrix = new int[maxVert][maxVert];

		// Initialise all entries in adjacency matrix to zero
		for (int i = 0; i < maxVert; i++) {
			for (int j = 0; j < maxVert; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	} // end of AdjMatrix()

	public void addVertex(T vertLabel) {
		// Check if vertexList is full
		if (numVert < maxVert) {
			// Check if vertex is already in list
			for (int i = 0; i < adjMatrix.length; i++) {
				if (vertexList[i].equals((String) vertLabel)) {
					System.err.println("Vertex has already been added");
					break;
				}
				// If index i already has a vertex move onto next iteration of for loop
				 else if (vertexList[i] != null)
					return;
				// If index i is empty add vertex
				else {
					vertexList[i] = (String) vertLabel;
					numVert++;
					break;
				}
			}			
			
		}
	} // end of addVertex()

	public void addEdge(T srcLabel, T tarLabel) {

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
		// os = new PrintWriter(System.out);
		
		/*for (int i = 0; i < vertexList.length; i++) {
			os.print(vertexList[i] + " ");
		}*/

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