package plugins;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import segmentation.AverageIntensity;

public class CellMaskPlugin implements PlugInFilter {

	ImagePlus image;
	ImageProcessor imgProcessor;
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		
		// Get Threshold or end the plugin
		int threshold = getThreshold();
		if (threshold == -1) return;
		
		// Retrieve the pixels of the current imageProcessor
		int[][] pixels = imgProcessor.getIntArray();
		int imageHeight = imgProcessor.getHeight();
		int imageWidth = imgProcessor.getWidth();
		
		// Use Kruskal's Algorithm to create UnionFind components(numbers above threshold are in cell bodies)
		
		// For each UnionFind component make a new cell mask
			// If a cell mask is too large break it in two
		
		// Make a new ImagePlus for each cell mask
		
		
		image = new ImagePlus("Average Pixel", this.imgProcessor);
		image.show();
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
}
