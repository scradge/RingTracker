package segmentation;

public class DifferenceInStrength {

	public static short DISShort(int position, int imageWidth, int imageHeight, short[] pixels) {
		short dIS = 0; 
		int[] xyPositions = calculateXY(position, imageWidth);
		int xPosition = xyPositions[0];
		int yPosition = xyPositions[1];
		
		// If pixel is in main body
		//System.out.print("isEdge:" + isEdge(xPosition, yPosition, imageWidth, imageHeight) + "  ");
		if (!isEdge(xPosition, yPosition, imageWidth, imageHeight)) dIS = calculateShort(position, imageWidth, pixels);
		
		return dIS;
	}
	
	/**
	* Return true if x position is equal to 0 or the image width
	* Return true if y position is equal to 0 or the image height
	* Else return false
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
	 * @param xPosition
	 * @param yPosition
	 * @param imageWidth
	 * @param imageHeight
	 * @return 
	 */
	private static boolean isCorner(int xPosition, int yPosition, int imageWidth, int imageHeight) {
		if ((xPosition == 0) && (yPosition == 0)) return true;								//Top-Left
		else if ((xPosition == 0) && (yPosition == imageHeight)) return true;				//Bottom-Left
		else if ((xPosition == imageWidth) && (yPosition == 0)) return true;				//Top-Right
		else if ((xPosition == imageWidth) && (yPosition == imageHeight)) return true;		//Bottom-Right
		else return false;
	}
	
	private static short calculateShort(int position, int imageWidth, short[] pixels) {
		/**
		 * z1	z2	z3
		 * z4	X	z5
		 * z6	z7	z8
		 */	
		
		/**
		 * z1 = current position - (width + 1)
		 * z2 = current position - width
		 * z3 = current position - (with - 1)
		 * z4 = current position - 1
		 * z5 = current position + 1
		 * z6 = current position + (width - 1)
		 * z7 = current position + width
		 * z8 = current position + (width + 1)
		 */
		
		//short edgeStrength = 0;
		
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
		
		//return edgeStrength;
	}

	private static int[] calculateXY(int position, int imageWidth) {
		int[] xy = new int[2];
		
		//Y
		int y = (position) / imageWidth;
		//System.out.print("Y:" + y + "  ");
		
		//X
		int x = position - (y * imageWidth);
		//System.out.print("X:" + x + "  ");
		
		xy[0] = x;
		xy[1] = y;
		
		return xy;
		
	}
}
