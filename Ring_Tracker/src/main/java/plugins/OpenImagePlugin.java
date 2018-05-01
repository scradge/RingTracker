package plugins;

import controllers.OpenImageDialog;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class OpenImagePlugin implements PlugIn {

	@Override
	public void run(String arg0) {
		String path = OpenImageDialog.getImagePath();
		
		ImagePlus image = new ImagePlus(path);
		image.show();
	}

	/**
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

}
