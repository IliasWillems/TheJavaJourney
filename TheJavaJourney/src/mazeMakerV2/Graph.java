package mazeMakerV2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Graph {
	private Set<GraphNode> vertices;
	private Set<GraphEdge> edges;
	
	public Graph() {}
	
	public Set<GraphNode> getVertices() {
		Set<GraphNode> copiedVertices = new HashSet<GraphNode>();
		copiedVertices.addAll(vertices);
		return copiedVertices;
	}
	
	public Set<GraphEdge> getEdges() {
		Set<GraphEdge> copiedEdges = new HashSet<GraphEdge>();
		copiedEdges.addAll(edges);
		return copiedEdges;
	}
	
	public void addVertex(GraphNode newVertex) {
		this.vertices.add(newVertex);
	}
	
	public void addEdge(GraphEdge edge) {
		for (GraphNode vertex : edge.getEndVertices()) {
			if (!vertices.contains(vertex))
				throw new IllegalArgumentException("At least one of the required endpoints are not contained in the graph");
		}
		
		this.edges.add(edge);
	}
	
	public void addVertices(Set<GraphNode> vertices) {
		for (GraphNode vertex : vertices) {
			this.vertices.add(vertex);
		}
	}
	
	public void connect(GraphNode a, GraphNode b) {
		if (!vertices.contains(a) | !vertices.contains(b)) {
			throw new IllegalArgumentException("At least one of the given vertices is not contained by the graph.");
		}
		
		this.edges.add(new GraphEdge(a, b));
	}
	
	public void removeVertex(GraphNode vertex) {
		if (!vertices.contains(vertex))
			throw new IllegalArgumentException("The given vertex is not contained in the graph.");
		
		vertices.remove(vertex);
	}
	
	public void removeEdge(GraphEdge edge) {
		if (!edges.contains(edge))
			throw new IllegalArgumentException("The edge is not contained in the graph.");
	}
	
	public GraphNode getRandomVertex() {
		Random RandomIntegerGenerator = new Random();
		int index = RandomIntegerGenerator.nextInt(vertices.size());
		int i = 0;
		GraphNode randomVertex = null;
		for (GraphNode vertex : vertices) {
			if (i == index) {
				randomVertex = vertex;
			}
		}
		return randomVertex;
	}

	public HashSet<GraphEdge> getEdgesConnectedToVertex(GraphNode a) {
		HashSet<GraphEdge> connectedEdges = new HashSet<GraphEdge>();
		for(GraphEdge edge : edges) {
			if (edge.getEndVertices().contains(a))
				connectedEdges.add(edge);
		}
		return connectedEdges;
	}
	
	public WeightedGraph toRandomlyWeightedGraph(int weightLowerBound, int weightUpperBound) {
		WeightedGraph weightedGraph = new WeightedGraph();
		weightedGraph.addVertices(this.getVertices());
		
		for (GraphEdge edge : this.edges) {
			weightedGraph.addEdge(edge.addRandomWeight(weightLowerBound, weightUpperBound));
		}
		
		return weightedGraph;
	}
}
