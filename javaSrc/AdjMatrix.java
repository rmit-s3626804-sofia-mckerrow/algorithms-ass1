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
					System.err.println("Vertex " + vertLabel + " has already been added");
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

		// check if vertexList contains both srcLabel and tarLabel
		if (Arrays.asList(vertexList).contains(srcLabel) && Arrays.asList(vertexList).contains(tarLabel)) {
			// check if edge has been added to edgeList
			if (!(Arrays.asList(edgeList).contains((String) srcLabel + " " + (String) tarLabel))) {
				// If edge hasn't already been added then add edge to edgeList
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
		}

	} // end of addEdge()

	public ArrayList<T> neighbours(T vertLabel) {
		ArrayList<T> neighbours = new ArrayList<T>();

		return neighbours;
	} // end of neighbours()

	public void removeVertex(T vertLabel) {
		boolean vertexFound = false;

		// check if vertLabel has already been added
		for (int i = 0; i < vertexList.length; i++) {
			if (Arrays.asList(vertexList).contains(vertLabel)) {
				// remove vertex from vertexList
				rowIndex = Arrays.asList(vertexList).indexOf(vertLabel);
				vertexList[rowIndex] = null;
				vertexFound = true;
				break;
			}
		}

		// if vertLabel is not in vertexList print error message
		if (!vertexFound)
			System.err.println("Vertex " + vertLabel + " has not been added");

		// check if edge has already been added
		for (int i = 0; i < edgeList.length; i++) {
			String test = edgeList[i];
			if (test != null && test.contains((String) vertLabel)) {
				// remove edge with vertex vertLabel from edgeList
				rowIndex = Arrays.asList(edgeList).indexOf(test);
				edgeList[rowIndex] = null;
			}
		}

		// check if vertLabel has already been added
		for (int i = 0; i < vertexList.length; i++) {
			if (Arrays.asList(vertexList).contains(vertLabel)) {
				// remove edge from adjMatrix
				rowIndex = Arrays.asList(vertexList).indexOf(vertLabel);
				for (int j = 0; j < adjMatrix.length; j++) {
					adjMatrix[rowIndex][j] = 0;
					adjMatrix[j][rowIndex] = 0;
				}
			}
		}

	} // end of removeVertex()

	public void removeEdge(T srcLabel, T tarLabel) {
		boolean vertexFound = false;
		boolean edgeFound = false;

		// check if edge has already been added
		for (int i = 0; i < edgeList.length; i++) {
			if (Arrays.asList(edgeList).contains((String) srcLabel + " " + (String) tarLabel)) {
				// remove edge from edgeList
				rowIndex = Arrays.asList(edgeList).indexOf((String) srcLabel + " " + (String) tarLabel);
				edgeList[rowIndex] = null;

				// remove edge from adjMatrix
				rowIndex = Arrays.asList(vertexList).indexOf(srcLabel);
				colIndex = Arrays.asList(vertexList).indexOf(tarLabel);
				adjMatrix[rowIndex][colIndex] = 0;
				adjMatrix[colIndex][rowIndex] = 0;

				edgeFound = true;
				break;
			}
		}

		if (!edgeFound)
			System.err.println("Edge " + srcLabel + " " + tarLabel + " has not been added");

		// check if vertex srcLabel is in vertexList
		for (int i = 0; i < vertexList.length; i++) {
			if (Arrays.asList(vertexList).contains(srcLabel)) {
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
				vertexFound = true;
				break;
			}
		}
		if (!vertexFound)
			System.err.println("Vertex " + tarLabel + " has not been added");

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