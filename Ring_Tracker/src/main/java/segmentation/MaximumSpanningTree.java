package segmentation;

import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumSpanningTree {
	/**
	private boolean[] marked;
	private Queue<Edge> mst;
	private PriorityQueue<Edge> pq;
	
	public MaximumSpanningTree(EdgeWeightedGraph G) {
		pq = new PriorityQueue<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		
		visit (G, O);
		
		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.add(e);
			if (!marked[v]) visit (G, v);
			if (!marked[w]) visit (G, w);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)]) pq.insert(e);
	}
	
	public Iterable<Edge> edges() {
		return mst;
	}
	
	//public double weight()
	 */
 }
