package utils;

import java.util.Comparator;

public class Pixel implements Comparable<Pixel>{

	private final int X;
	private final int Y;
	private final int VALUE;
	
	public Pixel(int x, int y, int value) {
		this.X = x;
		this.Y = y;
		this.VALUE = value;
	}
	
	public int getX() { return X; }
	public int getY() { return Y; }
	public int getValue() { return VALUE; }
	
	private static final Comparator<Pixel> COMPARATOR = Comparator.comparingInt((Pixel pixel) -> pixel.X).thenComparingInt((Pixel pixel) -> pixel.Y).thenComparingInt((Pixel pixel) -> pixel.VALUE);
	
	@Override
	public int compareTo(Pixel pixel) {
		return COMPARATOR.compare(this, pixel);
	}
}
