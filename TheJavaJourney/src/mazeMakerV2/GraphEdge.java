package mazeMakerV2;

import java.util.HashSet;
import java.util.Random;

/**
 * An instance of this class represents an edge in a graph that connects two vertices. 
 */
public class GraphEdge {
	protected GraphNode a;
	protected GraphNode b;
	
	public GraphEdge(GraphNode a, GraphNode b) {
		this.a = a;
		this.b = b; 
	}
	
	public HashSet<GraphNode> getEndVertices() {
		HashSet<GraphNode> vertices = new HashSet<GraphNode>();
		vertices.add(a);
		vertices.add(b);
		return vertices;
	}
	
	public WeightedGraphEdge addRandomWeight(int weightLowerBound, int weightUpperBound) {
		Random RandomIntegerGenerator = new Random();
		int randomInteger = RandomIntegerGenerator.nextInt(weightUpperBound - weightLowerBound) + weightLowerBound;
		return new WeightedGraphEdge(this.a, this.b, randomInteger);
	}

	@Override
	public String toString() {
		return "GraphEdge [a=" + a + ", b=" + b + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphEdge other = (GraphEdge) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
	
	
}
