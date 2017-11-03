package jk.album.albumfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Link;
import jk.album.domain.tools.PathTool;

public class AlbumFileLinksTest {

	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String dirName = "testDir";
	private static String fileName = dirName + "\\" + "testXMLFile.alb";

	private static String[] paths = new String[]{
			"\\dir1\\description.xml",
			"\\dir2\\pictures.xml",
			"\\dir3\\des.xml",
			"\\dir4\\images.xml"
			};

	@BeforeClass
	public static void preparation(){

		File dir = new File(dirName);
		dir.mkdir();

		ArrayList<Link> links = new ArrayList<>();

		for(int i=0; i<paths.length-1; i++)
			links.add(new Link(paths[i]));


		try {
			File f1;
			f1 = new File("testLinkFile.alb");
			f1.createNewFile();
			System.out.println(PathTool.tryCanonicalPath(f1.getAbsolutePath()));
			links.add(new Link(f1.getAbsolutePath()));
		} catch (IOException e) {}

		File f2;
		f2 = new File("testLinkFailFile.alb");
		links.add(new Link(f2.getAbsolutePath()));

		links.add(new Link(paths[paths.length-1]));//last link


		albumFile.setLinks(links);

		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);
	}

	@AfterClass
	public static void clear(){
//		albumFile = AlbumFileFactory.build();
		System.out.println("---");
		File f1;
		f1 = new File("testLinkFile.alb");
		f1.delete();

//		try{
			f1 = new File(fileName);

			System.out.println(f1.getAbsolutePath());
			System.out.println(f1.delete());
//		} catch (SecurityException e)
//		{
//			System.out.println("!");
//		}
		f1 = new File(dirName);
		f1.delete();
	}

	@Test
	public void countOfLinks() {
		int size = albumFile.getLinks().size();

		assertEquals(6, size);
	}

	@Test
	public void firstPath() {
		String path = albumFile.getLinks().get(0).getPath();
		assertEquals("\\dir1\\description.xml", path);
	}

	@Test
	public void lastPath() {
		int size = albumFile.getLinks().size();
		String path = albumFile.getLinks().get(size-1).getPath();
		assertEquals("\\dir4\\images.xml", path);
	}

	@Test
	public void checkCorrectLink() {
		String path = albumFile.getLinks().get(3).getPath();
//		String absPath = PathTool.makeAbsolutePath(path, fileName);
//		System.out.println(absPath);
		System.out.println(path);
		File f = new File(path);

		System.out.println(PathTool.tryCanonicalPath(path));
		assertTrue(f.exists());
	}

	@Test
	public void checkFailLink() {
		String path = albumFile.getLinks().get(4).getPath();
		File f = new File(path);

		assertFalse(f.exists());
	}


}