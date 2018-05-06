package segmentation;

/**
 * This class contains only static methods designed to calculate the Difference in Strength 
 * 
 * @author Ryan Dundon
 */

public class DifferenceInStrength {

	/**
	 * Calculates Difference in Strength for short arrays
	 * @param position    position of the pixel within the pixel array
	 * @param imageWidth  width of the image containing the pixel
	 * @param imageHeight Height of the image containing the pixel
	 * @param pixels      one dimensional array containing the pixel of the image
	 * @return short      returns a short value of the difference in strength
	 */
	public static short DISShort(int position, int imageWidth, int imageHeight, short[] pixels) {
		short dIS = 0; 
		int[] xyPositions = calculateXY(position, imageWidth);
		int xPosition = xyPositions[0];
		int yPosition = xyPositions[1];
		
		// If pixel is in main body
		if (!isEdge(xPosition, yPosition, imageWidth, imageHeight)) dIS = calculateShort(position, imageWidth, pixels);
		
		return dIS;
	}
	
	/**
	 * Checks if the position of the pixel is an the edge of the image
	 * 
	 * @param xPosition   position of y coordinate of the pixel being tested
	 * @param yPosition   position of y coordinate of the pixel being tested
	 * @param imageWidth  width of the image being tested
	 * @param imageHeight height of the image being tested
	 * @return boolean    Return true if x position is equal to 0 or the image width or
	 *                    if y position is equal to 0 or the image height
	*/
	private static boolean isEdge(int xPosition, int yPosition, int imageWidth, int imageHeight) {
		if (xPosition == 0) return true;					//Left
		else if (xPosition == (imageWidth - 1)) return true;		//Right
		else if (yPosition == 0) return true;				//Top 
		else if (yPosition == (imageHeight - 1)) return true;		//Bottom
		else return false;
	}

	/**
	 * 
	 * @param position
	 * @param imageWidth
	 * @param pixels
	 * @return short
	 */
	private static short calculateShort(int position, int imageWidth, short[] pixels) {	
		// Get position for surrounding pixels
		int z1 = position - (imageWidth + 1);
		int z2 = position - imageWidth;
		int z3 = position - (imageWidth - 1);
		int z4 = position - 1;
		int z5 = position + 1;
		int z6 = position + (imageWidth -1);
		int z7 = position + imageWidth;
		int z8 = position + (imageWidth + 1);
		
		// Compute Difference in Strength
		return (short)(Math.abs(pixels[z1] - pixels[z3]) + Math.abs(pixels[z1] - pixels[z5]) + Math.abs(pixels[z1] - pixels[z6]) + Math.abs(pixels[z1] - pixels[z7]) + Math.abs(pixels[z1] - pixels[z8])
		+ Math.abs(pixels[z2] - pixels[z4]) + Math.abs(pixels[z2] - pixels[z5]) + Math.abs(pixels[z2] - pixels[z6]) + Math.abs(pixels[z2] - pixels[z7]) + Math.abs(pixels[z2] - pixels[z8])
		+ Math.abs(pixels[z3] - pixels[z4]) + Math.abs(pixels[z3] - pixels[z6]) + Math.abs(pixels[z3] - pixels[z7]) + Math.abs(pixels[z3] - pixels[z8])
		+ Math.abs(pixels[z4] - pixels[z5]) + Math.abs(pixels[z4] - pixels[z7]) + Math.abs(pixels[z4] - pixels[z8])
		+ Math.abs(pixels[z5] - pixels[z6]) + Math.abs(pixels[z5] - pixels[z7])
		+ Math.abs(pixels[z6] - pixels[z8]));
	}

	/**
	 * Calculates the X and Y coordinates of a pixel position for an Image that has been
	 * flattened into a one-dimensional array.
	 * 
	 * @param position   position in the array created by flattening an image
	 * @param imageWidth width of the image that position belongs to
	 * @return int[]     returns a two element array with x at position 0 and y and position 1
	 */
	private static int[] calculateXY(int position, int imageWidth) {
		int[] xy = new int[2];
		xy[1] = (position) / imageWidth; //y;
		xy[0] = position - (xy[1] * imageWidth); //x;
		return xy;		
	}
}
