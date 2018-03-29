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
	private String[] edgeList;
	private int[][] adjMatrix;
	private int rowIndex; // index number for row in adjMatrix[][]
	private int colIndex; // index number for column in adjMatrix[][]

	/**
	 * Contructs empty graph.
	 */
	public AdjMatrix() {
		maxVert = 4000;
		numVert = 0;
		vertexList = new String[maxVert];
		edgeList = new String[maxVert];
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
			for (int i = 0; i < vertexList.length; i++) {
				// check if vertLabel has already been added
				if (Arrays.asList(vertexList).contains(vertLabel)) {
					System.err.println("Vertex has already been added");
					break;
				}
				// if index i already has a vertex move onto next iteration of for loop
				else if (vertexList[i] != null)
					continue;
				// if index i is empty add vertLabel as vertex
				else {
					vertexList[i] = (String) vertLabel;
					numVert++;
					break;
				}
			}
		}
	} // end of addVertex()

	public void addEdge(T srcLabel, T tarLabel) {
		boolean vertexFound = false;

		// check if vertex srcLabel has already been added
		for (int i = 0; i < vertexList.length; i++) {
			if (Arrays.asList(vertexList).contains(srcLabel)) {
				rowIndex = Arrays.asList(vertexList).indexOf(srcLabel);
				vertexFound = true;
				break;
			}
		}
		if (!vertexFound)
			System.err.println("Vertex " + srcLabel + " has not been added");
		
		// reset vertexFound to false before next check
		vertexFound = false;

		// check if vertex tarLabel has already been added
		for (int i = 0; i < vertexList.length; i++) {
			if (Arrays.asList(vertexList).contains(tarLabel)) {
				colIndex = Arrays.asList(vertexList).indexOf(tarLabel);
				vertexFound = true;
				break;
			}
		}
		if (!vertexFound)
			System.err.println("Vertex " + tarLabel + " has not been added");
		
		// Check if vertexList contains both srcLabel and tarLabel
		if (Arrays.asList(vertexList).contains(srcLabel) && Arrays.asList(vertexList).contains(tarLabel)) {
			// Add edge to edgeList
			for (int i = 0; i < edgeList.length; i++) {
				if (edgeList[i] != null)
					continue;
				else {
					edgeList[i] = (String) srcLabel + " " + (String) tarLabel;
					break;
				}
			}
			
			// Add edge to adjMatrix
			adjMatrix[rowIndex][colIndex] = 1;
			adjMatrix[colIndex][rowIndex] = 1;
		}

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
		os = new PrintWriter(System.out, true);

		for (int i = 0; i < vertexList.length; i++) {
			if (vertexList[i] != null) {
				os.print(vertexList[i] + " ");
			}
		}

		os.flush();
	} // end of printEdges()

	public void printEdges(PrintWriter os) {
		os = new PrintWriter(System.out, true);

		for (int i = 0; i < edgeList.length; i++) {
			if (edgeList[i] != null) {
				os.println(edgeList[i]);
			}
		}

		os.flush();
	} // end of printEdges()

	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		// Implement me!

		// if we reach this point, source and target are disconnected
		return disconnectedDist;
	} // end of shortestPathDistance()

} // end of class AdjMatrix