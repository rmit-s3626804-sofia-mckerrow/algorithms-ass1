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
	private int numEdge; // number of edges
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
		maxVert = 4038;
		numVert = 0;
		numEdge = 0;
		vertexList = new String[maxVert];
		edgeList = new String[maxVert];
		indMatrix = new int[maxVert][maxVert];
		vertexFound = false;
		edgeFound = false;

		// If the indMatrix array is too small to add more vertices, copy indMatrix to a
		// new, larger array
		if (numVert >= maxVert) {
			int[][] newIndMatrix = new int[maxVert * 2][maxVert * 2];

			for (int i = 0; i < indMatrix.length; i++) {
				for (int j = 0; j < indMatrix[i].length; j++) {
					newIndMatrix[i][j] = indMatrix[i][j];
				}
			}

			indMatrix = new int[maxVert * 2][maxVert * 2];

			// Copy values from new array back to adjMatrix
			for (int i = 0; i < newIndMatrix.length; i++) {
				for (int j = 0; j < newIndMatrix[i].length; j++) {
					indMatrix[i][j] = newIndMatrix[i][j];
				}
			}
		}

		// Initialise all entries in incidence matrix to zero
		for (int i = 0; i < maxVert; i++) {
			for (int j = 0; j < maxVert; j++) {
				indMatrix[i][j] = 0;
			}
		}
	} // end of IndMatrix()

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
		int rowIndexSrc = 0;
		int rowIndexTar = 0;

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
			rowIndexSrc = Arrays.asList(vertexList).indexOf(srcLabel);
		else
			System.err.println("Vertex " + srcLabel + " has not been added");

		// reset vertexFound to false before next check
		vertexFound = false;

		// check if vertex tarLabel has already been added
		vertexFound = checkIfVertexAdded(tarLabel);
		if (vertexFound)
			rowIndexTar = Arrays.asList(vertexList).indexOf(tarLabel);
		else
			System.err.println("Vertex " + tarLabel + " has not been added");

		// check if vertexList contains both srcLabel and tarLabel
		if (checkIfVertexAdded(srcLabel) && checkIfVertexAdded(tarLabel)) {
			// check if edge has been added to edgeList
			edgeFound = checkIfEdgeAdded(srcLabel, tarLabel);
			if (!edgeFound) {
				// If edge hasn't already been added then add edge to edgeList
				for (int i = 0; i < edgeList.length; i++) {
					if (edgeList[i] != null)
						continue;
					else {
						edgeList[i] = (String) srcLabel + " " + (String) tarLabel;
						colIndex = i;
						break;
					}
				}

				// Add edge to adjMatrix
				indMatrix[rowIndexSrc][colIndex] = 1;
				indMatrix[rowIndexTar][colIndex] = 1;
				numEdge++;
			}
		}
	} // end of addEdge()

	@SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
		ArrayList<T> neighbours = new ArrayList<T>();
		T neighbour;
		String edge1;
		String edge2;
		String[] edgeListSplit;

		// check if vertex has been added
		vertexFound = checkIfVertexAdded(vertLabel);

		if (vertexFound) {
			rowIndex = Arrays.asList(vertexList).indexOf(vertLabel);
			// check which vertices in intMatrix have been added as an edge for vertLabel
			for (int i = 0; i < indMatrix.length; i++) {
				if (indMatrix[rowIndex][i] == 1) {
					if (edgeList[i] != null) {
						// Split the concatenated string in edgeList[i] into two separate strings, one
						// for each vertex
						edgeListSplit = edgeList[i].split("\\s");
						edge1 = edgeListSplit[0];
						edge2 = edgeListSplit[1];

						if (!edge1.equals(vertLabel)) {
							neighbour = (T) edge1;
							neighbours.add(neighbour);
						}

						if (!edge2.equals(vertLabel)) {
							neighbour = (T) edge2;
							neighbours.add(neighbour);
						}
					}
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

			// remove vertex from indMatrix
			for (int i = 0; i < indMatrix.length; i++) {
				indMatrix[rowIndex][i] = 0;
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
		int rowIndexSrc = 0;
		int rowIndexTar = 0;

		// check if vertex srcLabel has already been added
		vertexFound = checkIfVertexAdded(srcLabel);

		if (vertexFound)
			rowIndexSrc = Arrays.asList(vertexList).indexOf(srcLabel);
		else
			System.err.println("Vertex " + srcLabel + " has not been added");

		// reset vertexFound to false before next check
		vertexFound = false;

		// check if vertex tarLabel has already been added
		vertexFound = checkIfVertexAdded(tarLabel);
		if (vertexFound)
			rowIndexTar = Arrays.asList(vertexList).indexOf(tarLabel);
		else
			System.err.println("Vertex " + srcLabel + " has not been added");

		// check if edge has already been added
		edgeFound = checkIfEdgeAdded(srcLabel, tarLabel);

		if (edgeFound) {
			// remove edge from edgeList
			colIndex = Arrays.asList(edgeList).indexOf((String) srcLabel + " " + (String) tarLabel);
			edgeList[colIndex] = null;

			// remove edge from adjMatrix
			indMatrix[rowIndexSrc][colIndex] = 0;
			indMatrix[rowIndexTar][colIndex] = 0;
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
	} // end of printVertices()

	public void printEdges(PrintWriter os) {
		String edge1;
		String edge2;
		ArrayList<String> edges = new ArrayList<String>();

		for (int i = 0; i < edgeList.length; i++) {
			if (edgeList[i] != null) {
				// Split the concatenated string in edgeList[i] into two separate strings, one
				// for each vertex
				String[] edgeListSplit = edgeList[i].split("\\s");
				edge1 = edgeListSplit[0];
				edge2 = edgeListSplit[1];
				edges.add(edge1 + " " + edge2);
				edges.add(edge2 + " " + edge1);
			}
		}

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i) != null)
				os.println(edges.get(i));
		}

		os.flush();
	} // end of printEdges()

	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		int connectedDist = -1;
		String vertex;

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

		if (!vertexFound)
			System.err.println("Vertex " + vertLabel2 + " has not been added");

		// Check if both vertices vertLabel1 and vertLabel 2 have been added
		if (Arrays.asList(vertexList).contains(vertLabel1) && Arrays.asList(vertexList).contains(vertLabel2)) {
			// Search for shortest path using Djikstra's Algorithm
			// Based on code from
			// https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
			int distances[] = new int[maxVert];
			// shortestPathTreeSet[i] will be true if vertex i is included in shortest path
			// tree or shortest distance from vertLabel1 to vertLabel2 is finalised
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
					if (!shortestPathTreeSet[v] && indMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE
							&& distances[u] + indMatrix[u][v] < distances[v]) {
						distances[v] = distances[u] + indMatrix[u][v];
					}
				}
			}

			for (int i = 0; i < edgeList.length; i++) {
				if (edgeList[i] != null) {
					// Split the concatenated string in edgeList[i] into two separate strings, one
					// for each vertex
					String[] edgeListSplit = edgeList[i].split("\\s");
					vertex = edgeListSplit[0];
					// Check if edge with vertex vertLabel2 has been added
					if (vertex.equals(vertLabel2)) {
						colIndex = i;
						break;
					} else
						colIndex = -1;
				}
			}

			if (colIndex >= 0)
				connectedDist = distances[colIndex];
			else
				connectedDist = -1;
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

} // end of class IndMatrix
