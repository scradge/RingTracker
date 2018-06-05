package plugins;

import java.util.ArrayList;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;

import ij.process.ImageProcessor;
import utils.Pixel;

public class RunPlugin implements PlugInFilter{
	ImageProcessor processor;
	ImagePlus image;
	
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		processor = imgProcessor;
		
		// Sizes of the current imageProcessor
		int imageHeight = processor.getHeight();
		System.out.println(imageHeight);
		int imageWidth = processor.getWidth();	
		System.out.println(imageWidth);
		
	
		
		// Create and show new image
		image = new ImagePlus("Cell Mask", imgProcessor);
		image.show();
		
	}

	@Override
	public int setup(String arg, ImagePlus image) {
		this.image = image;
		return DOES_16 + NO_CHANGES;
	}
}
