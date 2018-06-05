package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class UnionFind {//implements Iterable{

	private int[] roots;	//points to root of component
	private int[] componentSize;	//size of component for roots (site indexed)
	private int numOfComponents;	
	private List<Component> componentList;
	
	public UnionFind() {
		componentList = new ArrayList<>();
	}
	
	public boolean isConnected(Pixel primaryPixel, Pixel secondaryPixel) {
		if(findComponent(primaryPixel) == findComponent(secondaryPixel)) return true;
		

		return false;
	}
	
	// Returns Empty component if none is found
	private Component findComponent(Pixel pixel) {
		Component emptyComponent = new Component();
		for (Component component : componentList) {
			for (Pixel searchPixel : component.pixels) {
				if (searchPixel.compareTo(pixel) == 0) return component;
			}
		}
		return emptyComponent;
	}
	
	public void union(Pixel primaryPixel, Pixel secondaryPixel) {
		Component primaryComponent = findComponent(primaryPixel);
		Component secondaryComponent = findComponent(secondaryPixel);
		
		int primaryLength = primaryComponent.pixels.size();
		int secondaryLength = secondaryComponent.pixels.size();
		
		if (primaryLength >= secondaryLength) {
			primaryComponent.pixels.addAll(secondaryComponent.pixels);
			componentList.remove(secondaryComponent);
		}
		else {
			secondaryComponent.pixels.addAll(primaryComponent.pixels);
			componentList.remove(primaryComponent);
		}	
	}
	
	public int numOfComponents() {
		if (componentList != null) return componentList.size();
		return 0;
	}
	
	public Queue<Pixel> getComponent(int index) {
		return (Queue<Pixel>) componentList.get(index);
	}
	
	private class Component {
		Queue<Pixel> pixels = new PriorityQueue<>();
	}
	
	
	
	/**
	public UnionFind(int numberOfVertices) {
		this.numOfComponents = numberOfVertices;
		
		roots = new int[numOfComponents];
		for (int i = 0; i < numOfComponents; i++) roots[i] = i; 
		
		componentSize = new int[numOfComponents];
		for (int i = 0; i<numOfComponents; i++) componentSize[i] = 1;
	}
	
	public UnionFind(int numberOfVertices) {
		this.numOfComponents = numberOfVertices;
		
		roots = new int[numOfComponents];
		for (int i = 0; i < numOfComponents; i++) roots[i] = i; 
		
		componentSize = new int[numOfComponents];
		for (int i = 0; i<numOfComponents; i++) componentSize[i] = 1;
	}
	
	public int numOfComponents() { return numOfComponents; }
	
	//public boolean isConnected(int primaryVertex, int secondaryVertex) { return findRoot(primaryVertex) == findRoot(secondaryVertex); }
	public boolean isConnected(Pixel primaryVertex, Pixel secondaryVertex) { return findRoot(primaryVertex) == findRoot(secondaryVertex); }
	
	public int findRoot(int p) {
		while (p != roots[p]) p = roots[p];
		return p;
	}
	
	public void union(int p, int q) {
		int i = findRoot(p);
		int j = findRoot(q);
		if (i == j) return;	//If the roots are the same
		
		//Make smaller root point to larger one
		if (componentSize[i] < componentSize[j]) {roots[i] = j; componentSize[j] += componentSize[i]; }
		else {roots[j] = i; componentSize[i] += componentSize[j]; }
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
	
	
	
	/**
	for (Component component : componentList) {
		boolean primaryFound = false;
		boolean secondaryFound = false;
		for (Pixel pixel : component.pixels) {
			if (primaryPixel.compareTo(pixel) == 0) primaryFound = true;
			if (secondaryPixel.compareTo(pixel) == 0) secondaryFound = true;
		}
		if (primaryFound && secondaryFound) return true;
	}
	*/
}
