package plugins;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import segmentation.AverageIntensity;

public class AveragePixelPlugin implements PlugInFilter {

	
	
	ImagePlus image;
	ImageProcessor imgProcessor;
	
	@Override
	public void run(ImageProcessor imgProcessor) {
		
		
		GenericDialog gd = new GenericDialog("Scale");
		gd.addNumericField("Reduce Scale", 3500, 0);
		gd.showDialog();
		if (gd.wasCanceled()) return;
		
		int threshold = (short) gd.getNextNumber();
		
		//Duplicate ImageProcessor of current open image
		this.imgProcessor = imgProcessor.duplicate();
		
		//Find edges
		int imageHeight = this.imgProcessor.getHeight();
		int imageWidth = this.imgProcessor.getWidth();
		
		short[] pixels = (short[])this.imgProcessor.getPixels();
		short[] temp = new short[pixels.length];
		
		for (int i = 0; i < pixels.length; i++) {
			short strength = AverageIntensity.AIShort(i, imageWidth, imageHeight, pixels, threshold);

			//strength = (short) (strength/threshold);
			temp[i] = strength;
		}
		
		//Make new ImagePlus from modified ImageProcessor
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = temp[i];
		}
		
		image = new ImagePlus("Average Pixel", this.imgProcessor);
		image.show();
	}

	@Override
	public int setup(String arg, ImagePlus image) {
		this.image = image;
		return DOES_16 + NO_CHANGES;
	}
}






	





