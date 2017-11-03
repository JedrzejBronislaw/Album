package jk.album.albumfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Author;

public class AlbumFileAuthorsTest {

	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";


	@BeforeClass
	public static void preparation(){
		Author a1, a2, a3;

		a1 = new Author();
		a2 = new Author();
		a3 = new Author();

		a1.setName("Author1");
		a1.setDescription("First author");
		a2.setName("Author2");
		a2.setDescription("Second author");
		a3.setName("Author3");
		a3.setDescription("Thrid author");

		a1.setGlobalId("abc");
		a2.setGlobalId("");
		a3.setGlobalId(null);

		ArrayList<Author> authors = new ArrayList<>();
		authors.add(a1);
		authors.add(a2);
		authors.add(a3);
		albumFile.setAuthors(authors);

		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);
	}

	@Test
	public void countOfAuthors() {
		int size = albumFile.getAuthors().size();

		assertEquals(3, size);
	}

	@Test
	public void firstName() {
		String name = albumFile.getAuthors().get(0).getName();
		assertEquals("Author1", name);
	}

	@Test
	public void lastName() {
		int size = albumFile.getAuthors().size();
		String name = albumFile.getAuthors().get(size-1).getName();
		assertEquals("Author3", name);
	}

	@Test
	public void firstDescription() {
		String des = albumFile.getAuthors().get(0).getDescription();
		assertEquals("First author", des);
	}

	@Test
	public void lastDescription() {
		int size = albumFile.getAuthors().size();
		String des = albumFile.getAuthors().get(size-1).getDescription();
		assertEquals("Thrid author", des);
	}

	@Test
	public void notNullAndNotEmptyGlobalId() {
		String gId = albumFile.getAuthors().get(0).getGlobalId();
		assertEquals("abc", gId);
	}

	@Test
	public void nullGlobalId() {
		String gId = albumFile.getAuthors().get(1).getGlobalId();
		assertNull(gId);
	}

	@Test
	public void emptyGlobalId() {
		String gId = albumFile.getAuthors().get(2).getGlobalId();
		assertNull(gId);
	}


}
