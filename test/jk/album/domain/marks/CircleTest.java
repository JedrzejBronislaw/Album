package jk.album.domain.marks;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

public class CircleTest {

	private static Circle circle;

	@BeforeClass
	public static void preparation(){
		circle = new Circle(100, 200, 10);
	}

	@Test
	public void squaredArea() {

		int[] coord1 = new int[]{circle.getTop(), circle.getRight(), circle.getBottom(), circle.getLeft()};
		int[] coord2 = new int[]{190, 110, 210, 90};
		assertArrayEquals(coord1, coord2);
	}

	@Test
	public void pointInCircle() {
		boolean b = circle.isHere(new Point(95, 205));
		assertTrue(b);
	}

	@Test
	public void pointInCentre() {
		boolean b = circle.isHere(new Point(100, 200));
		assertTrue(b);
	}

	@Test
	public void pointOnTopEdge() {
		boolean b = circle.isHere(new Point(100, 190));
		assertTrue(b);
	}

	@Test
	public void pointOnBottomEdge() {
		boolean b = circle.isHere(new Point(100, 210));
		assertTrue(b);
	}

	@Test
	public void pointOnLeftEdge() {
		boolean b = circle.isHere(new Point(90, 200));
		assertTrue(b);
	}

	@Test
	public void pointOnRightEdge() {
		boolean b = circle.isHere(new Point(110, 200));
		assertTrue(b);
	}

	@Test
	public void pointOutsideCircle_onlyXIn() {
		boolean b = circle.isHere(new Point(105, 220));
		assertFalse(b);
	}

	@Test
	public void pointOutsideCircle_onlyYIn() {
		boolean b = circle.isHere(new Point(60, 203));
		assertFalse(b);
	}

	@Test
	public void pointOutsideCircle_XandYOut() {
		boolean b = circle.isHere(new Point(20, 3));
		assertFalse(b);
	}

	@Test
	public void pointInTopLeftCorner() {
		boolean b = circle.isHere(new Point(91, 191));
		assertFalse(b);
	}

	@Test
	public void pointInTopRightCorner() {
		boolean b = circle.isHere(new Point(109, 191));
		assertFalse(b);
	}

	@Test
	public void pointInBottomLeftCorner() {
		boolean b = circle.isHere(new Point(91, 209));
		assertFalse(b);
	}

	@Test
	public void pointInBottomRightCorner() {
		boolean b = circle.isHere(new Point(109, 209));
		assertFalse(b);
	}
}
