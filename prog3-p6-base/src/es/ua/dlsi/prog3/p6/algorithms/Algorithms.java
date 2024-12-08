package es.ua.dlsi.prog3.p6.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import es.ua.dlsi.prog3.p6.graph.Graph;
import es.ua.dlsi.prog3.p6.graph.Node;
import es.ua.dlsi.prog3.p6.graph.NodeNotFoundException;

/**
 * Facade class giving easy input to the available algorithms from the library
 * @author drizo
 */
public class Algorithms {
	/**
	 * Returns a Depth First Search traversal of the graph starting with the provided node
	 * @param graph Graph to work on
	 * @param fromNode starting node
	 * @return List of visited nodes
	 */
	public static List<Node> dfs(Graph graph, Node fromNode) {
		DFS dfs = new DFS(graph);
		dfs.run(fromNode);
		return dfs.getVisitSequence();
	}

	/**
	 * It computes the cost to traverse from the source node to the target one
	 * @param graph Graph to work on
	 * @param fromNode Source node
	 * @param toNode Target node
	 * @return Cost
	 * @throws NodeNotFoundException Thrown if any of the nodes do not belong to the graph 
	 * @throws GraphAlgorithmException Thrown if the graph has no edges
	 */
	public static int shortestPathCost(Graph graph, Node fromNode, Node toNode) throws NodeNotFoundException, GraphAlgorithmException {
		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
		dijkstraShortestPath.compute(fromNode);

		// this is not the most efficient method, but it's what we have available
		if (!graph.contains(toNode)) {
			throw new NodeNotFoundException(toNode);
		}
		
		Integer distance = dijkstraShortestPath.getComputedDistances().get(toNode);
		if (distance == null) {
			throw new GraphAlgorithmException("Cannot find a distance to the target node " + toNode);
		}
				
		return distance;
	}
	
	/**
	 * It exports the graph to a file using the Graphviz Dot format
	 * @param file Output file
	 * @param graph Graph to be exported
	 * @throws FileNotFoundException Thrown when the file to be written does not exist (maybe because its folder does not exist)
	 */
	public static void exportDot(File file, Graph graph) throws FileNotFoundException {
		DotExporter dotExporter = new DotExporter();
		dotExporter.export(file, graph);
	}
}