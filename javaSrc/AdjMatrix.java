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
	private int numEdge; // number of edges
	private String[] vertexList;
	private String[] edgeList;
	private int[][] adjMatrix;
	private int rowIndex; // index number for row in adjMatrix[][]
	private int colIndex; // index number for column in adjMatrix[][]
	private boolean vertexFound;
	private boolean edgeFound;

	/**
	 * Contructs empty graph.
	 */
	public AdjMatrix() {
		maxVert = 4038;
		numVert = 0;
		numEdge = 0;
		vertexList = new String[maxVert];
		edgeList = new String[maxVert];
		adjMatrix = new int[maxVert][maxVert];
		vertexFound = false;
		edgeFound = false;

		// If the adjMatrix array is too small to add more vertices, copy adjMatrix to a
		// new, larger array
		if (numVert >= maxVert) {
			int[][] newAdjMatrix = new int[maxVert * 2][maxVert * 2];

			for (int i = 0; i < adjMatrix.length; i++) {
				for (int j = 0; j < adjMatrix[i].length; j++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}

			adjMatrix = new int[maxVert * 2][maxVert * 2];

			// Copy values from new array back to adjMatrix
			for (int i = 0; i < newAdjMatrix.length; i++) {
				for (int j = 0; j < newAdjMatrix[i].length; j++) {
					adjMatrix[i][j] = newAdjMatrix[i][j];
				}
			}
		}

		// Initialise all entries in adjacency matrix to zero
		for (int i = 0; i < maxVert; i++) {
			for (int j = 0; j < maxVert; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	} // end of AdjMatrix()

	public void addVertex(T vertLabel) {
		// Check if vertexList is full
		if (numVert >= maxVert) {
			// Copy vertexList to a temporary larger array
			String[] temp = Arrays.copyOf(vertexList, maxVert * 2);
			// Copy temporary array back to vertexList
			vertexList = temp;
		}

		vertexFound = checkIfVertexAdded(vertLabel);

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
	} // end of addVertex()

	public void addEdge(T srcLabel, T tarLabel) {
		// Check if edgeList is full
		if (numEdge >= maxVert) {
			// Copy edgeList to a temporary larger array
			String[] temp = Arrays.copyOf(edgeList, maxVert * 2);
			// Copy temporary array back to edgeList
			edgeList = temp;
		}

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
		if (vertexFound)
			colIndex = Arrays.asList(vertexList).indexOf(tarLabel);
		else
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
				adjMatrix[rowIndex][colIndex] = 1;
				adjMatrix[colIndex][rowIndex] = 1;
				numEdge++;
			}
		}

	} // end of addEdge()

	@SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
		ArrayList<T> neighbours = new ArrayList<T>();
		T neighbour;

		// check if vertex has been added
		vertexFound = checkIfVertexAdded(vertLabel);

		if (vertexFound) {
			rowIndex = Arrays.asList(vertexList).indexOf(vertLabel);
			// check which vertices in adjMatrix have been added as an edge for vertLabel
			for (int i = 0; i < adjMatrix.length; i++) {
				if (adjMatrix[rowIndex][i] == 1) {
					colIndex = i;
					neighbour = (T) vertexList[colIndex];
					neighbours.add(neighbour);
				}
			}
		} else
			System.err.println("Vertex " + vertLabel + " has not been added");

		return neighbours;
	} // end of neighbours()

	public void removeVertex(T vertLabel) {
		// check if vertLabel has already been added
		vertexFound = checkIfVertexAdded(vertLabel);

		if (vertexFound) {
			rowIndex = Arrays.asList(vertexList).indexOf(vertLabel);
			vertexList[rowIndex] = null;
			numVert--;

			// remove vertex from adjMatrix
			for (int i = 0; i < adjMatrix.length; i++) {
				adjMatrix[rowIndex][i] = 0;
				adjMatrix[i][rowIndex] = 0;
			}
		} else
			System.err.println("Vertex " + vertLabel + " has not been added");

		// check if edge has already been added
		for (int i = 0; i < edgeList.length; i++) {
			String test = edgeList[i];
			if (test != null && test.contains((String) vertLabel)) {
				// remove edge with vertex vertLabel from edgeList
				rowIndex = Arrays.asList(edgeList).indexOf(test);
				edgeList[rowIndex] = null;
				numEdge--;
			}
		}

	} // end of removeVertex()

	public void removeEdge(T srcLabel, T tarLabel) {
		// check if vertex srcLabel is in vertexList
		vertexFound = checkIfVertexAdded(srcLabel);

		if (!vertexFound)
			System.err.println("Vertex " + srcLabel + " has not been added");

		// reset vertexFound to false before next check
		vertexFound = false;

		// check if vertex tarLabel has already been added
		vertexFound = checkIfVertexAdded(tarLabel);

		if (!vertexFound)
			System.err.println("Vertex " + tarLabel + " has not been added");

		// check if edge has already been added
		edgeFound = checkIfEdgeAdded(srcLabel, tarLabel);

		if (edgeFound) {
			// remove edge from edgeList
			rowIndex = Arrays.asList(edgeList).indexOf((String) srcLabel + " " + (String) tarLabel);
			edgeList[rowIndex] = null;
			rowIndex = Arrays.asList(edgeList).indexOf((String) tarLabel + " " + (String) srcLabel);
			edgeList[rowIndex] = null;

			// remove edge from adjMatrix
			rowIndex = Arrays.asList(vertexList).indexOf(srcLabel);
			colIndex = Arrays.asList(vertexList).indexOf(tarLabel);
			adjMatrix[rowIndex][colIndex] = 0;
			adjMatrix[colIndex][rowIndex] = 0;
			numEdge--;
		} else
			System.err.println("Edge " + srcLabel + " " + tarLabel + " has not been added");

	} // end of removeEdges()

	public void printVertices(PrintWriter os) {
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
	} // end of printEdges()

	public void printEdges(PrintWriter os) {
		for (int i = 0; i < edgeList.length; i++)
			if (edgeList[i] != null)
				os.println(edgeList[i]);

		os.flush();
	} // end of printEdges()

	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		int connectedDist = -1;

		// Check if vertex vertLabel1 has already been added
		vertexFound = checkIfVertexAdded(vertLabel1);

		if (vertexFound)
			rowIndex = Arrays.asList(vertexList).indexOf(vertLabel1);
		else
			System.err.println("Vertex " + vertLabel1 + " has not been added");

		// Reset vertexFound to false before next check
		vertexFound = false;

		// Check if vertex vertLabel2 has already been added
		vertexFound = checkIfVertexAdded(vertLabel2);

		if (vertexFound)
			colIndex = Arrays.asList(vertexList).indexOf(vertLabel2);
		else
			System.err.println("Vertex " + vertLabel2 + " has not been added");

		// Check if both vertices vertLabel1 and vertLabel 2 have been added
		if (Arrays.asList(vertexList).contains(vertLabel1) && Arrays.asList(vertexList).contains(vertLabel2)) {
			// Search for shortest path using Djikstra's Algorithm
			// Based on code from
			// https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
			int distances[] = new int[maxVert];
			// shortestPathTreeSet[i] will be true if vertex i is included in shortest path
			// tree or shortest distance from
			// vertLabel1 to vertLabel2 is finalised
			Boolean shortestPathTreeSet[] = new Boolean[maxVert];

			// Initialise all distances as infinite and shortestPathTreeSet[] as false
			for (int i = 0; i < maxVert; i++) {
				distances[i] = Integer.MAX_VALUE;
				shortestPathTreeSet[i] = false;
			}

			// Distance of vertLabel1 (source vertex) from itself is always 0
			distances[rowIndex] = 0;

			// Find shortest path between vertLabel1 and vertLabel2
			for (int count = 0; count < maxVert - 1; count++) {
				// Pick the minimum distance vertex from the set of vertices not yet processed
				int u = minDistance(distances, shortestPathTreeSet);

				// Mark the picked vertex as processed
				shortestPathTreeSet[u] = true;

				// Update distance value of the adjacent vertices of the picked vertex
				for (int v = 0; v < maxVert; v++) {
					// Update distances[v] only if it is not in shortestPathSet, there is an edge
					// from u to v, and
					// total weight path from vertLabel1 to v through u is smaller than current
					// value of distances[v]
					if (!shortestPathTreeSet[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE
							&& distances[u] + adjMatrix[u][v] < distances[v]) {
						distances[v] = distances[u] + adjMatrix[u][v];
					}
				}
			}

			connectedDist = distances[colIndex];
		}

		// If there is a connection between source and target, return connectedDist
		if (connectedDist != Integer.MAX_VALUE)
			return connectedDist;
		// If we reach this point, source and target are disconnected
		else
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

} // end of class AdjMatrix
