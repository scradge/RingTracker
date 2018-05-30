package utils;

public class UnionFind {//implements Iterable{

	private int[] id;	//access to component id (site indexed)
	private int[] componentSize;	//size of component for roots (site indexed)
	private int numOfComponents;
	
	public UnionFind(int numberOfVertices) {
		this.numOfComponents = numberOfVertices;
		
		id = new int[numOfComponents];
		for (int i = 0; i < numOfComponents; i++) id[i] = i; 
		
		componentSize = new int[numOfComponents];
		for (int i = 0; i<numOfComponents; i++) componentSize[i] = 1;
	}
	
	public int numOfComponents() { return numOfComponents; }
	
	public boolean isConnected(int primaryVertex, int secondaryVertex) { return find(primaryVertex) == find(secondaryVertex); }
	
	public int find(int p) {
		while (p != id[p]) p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) return;
		
		//Make smaller root point to larger one
		if (componentSize[i] < componentSize[j]) {id[i] = j; componentSize[j] += componentSize[i]; }
		else {id[j] = i; componentSize[i] += componentSize[j]; }
		numOfComponents--;
	} 
/**	
public Iterator<Item> iterator() { return new ListIterator(); }
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;
		
		public boolean hasNext() { return current != null; }
		
		public void remove() {}
		
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
*/
}
