package plugins;

import controllers.OpenImageDialog;
import ij.ImagePlus;
import ij.ImageStack;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;


public class OpenImagePlugin implements PlugIn {

	int threshold = 110;
	
	@Override
	public void run(String arg0) {
		
		// Open new ImagePlus
		ImagePlus image = new ImagePlus(OpenImageDialog.getImagePath());

		// Get ImagePlus Dimensions	
		int nSlices = image.getNSlices();
		int nFrames = image.getNFrames();
		
		//Create New ImageStack
		ImageStack mask = OpenImagePlugin.thresholdStack(threshold, image, nFrames, nSlices);
	
		// Make mask into new ImagePlus
		ImagePlus modifiedImage = new ImagePlus("Modified Image", mask);	
		modifiedImage.show();
	}
	

	private static ImageStack thresholdStack(int threshold, ImagePlus image, int nFrames, int nSlices) {
		ImageStack imgStack = new ImageStack();
		
		// Create new slice to add to mask for each nFrames
		for (int i = 0; i<=nFrames; i++) {
			
			// For each nSlice(z) of the nFrame(t)
			for (int j = 0; j<=nSlices; j++) {
				
				// Get Green(2) channel
				image.setPosition(2, j, i);
				
				// Get Pixel Array for Image
				ImageProcessor processor = image.getProcessor();
				float[][] pixelArray = processor.getFloatArray();
				
				// Process Pixel Array
				for (float[] row : pixelArray) {
					for (float pixel : row) {
						if (pixel < threshold) pixel = 0;
					}
				}
				
				// Create new slice and add to mask ImageStack
				imgStack.addSlice(processor);
			}
		}	
		return imgStack;
	}
	


/**
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	
	
	/**
	// Create new slice to add to mask for each nFrames
	for (int i = 0; i<=nFrames; i++) {

		// For each nSlice(z) of the nFrame(t)
		for (int j = 0; j<=nSlices; j++) {
			
			// Get Green(2) channel
			image.setPosition(2, j, i);
			
			// Get Pixel Array for Image
			ImageProcessor processor = image.getProcessor();
			float[][] pixelArray = processor.getFloatArray();
			
			// Process Pixel Array
			for (float[] row : pixelArray) {
				for (float pixel : row) {
					if (pixel < threshold) pixel = 0;
				}
			}
			
			// Create new slice and add to mask ImageStack
			mask.addSlice(processor);
		}
		
	}
	*/
}
