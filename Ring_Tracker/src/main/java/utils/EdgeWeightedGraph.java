package utils;

import java.util.HashMap;
import java.util.Map;

public class EdgeWeightedGraph {

	private final int NUM_OF_VERTICES;
	private int numberOfEdges;
	//private Bag<Edge>[] vertices;
	private Map<Pixel, Bag<Edge>> pixelsEdges;	//Added map to replace Bag<Edge>[] vertices
	
	/**
	public EdgeWeightedGraph(int numberOfVertices) {
		this.NUM_OF_VERTICES = numberOfVertices;
		this.numberOfEdges = 0;
		
		/**
		 * This cast is correct because it is a private variable made by
		 * the constructor and is never exposed outside of the class
		 *
		@SuppressWarnings("unchecked")
		Bag<Edge>[] tmp = (Bag<Edge>[]) new Bag[NUM_OF_VERTICES];
		vertices = tmp;
		
		for (int i = 0; i < NUM_OF_VERTICES; i++) {
			vertices[i] = new Bag<Edge>();
		}
	}
	*/
	
	
	public EdgeWeightedGraph(int numberOfVertices) {
		this.NUM_OF_VERTICES = numberOfVertices;
		this.numberOfEdges = 0;
		this.pixelsEdges = new HashMap<>();
	}
	
	public int numberOfVertices() { return NUM_OF_VERTICES; }
	public int numberOfEdges() { return numberOfEdges; }
	
	/**
	public void addEdge(Edge edge) {
		int primaryVertex = edge.primaryVertex();
		int secondaryVertex = edge.secondaryVertex();
		vertices[primaryVertex].add(edge);
		vertices[secondaryVertex].add(edge);
		numberOfEdges++;
	}
	*/
	
	public void addEdge(Edge edge) {
		Pixel primaryVertex = edge.primaryVertex();
		Pixel secondaryVertex = edge.secondaryVertex();
		Bag<Edge> edges;
		
		// Add edge to each bag associated with the pixel keys
		if (pixelsEdges.containsKey(primaryVertex)) {
			edges = pixelsEdges.get(primaryVertex);
			edges.add(edge);
		}
		else {
			edges = new Bag<Edge>();
			edges.add(edge);
			pixelsEdges.put(primaryVertex, edges);
		}
		
		if (pixelsEdges.containsKey(secondaryVertex)) {
			edges = pixelsEdges.get(secondaryVertex);
			edges.add(edge);
		}
		else {
			edges = new Bag<Edge>();
			edges.add(edge);
			pixelsEdges.put(secondaryVertex, edges);
		}	
		numberOfEdges++;
	}
	
	//public Iterable<Edge> edgesOfVertex(int vertex) { return vertices[vertex]; }
	public Iterable<Edge> edgesOfVertex(Pixel vertex) { return pixelsEdges.get(vertex); }
	
	public Iterable<Edge> edges() {
		Bag<Edge> bagOfEdges = new Bag<Edge>();
		//for (int i = 0; i < NUM_OF_VERTICES; i++) {					// For Each Vertex
			//for (Edge edge : vertices[i]) {							// For Each Edge in the given Vertex
				//if (edge.other(i) > i) bagOfEdges.add(edge);		// Add the Edge if the connected Vertex is larger than the current one
			//}
		//}
	
		for (Pixel vertex : pixelsEdges.keySet()) {									// For each vertex
			Bag<Edge> bag = pixelsEdges.get(vertex);
			for (Edge edge : bag) { 												// For each Edge in the given Vertex
				if (edge.other(vertex).compareTo(vertex) > 0) bagOfEdges.add(edge);	// Add the Edge if the connected Vertex is larger than the current one
			}
		}
		return bagOfEdges;
	}
}
