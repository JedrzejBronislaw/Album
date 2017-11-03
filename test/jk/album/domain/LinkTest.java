package jk.album.domain;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkTest {

	private static String[] pathsD = new String[]{
			"testDir",
			"testDir\\dir1",
			"testDir\\dir2"
			};

	private static String[] pathsF = new String[]{
			"testDir\\descripion.alb",
			"testDir\\dir1\\pics.alb",
			"testDir\\dir2\\images.alb"
			};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File dir;
		for(String path : pathsD){
			dir = new File(path);
			dir.mkdir();
		}
		for(String path : pathsF){
			dir = new File(path);
			dir.createNewFile();
		}
	}


	@AfterClass
	public static void AfterClass() {
		File dir;
		for(String path : pathsF){
			dir = new File(path);
			dir.delete();
		}
		for(int i=pathsD.length-1; i>=0; i--){
			dir = new File(pathsD[i]);
			dir.delete();
		}
	}

	@Test
	public void linkNotChecked() {
		Link link = new Link(pathsF[0]);
		Boolean fileExists = link.fileExists();
		assertNull(fileExists);
	}

	@Test
	public void correctLink() {
		Link link = new Link(pathsF[0]);
		link.checkFile();
		Boolean fileExists = link.fileExists();
		assertEquals(true, fileExists.booleanValue());
	}

	@Test
	public void incorrectLink() {
		Link link = new Link(pathsF[0]+"a");
		link.checkFile();
		Boolean fileExists = link.fileExists();
		assertEquals(false, fileExists.booleanValue());
	}

	@Test
	public void correctLinkInDirectory() {
		Link link = new Link(pathsF[1]);
		link.checkFile();
		Boolean fileExists = link.fileExists();
		assertEquals(true, fileExists.booleanValue());
	}

	@Test
	public void incorrectLinkInDirectory() {
		Link link = new Link(pathsF[1]+"a");
		link.checkFile();
		Boolean fileExists = link.fileExists();
		assertEquals(false, fileExists.booleanValue());
	}

	@Test
	public void correctLinkInDirectory2() {
		Link link = new Link(pathsF[2]);
		link.checkFile();
		Boolean fileExists = link.fileExists();
		assertEquals(true, fileExists.booleanValue());
	}

}
