package utils;

public class EdgeWeightedGraph {

	private final int NUM_OF_VERTICES;
	private int numberOfEdges;
	private Bag<Edge>[] vertices;
	
	
	public EdgeWeightedGraph(int numberOfVertices) {
		this.NUM_OF_VERTICES = numberOfVertices;
		this.numberOfEdges = 0;
		
		/**
		 * This cast is correct because it is a private variable made by
		 * the constructor and is never exposed outside of the class
		 */
		@SuppressWarnings("unchecked")
		Bag<Edge>[] tmp = (Bag<Edge>[]) new Bag[NUM_OF_VERTICES];
		vertices = tmp;
		
		for (int i = 0; i < NUM_OF_VERTICES; i++) {
			vertices[i] = new Bag<Edge>();
		}
	}
	
	public int numberOfVertices() { return NUM_OF_VERTICES; }
	public int numberOfEdges() { return numberOfEdges; }
	
	public void addEdge(Edge edge) {
		int primaryVertex = edge.primaryVertex(), secondaryVertex = edge.secondaryVertex();
		vertices[primaryVertex].add(edge);
		vertices[secondaryVertex].add(edge);
		numberOfEdges++;
	}
	
	public Iterable<Edge> edgesOfVertex(int vertex) { return vertices[vertex]; }
	
	public Iterable<Edge> edges() {
		Bag<Edge> bagOfEdges = new Bag<Edge>();
		for (int i = 0; i < NUM_OF_VERTICES; i++) {					// For Each Vertex
			for (Edge edge : vertices[i]) {							// For Each Edge in the given Vertex
				if (edge.other(i) > i) bagOfEdges.add(edge);		// Add the Edge if the connected Vertex is larger than the current one
			}
		}
		return bagOfEdges;
	}
}
