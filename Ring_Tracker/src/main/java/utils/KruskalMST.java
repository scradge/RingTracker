package utils;

import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {

	private Queue<Edge> minSpanTree;
	private UnionFind unionFind;
	
	public KruskalMST(EdgeWeightedGraph graph) {
		
		// Create a new Minimum Spanning Tree
		minSpanTree = new PriorityQueue<>();
		
		// Create a Priority Queue containing all of the edges
		Queue<Edge> edgeQueue = new PriorityQueue<>();
		for (Edge e : graph.edges()) { edgeQueue.add(e); }
		
		//Create new unionFind the size of the graph
		UnionFind unionFind = new UnionFind(graph.numberOfVertices());
		
		/**
		 * While the queue of edges is not exhausted
		 * 
		 * AND
		 * 
		 * The minimum spanning tree is not larger than the graph
		 */
		while (!edgeQueue.isEmpty() && minSpanTree.size() < graph.numberOfVertices()-1) {
			Edge edge = edgeQueue.poll();															//Get next strongest edge
			int primaryVertex = edge.primaryVertex(), secondaryVertex = edge.other(primaryVertex);	//Get the associated vertices of the current edge
			
			if (unionFind.isConnected(primaryVertex, secondaryVertex)) continue;					//If the two vertices are already connected dont add the edge
			
			unionFind.union(primaryVertex, secondaryVertex);										//Else Connect the edges in UnionFind
			minSpanTree.add(edge);																	//Add the edge to the minimum spanning tree
		}		
	}
	
	public Iterable<Edge> edges() { return minSpanTree; }
	
	public Iterable<UnionFind> components() {return unionFind; }
	
	public int getUnitCount() { return unionFind.numOfComponents(); }
}
