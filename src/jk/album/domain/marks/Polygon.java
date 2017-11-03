package jk.album.domain.marks;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import jk.album.domain.Being;

public class Polygon extends Mark {

	private List<Point> points = new ArrayList<Point>();
	private int
			left=Integer.MAX_VALUE,
			right=Integer.MIN_VALUE,
			top=Integer.MAX_VALUE,
			bottom=Integer.MIN_VALUE;

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
		findBounds();
	}

	private void findBounds() {
		left=Integer.MAX_VALUE;
		right=Integer.MIN_VALUE;
		top=Integer.MAX_VALUE;
		bottom=Integer.MIN_VALUE;

		for(Point p : points){
			if(p.x < left)   left = p.x;
			if(p.x > right)  right = p.x;
			if(p.y < top)    top = p.y;
			if(p.y > bottom) bottom = p.y;
		}

	}
	private void findBounds(Point p) {
		if(p.x < left)   left = p.x;
		if(p.x > right)  right = p.x;
		if(p.y < top)    top = p.y;
		if(p.y > bottom) bottom = p.y;
	}

	public void addPoint(Point point){
		if (points == null) points = new ArrayList<>();
		points.add(point);
		findBounds(point);
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
	}

	public int getBottom() {
		return bottom;
	}

	public Polygon() {
	}

	public Polygon(List<Point> points) {
		this.points = points;
		findBounds();
	}

	public Polygon(Being being) {
		this.being = being;
	}

	public Polygon(Being being, List<Point> points) {
		this.being  = being;
		this.points = points;
		findBounds();
	}




	@Override
	public boolean isHere(Point point) {
		return (point.x<=right && point.x>=left && point.y>=top && point.y<=bottom);
	}

}
