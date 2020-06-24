package mazeMakerV2;

public class WeightedGraphEdge extends GraphEdge {

	private int weight;
	
	public WeightedGraphEdge(GraphNode a, GraphNode b, int weight) {
		super(a, b);
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
