package jk.album.domain.marks;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class PolygonTest {

	static Polygon poly, poly2;

	@BeforeClass
	public static void preparation(){
		Point p1, p2, p3, p4;
		List<Point> points = new ArrayList<Point>();
		List<Point> points2 = new ArrayList<Point>();
		p1 = new Point(10, 10);
		p2 = new Point(7, 100);
		p3 = new Point(50, 150);
		p4 = new Point(20, 110);
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);

		points2.add(p1);
		points2.add(p2);
		points2.add(p3);
		points2.add(p4);

		poly = new Polygon(points);
		poly2 = new Polygon(points2);
	}

	@Test
	public void coordinateOrder() {

		int[] bounds1 = new int[]{10, 50, 150, 7};
		int[] bounds2 = new int[]{poly.getTop(), poly.getRight(), poly.getBottom(), poly.getLeft()};
		assertArrayEquals(bounds1, bounds2);
	}

	@Test
	public void pointInRectangle() {
		boolean b = poly.isHere(new Point(15, 60));
		assertTrue(b);
	}

	@Test
	public void pointOutsideRectangle_onlyXIn() {
		boolean b = poly.isHere(new Point(15, 200));
		assertFalse(b);
	}

	@Test
	public void pointOutsideRectangle_onlyYIn() {
		boolean b = poly.isHere(new Point(5, 60));
		assertFalse(b);
	}

	@Test
	public void pointOutsideRectangle_XandYOut() {
		boolean b = poly.isHere(new Point(200, 3));
		assertFalse(b);
	}

	@Test
	public void changeBoundsAfterAddingPoint() {

		poly2.addPoint(new Point(210, 1));
//		int[] bounds1 = new int[]{10, 110, 150, 7};
		int[] bounds1 = new int[]{1, 210, 150, 7};
		int[] bounds2 = new int[]{poly2.getTop(), poly2.getRight(), poly2.getBottom(), poly2.getLeft()};
		assertArrayEquals(bounds1, bounds2);
	}
}
