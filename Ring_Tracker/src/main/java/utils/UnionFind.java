package utils;

public class UnionFind {

	private int[] id;	//access to component id (site indexed)
	private int[] sz;	//size of component for roots (site indexed)
	private int numOfComponents;
	
	public UnionFind(int n) {
		this.numOfComponents = n;
		
		id = new int[numOfComponents];
		for (int i = 0; i < numOfComponents; i++) id[i] = i; 
		
		sz = new int[numOfComponents];
		for (int i = 0; i<n; i++) sz[i] = 1;
	}
	
	public int numOfComponents() { return numOfComponents; }
	
	public boolean isConnected(int p, int q) { return find(p) == find(q); }
	
	public int find(int p) {
		while (p != id[p]) p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) return;
		
		//Make smaller root point to larger one
		if (sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i]; }
		else {id[j] = i; sz[i] += sz[j]; }
		numOfComponents--;
	} 
}
