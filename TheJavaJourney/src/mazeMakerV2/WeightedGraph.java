package mazeMakerV2;

import java.util.HashSet;
import java.util.Set;

public class WeightedGraph extends Graph {
	private Set<GraphNode> vertices;
	private Set<WeightedGraphEdge> weightedEdges;
	
	public WeightedGraph() {}
	
	// This methode is not yet finished
	public Graph constructMinimumSpanningTree() {
		Graph mst = new Graph();
		mst.addVertices(this.getVertices());
		GraphNode v = mst.getRandomVertex();
		
		// Find all edges that connect to the vertex at hand
		HashSet<GraphEdge> connectedEdges = mst.getEdgesConnectedToVertex(v);
		
		// Find the edge with the smallest weight.
		float minimumWeight = Float.POSITIVE_INFINITY;
		WeightedGraphEdge minimumWeightEdge = null;
		
		for (GraphEdge weightedEdge : mst.getEdgesConnectedToVertex(v)) {
			
			if (!(weightedEdge instanceof WeightedGraphEdge))
				throw new IllegalArgumentException("The edge is not an instance of 'weightedEdge'.");
			
			float weigth = (float) ((WeightedGraphEdge) weightedEdge).getWeight();
			if (weigth < minimumWeight) {
				minimumWeight = weigth;
				minimumWeightEdge = (WeightedGraphEdge) weightedEdge;
			}
		}
		
		// Add that edge and the corresponding vertex to the minimum spanning tree (one vertex is already contained by the spanning tree)
		for (GraphNode vertex : minimumWeightEdge.getEndVertices()) {
			if (!this.vertices.contains(vertex))
				this.addVertex(vertex);
		}
		this.addEdge(minimumWeightEdge);
		
		return null;
	}
}
