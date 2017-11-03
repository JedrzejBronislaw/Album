package jk.album.domain.marks;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

public class RectTest {

	static Rect rect1, rect2;

	@BeforeClass
	public static void preparation(){
		Point p1, p2;
		p1 = new Point(10, 20);
		p2 = new Point(50, 90);
		rect1 = new Rect(p1, p2);
		rect2 = new Rect(p2, p1);
	}

	@Test
	public void coordinateOrder() {

		int[] coord1 = new int[]{rect1.getTop(), rect1.getRight(), rect1.getBottom(), rect1.getLeft()};
		int[] coord2 = new int[]{rect2.getTop(), rect2.getRight(), rect2.getBottom(), rect2.getLeft()};
		assertArrayEquals(coord1, coord2);
	}

	@Test
	public void pointInRectangle() {
		boolean b = rect1.isHere(new Point(15, 60));
		assertTrue(b);
	}

	@Test
	public void pointOnTopEdge() {
		boolean b = rect1.isHere(new Point(10, 60));
		assertTrue(b);
	}

	@Test
	public void pointOnBottomEdge() {
		boolean b = rect1.isHere(new Point(50, 60));
		assertTrue(b);
	}

	@Test
	public void pointOnLeftEdge() {
		boolean b = rect1.isHere(new Point(15, 90));
		assertTrue(b);
	}

	@Test
	public void pointOnRightEdge() {
		boolean b = rect1.isHere(new Point(15, 20));
		assertTrue(b);
	}

	@Test
	public void pointOutsideRectangle_onlyXIn() {
		boolean b = rect1.isHere(new Point(15, 150));
		assertFalse(b);
	}

	@Test
	public void pointOutsideRectangle_onlyYIn() {
		boolean b = rect1.isHere(new Point(5, 60));
		assertFalse(b);
	}

	@Test
	public void pointOutsideRectangle_XandYOut() {
		boolean b = rect1.isHere(new Point(200, 3));
		assertFalse(b);
	}
}
