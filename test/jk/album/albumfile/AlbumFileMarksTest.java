package jk.album.albumfile;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Being;
import jk.album.domain.Being.Type;
import jk.album.domain.Photo;
import jk.album.domain.marks.Circle;
import jk.album.domain.marks.Mark;
import jk.album.domain.marks.Polygon;
import jk.album.domain.marks.Rect;
import jk.album.domain.marks.Shapeless;
import jk.album.domain.marks.Spot;

public class AlbumFileMarksTest {

	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";


	@BeforeClass
	public static void preparation(){
		Being b1, b2, b3, b4;
		ArrayList<Mark> marks = new ArrayList<>();
		ArrayList<Photo> photos = new ArrayList<>();

		b1 = new Being("JK", "John Kowalski");
		b2 = new Being("Being2", "description of being");
		b3 = new Being("Being3", "description of being");
		b4 = new Being("Being4", "description of being");

		b1.setType(Type.Human);

		Photo photo = new Photo();

		marks.add(new Shapeless(b1));
		marks.add(new Shapeless(b2));
		marks.add(new Shapeless(b3));

		marks.add(new Spot(b1, 120, 50));
		marks.add(new Spot(b2, 125, 55));

		marks.add(new Rect(b2, 10, 20, 30, 40));

		marks.add(new Circle(b2, 220, 320, 5));
		marks.add(new Circle(b3, 630, 390, 15));
		marks.add(new Circle(b4, 210, 870, 10));
		marks.add(new Circle(b1, 730, 660, 25));
		marks.add(new Circle(b2, 920, 330, 30));

		ArrayList<Point> points1 = new ArrayList<>();
		points1.add(new Point(234, 567));
		points1.add(new Point(345, 765));
		points1.add(new Point(678, 777));

		ArrayList<Point> points2 = new ArrayList<>();
		points2.add(new Point(122, 222));
		points2.add(new Point(133, 233));
		points2.add(new Point(144, 244));
		points2.add(new Point(155, 255));
		points2.add(new Point(166, 266));
		points2.add(new Point(177, 277));

		marks.add(new Polygon(b2, points1));
		marks.add(new Polygon(b3, points2));


		photo.setMarks(marks);

		photos.add(photo);

		albumFile.setPhotos(photos);

		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);
	}

	@AfterClass
	public static void clear(){
		new File(fileName).delete();
	}

	@Test
	public void countOfMarks() {
		int size = albumFile.getPhotos().get(0).getMarks().size();
		assertEquals(13, size);
	}

	@Test
	public void countOfShapelesses() {
		List<Mark> marks = albumFile.getPhotos().get(0).getMarks();
		int size = 0;

		for(Mark m : marks)
			if (m instanceof Shapeless) size++;

		assertEquals(3, size);
	}

	@Test
	public void countOfSpots() {
		List<Mark> marks = albumFile.getPhotos().get(0).getMarks();
		int size = 0;

		for(Mark m : marks)
			if (m instanceof Spot) size++;

		assertEquals(2, size);
	}

	@Test
	public void countOfRects() {
		List<Mark> marks = albumFile.getPhotos().get(0).getMarks();
		int size = 0;

		for(Mark m : marks)
			if (m instanceof Rect) size++;

		assertEquals(1, size);
	}

	@Test
	public void countOfCircles() {
		List<Mark> marks = albumFile.getPhotos().get(0).getMarks();
		int size = 0;

		for(Mark m : marks)
			if (m instanceof Circle) size++;

		assertEquals(5, size);
	}

	@Test
	public void countOfPolygons() {
		List<Mark> marks = albumFile.getPhotos().get(0).getMarks();
		int size = 0;

		for(Mark m : marks)
			if (m instanceof Polygon) size++;

		assertEquals(2, size);
	}

	@Test
	public void firstMarksBeingName() {
		String name = albumFile.getPhotos().get(0).getMarks().get(0).getBeing().getName();
		assertEquals("JK", name);
	}

	@Test
	public void firstMarksBeingDescription() {
		String description = albumFile.getPhotos().get(0).getMarks().get(0).getBeing().getDescription();
		assertEquals("John Kowalski", description);
	}

	@Test
	public void countOfPoligonsPoints1() {
		Polygon poly = (Polygon) albumFile.getPhotos().get(0).getMarks().get(11);
		int size = poly.getPoints().size();
		assertEquals(3, size);
	}

	@Test
	public void countOfPoligonsPoints2() {
		Polygon poly = (Polygon) albumFile.getPhotos().get(0).getMarks().get(12);
		int size = poly.getPoints().size();
		assertEquals(6, size);
	}

	@Test
	public void cordinateOfSpot() {
		Spot spot = (Spot) albumFile.getPhotos().get(0).getMarks().get(3);
		int x = spot.getX();
		int y = spot.getY();
		assertArrayEquals(new int[]{120, 50}, new int[]{x,y});
	}

	@Test
	public void valuesOfRectangle() {
		Rect rect = (Rect) albumFile.getPhotos().get(0).getMarks().get(5);
		int b = rect.getBottom();
		int t = rect.getTop();
		int l = rect.getLeft();
		int r = rect.getRight();
		assertArrayEquals(new int[]{10, 20, 30, 40}, new int[]{t,r,b,l});
	}

	@Test
	public void valuesOfCircle() {
		Circle circle = (Circle) albumFile.getPhotos().get(0).getMarks().get(6);
		int x = circle.getX();
		int y = circle.getY();
		int r = circle.getR();
		assertArrayEquals(new int[]{220, 320, 5}, new int[]{x,y,r});
	}

	@Test
	public void valuesOfFirstPointOfPolygon() {
		Polygon poly = (Polygon) albumFile.getPhotos().get(0).getMarks().get(11);
		Point point = poly.getPoints().get(0);
		int x = point.x;
		int y = point.y;
		assertArrayEquals(new int[]{234, 567}, new int[]{x,y});
	}

}