package plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import utils.Edge;
import utils.EdgeWeightedGraph;
import utils.KruskalMST;
import utils.Pixel;
import utils.UnionFind;

public class CellMaskPlugin implements PlugInFilter {

	ImagePlus image;
	List<Pixel> pixelList;
	Queue<Edge> edges;
	ImageProcessor processor;
	Edge[] edgeArray;
	//ImageProcessor imgProcessor;
	//KruskalMST  minSpanTree;
	//EdgeWeightedGraph graph;
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		processor = imgProcessor;
		
		// Get Threshold or end the plugin
		int threshold = getThreshold();
		if (threshold == -1) return;
		
		// Sizes of the current imageProcessor
		int imageHeight = processor.getHeight();
		int imageWidth = processor.getWidth();		
		
		// Fill the pixelArray
		pixelList = new ArrayList<>();
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				int currentValue = processor.getPixel(x, y);
				Pixel currentPixel = new Pixel(x, y, currentValue);
				pixelList.add(currentPixel);
			}
		}
		System.out.println(pixelList.size());
		
		// Fill the priority queue with edges
		edges = new PriorityQueue<>();
		for (Pixel pixel : pixelList) {
			// check surrounding pixel[8-Way]
			for (int i = 1; i <= 8; i++) {	
				Pixel nextPixel = getNextPixel(pixel, i);
				if (nextPixel != null) {
					int edgeWeight = getWeight(pixel.getValue(), nextPixel.getValue());
					Edge edge = new Edge(pixel, nextPixel, edgeWeight);
					edges.add(edge);
				}
			}
		}
		
		//Create new cell masks
		edgeArray = (Edge[]) edges.toArray();
		for (int i = edgeArray.length-1; i>=0; i--) {
			Edge edge = edgeArray[i];
			
		}
		
			
		// Create and show new image
		image = new ImagePlus("Cell Mask", imgProcessor);
		image.show();
	}
		/**
		// Get Threshold or end the plugin
		int threshold = getThreshold();
		if (threshold == -1) return;
		
		// Set Mask color
		imgProcessor.setColor(500);
		
		// Sizes of the current imageProcessor
		int imageHeight = imgProcessor.getHeight();
		int imageWidth = imgProcessor.getWidth();		
		int numOfVertices = imgProcessor.getPixelCount();
		
		// Create an edge weighted graph of the current processor and fill with edges
		// Don't add any edges that are under the threshold
		graph = new EdgeWeightedGraph(numOfVertices);	
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				int currentPixel = imgProcessor.getPixel(x, y);
				
				// check surrounding pixel[8-Way]
				for (int i = 1; i <= 8; i++) {
					int[] nextPixel = getNextPixel(x, y, i);
					int connectedPixel = imgProcessor.getPixel(nextPixel[0], nextPixel[1]);
					int edgeWeight = getWeight(currentPixel, connectedPixel);
					
					if (edgeWeight >= threshold) {
						Pixel primaryPixel = new Pixel(x, y, currentPixel);
						Pixel secondaryPixel = new Pixel(nextPixel[0], nextPixel[1], connectedPixel);
						Edge edge = new Edge(primaryPixel, secondaryPixel, edgeWeight);
						graph.addEdge(edge);
					}
				}
			}
		}
			
		// Use Kruskal's Algorithm to create UnionFind components(numbers above threshold are in cell bodies)
		minSpanTree = new KruskalMST(graph);
		
		
		// For each UnionFind component make a new cell mask
		int numOfCells = minSpanTree.getUnitCount();
		System.out.println("number of cells: " + numOfCells);
			// If a cell mask is too large break it in two
		
		// Make a new ImagePlus for each cell mask
		for (int i = 0; i < numOfCells; i++) {
			// Create new ImageProcessor 
			
			//ImageProcessor mask = this.imgProcessor.duplicate();
			
			// Draw each pixel of the cell
			//for (int[] pixel: minSpanTree.getUnit(i)) {
			//	mask.drawPixel(pixel[0], pixel[1]);
			//}
			for (Pixel pixel : minSpanTree.getUnit(i)) {
				imgProcessor.drawPixel(pixel.getX(), pixel.getY());
			}
			
			// Create and show new image
			//image = new ImagePlus("Cell " + i++ + " Mask", imgProcessor);
			//image.show();
		}
		
		// Create and show new image
				//	image = new ImagePlus("Cell Mask", imgProcessor);
				//	image.show();
		
		*/
	

	@Override
	public int setup(String arg, ImagePlus image) {
		this.image = image;
		return DOES_16 + NO_CHANGES;
	}
	
	/**
	 * Creates a GenericDialog to ask for the masking threshold
	 * @return int returns the int the user inputs or -1 if the dialog was cancelled
	 */
	private static int getThreshold() {
		GenericDialog gd = new GenericDialog("Ring Tracker");
		gd.addNumericField("Mask Threshold", 160, 0);
		gd.showDialog();
		if (gd.wasCanceled()) return -1;	
		return (int) gd.getNextNumber();
	}
	
	private static int getWeight(int primaryVertex, int secondaryVertex) {
		return (primaryVertex + secondaryVertex)/2;
	}
	
	private Pixel getNextPixel(Pixel pixel, int position) {
		int X, Y;
		Pixel nextPixel = null;
		switch(position) {
			case 1: 	X = pixel.getX()-1;
						Y = pixel.getY()-1;
						break;		
			case 2:		X = pixel.getX();
						Y = pixel.getY()-1;
						break;
			case 3:		X = pixel.getX()+1;
						Y = pixel.getY()-1;
						break;
			case 4:		X = pixel.getX()-1;
						Y = pixel.getY();
						break;
			case 5:		X = pixel.getX()+1;
						Y = pixel.getY();
						break;
			case 6:		X = pixel.getX()-1;
						Y = pixel.getY()+1;
						break;
			case 7:		X = pixel.getX();
						Y = pixel.getY()+1;
						break;
			case 8:		X = pixel.getX()+1;
						Y = pixel.getY()+1;
						break;
			default: 	X = pixel.getX();
						Y = pixel.getY();
						break;
		}
		if (X<processor.getWidth() | X>=0) {
			if (Y<processor.getHeight() | Y>=0) {
				int index = (processor.getWidth() * Y) + X;
				nextPixel = this.pixelList.get(index);
			}
		}
		return nextPixel;
	}
}
