import java.io.*;
import java.util.*;

/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class IndMatrix<T extends Object> implements FriendshipGraph<T> {

	private int maxVert; // maximum number of vertices
	private int numVert; // number of vertices
	private String[] vertexList;
	private String[] edgeList;
	private int[][] indMatrix;
	private int rowIndex; // index number for row in indMatrix[][]
	private int colIndex; // index number for column in indMatrix[][]
	private boolean vertexFound;
	private boolean edgeFound;

	/**
	 * Contructs empty graph.
	 */
	public IndMatrix() {
		maxVert = 4000;
		numVert = 0;
		vertexList = new String[maxVert];
		edgeList = new String[maxVert];
		indMatrix = new int[maxVert][maxVert];
		vertexFound = false;
		edgeFound = false;

		// Initialise all entries in incidence matrix to zero
		for (int i = 0; i < maxVert; i++) {
			for (int j = 0; j < maxVert; j++) {
				indMatrix[i][j] = 0;
			}
		}
	} // end of IndMatrix()

	public void addVertex(T vertLabel) {
		vertexFound = checkIfVertexAdded(vertLabel);

		// Check if vertexList is full
		if (numVert < maxVert) {
			// Check if vertex is already in list
			for (int i = 0; i < vertexList.length; i++) {
				// check if vertLabel has already been added
				if (vertexFound) {
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
		// check if vertex srcLabel has already been added
		vertexFound = checkIfVertexAdded(srcLabel);

		if (vertexFound)
			rowIndex = Arrays.asList(vertexList).indexOf(srcLabel);
		else
			System.err.println("Vertex " + srcLabel + " has not been added");

		// reset vertexFound to false before next check
		vertexFound = false;

		// check if vertex tarLabel has already been added
		vertexFound = checkIfVertexAdded(tarLabel);
		if (!vertexFound)
			System.err.println("Vertex " + tarLabel + " has not been added");

		// check if vertexList contains both srcLabel and tarLabel
		if (checkIfVertexAdded(srcLabel) && checkIfVertexAdded(tarLabel)) {
			// check if edge has been added to edgeList
			edgeFound = checkIfEdgeAdded(srcLabel, tarLabel);
			if (!edgeFound) {
				// If edge hasn't already been added then add edge to edgeList
				for (int i = 0; i < edgeList.length; i = i + 2) {
					if (edgeList[i] != null)
						continue;
					else {
						edgeList[i] = (String) srcLabel + " " + (String) tarLabel;
						edgeList[i + 1] = (String) tarLabel + " " + (String) srcLabel;
						break;
					}
				}

				// Add edge to adjMatrix
				indMatrix[rowIndex][colIndex] = 1;
				indMatrix[colIndex][rowIndex] = 1;
			}
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

		// Copy vertexList to a new array then sort the array vertices alphabetically
		String[] vertices = vertexList.clone();
		Arrays.sort(vertices, Comparator.nullsLast(Comparator.naturalOrder()));

		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] != null) {
				os.print(vertices[i] + " ");
			}
		}
		os.println("");

		os.flush();
	} // end of printVertices()

	public void printEdges(PrintWriter os) {
		os = new PrintWriter(System.out, true);
		
		for (int i = 0; i < edgeList.length; i++)
			if (edgeList[i] != null)
				os.println(edgeList[i]);

		os.flush();
	} // end of printEdges()

	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		// Implement me!

		// if we reach this point, source and target are disconnected
		return disconnectedDist;
	} // end of shortestPathDistance()

	// Check if a vertex has been added
	public boolean checkIfVertexAdded(T vertex) {
		if (Arrays.asList(vertexList).contains(vertex))
			vertexFound = true;
		else
			vertexFound = false;

		return vertexFound;
	}

	public boolean checkIfEdgeAdded(T vertex1, T vertex2) {
		if (Arrays.asList(edgeList).contains((String) vertex1 + " " + (String) vertex2))
			edgeFound = true;
		else
			edgeFound = false;

		return edgeFound;
	}

	// Find the vertex with minimum distance value from the set of vertices not yet
	// included in shortest path tree
	public int minDistance(int distances[], Boolean shortestPathTreeSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < maxVert; v++)
			if (shortestPathTreeSet[v] == false && distances[v] <= min) {
				min = distances[v];
				min_index = v;
			}

		return min_index;
	}

} // end of class IndMatrix
