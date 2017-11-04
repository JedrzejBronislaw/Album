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
import jk.album.domain.PhotoFile;
import jk.album.domain.Place;
import jk.album.domain.GPSLocation.latDir;
import jk.album.domain.GPSLocation.lonDir;

public class AlbumFilePhotosTest {


	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Photo p1, p2, p3, p4, p5;
		Author a1, a2;
		GPSLocation gps;
		ArrayList<String> tags = new ArrayList<String>();

		p1 = new Photo();
		p2 = new Photo();
		p3 = new Photo();
		p4 = new Photo();
		p5 = new Photo();

		a1 = new Author();
		a2 = new Author();

		gps = new GPSLocation();

		p1.setGlobalId("abc");
		p2.setGlobalId("");
		p3.setGlobalId(null);

		a1.setName("Jan Kowalski");
		a1.setDescription("The best photografer");
		a2.setName("Piotr Nowak");
		a2.setDescription("The worst photografer");

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

		p2.setAuthor(a1);
		p2.setPath("pic2.jpg");
		p3.setAuthor(a2);
		p3.setPath("bitmaps\\pic3.bmp");

		p4.setPhotoFile(new PhotoFile("..\\Album\\res\\malta.jpg"));

		ArrayList<Photo> photos = new ArrayList<Photo>();
		photos.add(p1);
		photos.add(p2);
		photos.add(p3);
		photos.add(p4);
		photos.add(p5);

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
	public void countOfPhotos() {
		int count = albumFile.getPhotos().size();
		assertEquals(5, count);
	}

	@Test
	public void pathPic1(){
		String path = albumFile.getPhotos().get(0).getPath();
		assertEquals("c:\\pic1.jpg", path);
	}

	@Test
	public void pathPic2(){
		String path = albumFile.getPhotos().get(1).getPath();
		assertEquals("pic2.jpg", path);
	}

	@Test
	public void pathPic3(){
		String path = albumFile.getPhotos().get(2).getPath();
		assertEquals("bitmaps\\pic3.bmp", path);
	}

	@Test
	public void title(){
		String title = albumFile.getPhotos().get(0).getTitle();
		assertEquals("Surprise", title);
	}

	@Test
	public void date(){
		String date = albumFile.getPhotos().get(0).getDate();
		assertEquals("2017.10.03", date);
	}

	@Test
	public void description(){
		String description = albumFile.getPhotos().get(0).getDescription();
		assertEquals("Dogs and cats.", description);
	}

	@Test
	public void gpsLatitude(){
		GPSLocation gps = albumFile.getPhotos().get(0).getGps();
		assertEquals(23.456789, gps.getLatitude(),0);
	}

	@Test
	public void gpsLongitude(){
		GPSLocation gps = albumFile.getPhotos().get(0).getGps();
		assertEquals(12.345678, gps.getLongitude(),0);
	}

	@Test
	public void occasion(){
		String occasion = albumFile.getPhotos().get(0).getOccasion();
		assertEquals("Birthday", occasion);
	}

	@Test
	public void placeName(){
		Place place = albumFile.getPhotos().get(0).getPlace();
		assertEquals("Home", place.getName());
	}

	@Test
	public void placeDescription(){
		Place place = albumFile.getPhotos().get(0).getPlace();
		assertEquals("Home in Poznan", place.getDescription());
	}

	@Test
	public void countOfTags(){
		int count = albumFile.getPhotos().get(0).getTags().size();
		assertEquals(2, count);
	}

	@Test
	public void FirstTag(){
		List<String> tags = albumFile.getPhotos().get(0).getTags();
		assertEquals("fun", tags.get(0));
	}

	@Test
	public void LastTag(){
		int count = albumFile.getPhotos().get(0).getTags().size();
		List<String> tags = albumFile.getPhotos().get(0).getTags();
		assertEquals("pet", tags.get(count-1));
	}

	@Test
	public void author(){
		Author author = albumFile.getPhotos().get(0).getAuthor();
		assertEquals("Jan Kowalski", author.getName());
	}

	@Test
	public void otherAuthor(){
		Author author = albumFile.getPhotos().get(2).getAuthor();
		assertEquals("Piotr Nowak", author.getName());
	}

	@Test
	public void nullAuthor(){
		Author author = albumFile.getPhotos().get(3).getAuthor();
		assertNull(author);
	}

	@Test
	public void theSameAuthorFor2Photos(){
		Author author1 = albumFile.getPhotos().get(0).getAuthor();
		Author author2 = albumFile.getPhotos().get(1).getAuthor();
		assertNotNull(author1);
		assertSame(author1, author2);
	}

	@Test
	public void diffrentAuthorFor2Photos(){
		Author author1 = albumFile.getPhotos().get(0).getAuthor();
		Author author2 = albumFile.getPhotos().get(2).getAuthor();
		assertNotSame(author1, author2);
	}
	@Test
	public void notNullAndNotEmptyGlobalId() {
		String gId = albumFile.getPhotos().get(0).getGlobalId();
		assertEquals("abc", gId);
	}

	@Test
	public void nullGlobalId() {
		String gId = albumFile.getPhotos().get(1).getGlobalId();
		assertNull(gId);
	}

	@Test
	public void emptyGlobalId() {
		String gId = albumFile.getPhotos().get(2).getGlobalId();
		assertNull(gId);
	}

	@Test
	public void canonicalPathOfExistingFile() {
		String path = albumFile.getPhotos().get(3).getPath();
		assertEquals("E:\\programy\\Album\\JAVA\\Album\\res\\malta.jpg", path);
	}

	@Test
	public void sizeOfExistingFile() {
		long size = albumFile.getPhotos().get(3).getPhotoFile().getSize();
		assertEquals(1283502, size);
	}

	@Test
	public void widthExistingFile() {
		int width = albumFile.getPhotos().get(3).getPhotoFile().getWidth();
		assertEquals(2288, width);
	}

	@Test
	public void heightExistingFile() {
		int height = albumFile.getPhotos().get(3).getPhotoFile().getHeight();
		assertEquals(1712, height);
	}

	@Test
	public void hashExistingFile() {
		String hash = albumFile.getPhotos().get(3).getPhotoFile().getHash();
		assertEquals("E3CA9E7CA82884AFC6196A5085ED2D1B8452BB57754A8B49D9C4E673D6A3", hash);
	}

}
