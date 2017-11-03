package jk.album.albumfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Place;

public class AlbumFilePlacesTest {

	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";


	@BeforeClass
	public static void preparation(){
		Place p1, p2, p3, p4, p5;

		p1 = new Place("Earth", "our planet", null);
		p2 = new Place("Mars", "fourth planet", null);
		p3 = new Place("Poland", "country in the middle of Europe", p1);
		p4 = new Place("Hungary", "country in Europe", p1);
		p5 = new Place("Warsaw", "capital of Poland", p3);

		p1.setGlobalId("abc");
		p2.setGlobalId("");
		p3.setGlobalId(null);

		ArrayList<Place> places = new ArrayList<Place>();
		places.add(p1);
		places.add(p2);
		places.add(p3);
		places.add(p4);
		places.add(p5);

		albumFile.setPlaces(places);

		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);
	}


	@Test
	public void countOfPlaces() {
		int size = albumFile.getPlaces().size();
		assertEquals(5, size);
	}

//Names

	@Test
	public void nameEarth() {
		String name = albumFile.getPlaces().get(0).getName();
		assertEquals("Earth", name);
	}

	@Test
	public void nameMars() {
		String name = albumFile.getPlaces().get(1).getName();
		assertEquals("Mars", name);
	}

	@Test
	public void namePoland() {
		String name = albumFile.getPlaces().get(2).getName();
		assertEquals("Poland", name);
	}

	@Test
	public void nameHungary() {
		String name = albumFile.getPlaces().get(3).getName();
		assertEquals("Hungary", name);
	}

	@Test
	public void nameWarsaw() {
		String name = albumFile.getPlaces().get(4).getName();
		assertEquals("Warsaw", name);
	}

//Descriptions

	@Test
	public void descriptionEarth() {
		String des = albumFile.getPlaces().get(0).getDescription();
		assertEquals("our planet", des);
	}

	@Test
	public void descriptionMars() {
		String des = albumFile.getPlaces().get(1).getDescription();
		assertEquals("fourth planet", des);
	}

	@Test
	public void descriptionPoland() {
		String des = albumFile.getPlaces().get(2).getDescription();
		assertEquals("country in the middle of Europe", des);
	}

	@Test
	public void descriptionHungary() {
		String des = albumFile.getPlaces().get(3).getDescription();
		assertEquals("country in Europe", des);
	}

	@Test
	public void descriptionWarsaw() {
		String des = albumFile.getPlaces().get(4).getDescription();
		assertEquals("capital of Poland", des);
	}

//

	@Test
	public void placeEarth() {
		Place place = albumFile.getPlaces().get(0).getPlace();
		assertEquals(null, place);
	}

	@Test
	public void placeMars() {
		Place place = albumFile.getPlaces().get(1).getPlace();
		assertEquals(null, place);
	}

	@Test
	public void placePoland() {
		Place placeUp = albumFile.getPlaces().get(0);
		Place place = albumFile.getPlaces().get(2).getPlace();
		assertEquals(placeUp, place);
	}

	@Test
	public void placeHungary() {
		Place placeUp = albumFile.getPlaces().get(0);
		Place place = albumFile.getPlaces().get(3).getPlace();
		assertEquals(placeUp, place);
	}

	@Test
	public void placeWarsaw() {
		Place placeUp = albumFile.getPlaces().get(2);
		Place place = albumFile.getPlaces().get(4).getPlace();
		assertEquals(placeUp, place);
	}

//

	@Test
	public void placeUpForTwoPlacesIsTheSamePolandAndHungary() {
		Place placePl = albumFile.getPlaces().get(2).getPlace();
		Place placeHu = albumFile.getPlaces().get(3).getPlace();
		assertEquals(placePl, placeHu);
	}

	@Test
	public void notNullAndNotEmptyGlobalId() {
		String gId = albumFile.getPlaces().get(0).getGlobalId();
		assertEquals("abc", gId);
	}

	@Test
	public void nullGlobalId() {
		String gId = albumFile.getPlaces().get(1).getGlobalId();
		assertNull(gId);
	}

	@Test
	public void emptyGlobalId() {
		String gId = albumFile.getPlaces().get(2).getGlobalId();
		assertNull(gId);
	}
}
