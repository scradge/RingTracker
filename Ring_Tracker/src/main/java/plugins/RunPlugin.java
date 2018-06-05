package plugins;

import java.util.ArrayList;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;

import ij.process.ImageProcessor;
import segmentation.AverageIntensity;
import utils.Pixel;

public class RunPlugin implements PlugInFilter{
	ImageProcessor processor;
	ImagePlus image;
	Pixel[] pixelArray;
	
	
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		processor = imgProcessor.duplicate();
		
		// Sizes of the current imageProcessor
		int imageHeight = processor.getHeight();
		System.out.println("Image Height: " + imageHeight);
		int imageWidth = processor.getWidth();	
		System.out.println("Image Width: " + imageWidth);
		
		// Create and fill pixelArray
		pixelArray = new Pixel[imageHeight * imageWidth];
		System.out.println("pixelArray size: " + pixelArray.length);
		
		// Set values below threshold to 0
		for (int i=0; i<processor.getPixelCount(); i++) {
			if (processor.get(i) < 160) processor.set(i, 0);
		}
		
		// Create and show new image
		image = new ImagePlus("Cell Mask", processor);
		image.show();
		
	}

	@Override
	public int setup(String arg, ImagePlus image) {
		this.image = image;
		return DOES_16 + NO_CHANGES;
	}
}
