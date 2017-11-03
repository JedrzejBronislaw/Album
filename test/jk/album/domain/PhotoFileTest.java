package jk.album.domain;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class PhotoFileTest {

	private static PhotoFile photoFile;
	private static PhotoFile emptyPhotoFile;

	@BeforeClass
	public static void preparation(){
//		photoFile = new PhotoFile(new File("E:\\programy\\Album\\JAVA\\Album\\res\\malta.jpg"));
		photoFile = new PhotoFile(new File("..\\Album\\res\\malta.jpg"));
		emptyPhotoFile = new PhotoFile(new File("..\\Album\\res\\malta1.jpg"));
	}

	@Test
	public void path() {
		assertEquals("E:\\programy\\Album\\JAVA\\Album\\res\\malta.jpg", photoFile.getPath());
	}

	@Test
	public void size() {
		assertEquals(1283502, photoFile.getSize());
	}

	@Test
	public void width() {
		assertEquals(1712, photoFile.getWidth());
	}

	@Test
	public void height() {
		assertEquals(2288, photoFile.getHeight());
	}

	@Test
	public void hash() {
		assertEquals("E3CA9E7CA82884AFC6196A5085ED2D1B8452BB57754A8B49D9C4E673D6A3", photoFile.getHash());
	}


	@Test
	public void nonPath() {
		assertNull(emptyPhotoFile.getPath());
	}

	@Test
	public void nonSize() {
		assertEquals(0, emptyPhotoFile.getSize());
	}

	@Test
	public void nonWidth() {
		assertEquals(0, emptyPhotoFile.getWidth());
	}

	@Test
	public void nonHeight() {
		assertEquals(0, emptyPhotoFile.getHeight());
	}

	@Test
	public void nonHash() {
		assertNull(emptyPhotoFile.getHash());
	}

}
