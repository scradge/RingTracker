package utils;

import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {

	private Queue<Edge> minSpanTree;
	private UnionFind unionFind;
	private Queue<Edge> edgeQueue;
	
	public KruskalMST(EdgeWeightedGraph graph) {
		
		System.out.println("in Kruskal");
		// Create a new Minimum Spanning Tree
		minSpanTree = new PriorityQueue<>();
		
		// Create a Priority Queue containing all of the edges
		edgeQueue = new PriorityQueue<>();
		for (Edge e : graph.edges()) { 
			edgeQueue.add(e); 
			System.out.println("Adding to edgeQueue");
			}
		
		//Create new unionFind the size of the graph
		//UnionFind unionFind = new UnionFind(graph.numberOfVertices());
		unionFind = new UnionFind();
		
		/**
		 * While the queue of edges is not exhausted
		 * 
		 * AND
		 * 
		 * The minimum spanning tree is not larger than the graph
		 */
		while (!edgeQueue.isEmpty() && minSpanTree.size() < graph.numberOfVertices()-1) {
			Edge edge = edgeQueue.poll();															//Get next strongest edge
			//int primaryVertex = edge.primaryVertex(), secondaryVertex = edge.other(primaryVertex);
			Pixel primaryVertex = edge.primaryVertex(), secondaryVertex = edge.other(primaryVertex);	//Get the associated vertices of the current edge
			
			if (unionFind.isConnected(primaryVertex, secondaryVertex)) continue;					//If the two vertices are already connected dont add the edge
			
			unionFind.union(primaryVertex, secondaryVertex);										//Else Connect the edges in UnionFind
			minSpanTree.add(edge);																	//Add the edge to the minimum spanning tree
		}		
	}
	
	public Iterable<Edge> edges() { return minSpanTree; }
	
	public int getUnitCount() { 
		if (unionFind != null) return unionFind.numOfComponents(); 
		return 0;
	}
	
	public Queue<Pixel> getUnit(int index) {
		return unionFind.getComponent(index);
	}
}
