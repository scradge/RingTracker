package plugins;

import controllers.OpenImageDialog;
import ij.ImagePlus;
import ij.ImageStack;
import ij.plugin.PlugIn;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;


public class OpenImagePlugin implements PlugIn {

	float threshold = 110;
	
	@Override
	public void run(String arg0) {
		
		// Open new ImagePlus
		ImagePlus image = new ImagePlus(OpenImageDialog.getImagePath());
		
		//Create New ImageStack
		ImageStack mask = OpenImagePlugin.thresholdStack(threshold, image, image.getNFrames(), image.getNSlices());
	
		// Make mask into new ImagePlus
		ImagePlus modifiedImage = new ImagePlus("Modified Image", mask);	
		modifiedImage.show();
	}
	

	private static ImageStack thresholdStack(float threshold, ImagePlus image, int nFrames, int nSlices) {
		ImageStack imgStack = new ImageStack(image.getWidth(), image.getHeight());
		
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
				for (int x = 0; x< pixelArray.length; x++) {
					for (int y = 0; y < pixelArray[y].length; y++) {			
						if (processor.getf(x, y) < threshold) pixelArray[x][y] = 0;
					}
				}
						
				// Create new slice and add to mask ImageStack
				ImageProcessor slice = new FloatProcessor(pixelArray);
				imgStack.addSlice(slice);;
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
}
