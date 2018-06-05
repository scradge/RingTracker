package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Cell {
	List<Component> componentList;
	
	public Cell(Queue queue) {
		componentList = new ArrayList<>();
		
		// Reverse Queue
		Edge[] edgeArray = (Edge[]) queue.toArray();
		for (int i = edgeArray.length-1; i>=0; i--) {
			Edge edge = edgeArray[i];	// Get Strongest Edge	
			addEdge(edge);
		}	
	}
	
	private void addEdge(Edge edge) {
		Pixel primaryPixel = edge.primaryVertex();
		Pixel secondaryPixel = edge.secondaryVertex();
		
		Component primaryComponent = getComponent(primaryPixel);
		Component secondaryComponent = getComponent(secondaryPixel);
		
		if (primaryComponent == secondaryComponent) return;										//Same component
		if (primaryComponent != null && secondaryComponent != null) {   						//Different components
			if(primaryComponent.pixelList.size() > secondaryComponent.pixelList.size()) {
				
			}
			return;
		}
		
		if (primaryComponent != null) {															//New addition to primary component					
			primaryComponent.edgeList.add(edge);
			primaryComponent.pixelList.add(secondaryPixel);
			return;
		}
						
		secondaryComponent.edgeList.add(edge);													//New addition to secondary component
		secondaryComponent.pixelList.add(primaryPixel);
		
	}
	
	private Component getComponent(Pixel pixel) {
		for (Component component : componentList) {
			if (component.pixelList.contains(pixel)) return component;
		}
		return null;
	}
	
	private class Component {
		List<Edge> edgeList = new ArrayList<>();
		List<Pixel> pixelList = new ArrayList<>();
	}
}