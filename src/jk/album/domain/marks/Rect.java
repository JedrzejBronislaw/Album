package jk.album.domain.marks;

import java.awt.Point;

import jk.album.domain.Being;

public class Rect extends Mark {

	private int left, right;
	private int top, bottom;

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public Rect() {
	}

	public Rect(int top, int right, int bottom, int left) {
		this.top=top;
		this.bottom=bottom;
		this.left=left;
		this.right=right;
	}

	public Rect(Point point1, Point point2) {
		this.top    = Math.min(point1.y, point2.y);
		this.bottom = Math.max(point1.y, point2.y);
		this.left   = Math.min(point1.x, point2.x);
		this.right  = Math.max(point1.x, point2.x);
	}

	public Rect(Being being) {
		this.being = being;
	}

	public Rect(Being being, int top, int right, int bottom, int left) {
		this.being = being;
		this.top    = top;
		this.bottom = bottom;
		this.left   = left;
		this.right  = right;
	}

	public Rect(Being being, Point point1, Point point2) {
		this.being = being;
		this.top    = Math.min(point1.y, point2.y);
		this.bottom = Math.max(point1.y, point2.y);
		this.left   = Math.min(point1.x, point2.x);
		this.right  = Math.max(point1.x, point2.x);
	}




	@Override
	public boolean isHere(Point point) {
		return (point.x<=right && point.x>=left && point.y>=top && point.y<=bottom);
	}

}
