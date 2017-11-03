package jk.album.domain.marks;

import java.awt.Point;

import jk.album.domain.Being;

public class Spot extends Mark {

	private int x, y;


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

	public Spot() {
	}

	public Spot(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public Spot(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public Spot(Being being) {
		this.being = being;
	}

	public Spot(Being being, int x, int y) {
		this.being = being;
		this.x=x;
		this.y=y;
	}

	public Spot(Being being, Point point) {
		this.being = being;
		this.x = point.x;
		this.y = point.y;
	}




	@Override
	public boolean isHere(Point point) {
		return (point.x==x && point.y==y);
	}

}
