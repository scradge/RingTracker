package controllers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class OpenImageDialog {

	public static String getImagePath() {
		String imagePath = "";
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			imagePath = selectedFile.getAbsolutePath();
		}
		
		return imagePath;
	}
	
	/**
	public static void openDirectoryPath() {
	
	}
	*/
}
