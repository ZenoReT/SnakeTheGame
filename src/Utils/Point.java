package utils;

public class Point{
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public Point add(Point point) {
		return new Point(point.x + this.x, point.y + this.y);
	}

	public Point sub(Point point) {
		return new Point(this.x - point.x, this.y - point.y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point other = (Point)obj;
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}
}
