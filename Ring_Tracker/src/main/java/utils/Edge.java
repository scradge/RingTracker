package utils;

public class Edge implements Comparable<Edge>{
	
	private final int PRIMARY_VERTEX;
	private final int SECONDARY_VERTEX;
	private final double EDGE_WEIGHT;
	
	public Edge (int primaryVertex, int secondaryVertex, double edgeWeight) {
		this.PRIMARY_VERTEX = primaryVertex;
		this .SECONDARY_VERTEX = secondaryVertex;
		this.EDGE_WEIGHT = edgeWeight;
	}
	
	public double weight() { return EDGE_WEIGHT; }
	
	public int primaryVertex() { return PRIMARY_VERTEX; }
	
	public int secondaryVertex() { return SECONDARY_VERTEX; }
	
	public int other(int vertex) {
		if (vertex == PRIMARY_VERTEX) return SECONDARY_VERTEX;
		else if (vertex == SECONDARY_VERTEX) return PRIMARY_VERTEX;
		else throw new IllegalArgumentException();
	}
	
	public int compareTo(Edge that) {
		if (this.weight() < that.weight()) return -1;
		else if (this.weight() > that.weight()) return +1;
		else return 0;
	}
	
	public String toString() { return String.format("%d-%d %.5f", PRIMARY_VERTEX, SECONDARY_VERTEX, EDGE_WEIGHT); }
}
