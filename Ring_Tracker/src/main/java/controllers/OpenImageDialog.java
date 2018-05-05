package controllers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import ij.ImagePlus;

public class OpenImageDialog {

	public static boolean getImageFromPath(ImagePlus image) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			image = new ImagePlus(selectedFile.getAbsolutePath());
			return true;
		}
		
		return false;
	}
	
	public static String getImagePath() {
		String path = "";
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			return path;
		}
		
		return path;
	}
	
	/**
	public static void openDirectoryPath() {
	
	}
	*/
}
