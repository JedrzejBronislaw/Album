package jk.album.domain.marks;

import java.awt.Point;

import jk.album.domain.Being;

public class Circle extends Mark {

	private int x, y;
	private int r;


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}

	public int getLeft() {
		return x-r;
	}

	public int getRight() {
		return x+r;
	}

	public int getTop() {
		return y-r;
	}

	public int getBottom() {
		return y+r;
	}

	public Circle() {
	}

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.r = radius;
	}

	public Circle(Point centre, int radius) {
		this.x = centre.x;
		this.y = centre.y;
		this.r = radius;
	}

	public Circle(Being being) {
		this.being = being;
	}

	public Circle(Being being, int x, int y, int radius) {
		this.being = being;
		this.x = x;
		this.y = y;
		this.r = radius;
	}

	public Circle(Being being, Point point, int radius) {
		this.being = being;
		this.x = point.x;
		this.y = point.y;
		this.r = radius;
	}




	@Override
	public boolean isHere(Point point) {
		return ((point.x-x)*(point.x-x) +
				(point.y-y)*(point.y-y) <= (r*r));
	}

}
