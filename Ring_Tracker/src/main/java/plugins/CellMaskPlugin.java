package plugins;

import java.util.Queue;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import utils.Edge;
import utils.EdgeWeightedGraph;
import utils.KruskalMST;
import utils.Pixel;

public class CellMaskPlugin implements PlugInFilter {

	ImagePlus image;
	ImageProcessor imgProcessor;
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		
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
		EdgeWeightedGraph graph = new EdgeWeightedGraph(numOfVertices);	
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
		KruskalMST  minSpanTree = new KruskalMST(graph);
		
		
		// For each UnionFind component make a new cell mask
		int numOfCells = minSpanTree.getUnitCount();
			// If a cell mask is too large break it in two
		
		// Make a new ImagePlus for each cell mask
		for (int i = 0; i < numOfCells; i++) {
			// Create new ImageProcessor 
			ImageProcessor mask = this.imgProcessor.duplicate();
			
			// Draw each pixel of the cell
			//for (int[] pixel: minSpanTree.getUnit(i)) {
			//	mask.drawPixel(pixel[0], pixel[1]);
			//}
			for (Pixel pixel : minSpanTree.getUnit(i)) {
				mask.drawPixel(pixel.getX(), pixel.getY());
			}
			
			// Create and show new image
			image = new ImagePlus("Cell " + i++ + " Mask", mask);
			image.show();
		}
	}

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
	
	private static int[] getNextPixel(int currentX, int currentY, int position) {
		int[] nextPixel = new int[2];
		switch(position) {
			case 1: 	nextPixel[0] = currentX--;
						nextPixel[1] = currentY--;
						break;		
			case 2:		nextPixel[0] = currentX;
						nextPixel[1] = currentY--;
						break;
			case 3:		nextPixel[0] = currentX++;
						nextPixel[1] = currentY--;
						break;
			case 4:		nextPixel[0] = currentX--;
						nextPixel[1] = currentY;
						break;
			case 5:		nextPixel[0] = currentX++;
						nextPixel[1] = currentY;
						break;
			case 6:		nextPixel[0] = currentX--;
						nextPixel[1] = currentY++;
						break;
			case 7:		nextPixel[0] = currentX;
						nextPixel[1] = currentY++;
						break;
			case 8:		nextPixel[0] = currentX++;
						nextPixel[1] = currentY++;
						break;
			default: 	nextPixel[0] = currentX;
						nextPixel[1] = currentY;
						break;
		}
		return nextPixel;
	}
}
