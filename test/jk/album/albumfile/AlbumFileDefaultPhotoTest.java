package jk.album.albumfile;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Author;
import jk.album.domain.GPSLocation;
import jk.album.domain.Photo;
import jk.album.domain.Place;
import jk.album.domain.GPSLocation.latDir;
import jk.album.domain.GPSLocation.lonDir;

public class AlbumFileDefaultPhotoTest {


	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Photo p1;
		Author a1;
		GPSLocation gps;
		ArrayList<String> tags = new ArrayList<String>();

		p1 = new Photo();

		a1 = new Author();

		gps = new GPSLocation();

		a1.setName("Jan Kowalski");
		a1.setDescription("The best photografer");

		gps.setLatitude(23.456789, latDir.North);
		gps.setLongitude(12.345678, lonDir.East);

		tags.add("fun");
		tags.add("pet");

		p1.setAuthor(a1);
		p1.setPath("c:\\pic1.jpg");
		p1.setTitle("Surprise");
		p1.setDate("2017.10.03");
		p1.setDescription("Dogs and cats.");
		p1.setGps(gps);
		p1.setOccasion("Birthday");
		p1.setPlace(new Place("Home", "Home in Poznan", null));
		p1.setTags(tags);

		albumFile.setDefaultPhoto(p1);
		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);

	}

	@AfterClass
	public static void clear(){
		new File(fileName).delete();
	}

	@Test
	public void existingDefaultPhotos() {
		Photo dafaulPhoto = albumFile.getDefaultPhoto();
		assertNotNull(dafaulPhoto);
	}

	@Test
	public void erasingPath(){
		String path = albumFile.getDefaultPhoto().getPath();
		assertNull(path);
	}

	@Test
	public void title(){
		String title = albumFile.getDefaultPhoto().getTitle();
		assertEquals("Surprise", title);
	}

	@Test
	public void date(){
		String date = albumFile.getDefaultPhoto().getDate();
		assertEquals("2017.10.03", date);
	}

	@Test
	public void description(){
		String description = albumFile.getDefaultPhoto().getDescription();
		assertEquals("Dogs and cats.", description);
	}

	@Test
	public void gpsLatitude(){
		GPSLocation gps = albumFile.getDefaultPhoto().getGps();
		assertEquals(23.456789, gps.getLatitude(),0);
	}

	@Test
	public void gpsLongitude(){
		GPSLocation gps = albumFile.getDefaultPhoto().getGps();
		assertEquals(12.345678, gps.getLongitude(),0);
	}

	@Test
	public void occasion(){
		String occasion = albumFile.getDefaultPhoto().getOccasion();
		assertEquals("Birthday", occasion);
	}

	@Test
	public void placeName(){
		Place place = albumFile.getDefaultPhoto().getPlace();
		assertEquals("Home", place.getName());
	}

	@Test
	public void placeDescription(){
		Place place = albumFile.getDefaultPhoto().getPlace();
		assertEquals("Home in Poznan", place.getDescription());
	}

	@Test
	public void countOfTags(){
		int count = albumFile.getDefaultPhoto().getTags().size();
		assertEquals(2, count);
	}

	@Test
	public void FirstTag(){
		List<String> tags = albumFile.getDefaultPhoto().getTags();
		assertEquals("fun", tags.get(0));
	}

	@Test
	public void LastTag(){
		int count = albumFile.getDefaultPhoto().getTags().size();
		List<String> tags = albumFile.getDefaultPhoto().getTags();
		assertEquals("pet", tags.get(count-1));
	}

	@Test
	public void author(){
		Author author = albumFile.getDefaultPhoto().getAuthor();
		assertEquals("Jan Kowalski", author.getName());
	}

}
