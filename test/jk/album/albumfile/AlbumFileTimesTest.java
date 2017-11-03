package jk.album.albumfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Being;

public class AlbumFileTimesTest {

	private static AlbumFile albumFileBefore = AlbumFileFactory.build();
	private static AlbumFile albumFileAfter = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";


	@BeforeClass
	public static void preparation(){
		Being o1;
		o1 = new Being("Object 1", "this is object one");

		ArrayList<Being> beings = new ArrayList<Being>();
		beings.add(o1);
		albumFileBefore.setBeings(beings);

		albumFileBefore.save(fileName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		albumFileAfter = AlbumFileFactory.build();
		albumFileAfter.load(fileName);
		albumFileAfter.save(fileName);

		albumFileAfter = AlbumFileFactory.build();
		albumFileAfter.load(fileName);
	}

	@Test
	public void differentEditTime() {
		String timeB = albumFileBefore.getEditDate().toString();
		String timeA = albumFileAfter.getEditDate().toString();

		assertNotEquals(timeB, timeA);
	}

	@Test
	public void theSameCreateTime() {
		String timeB = albumFileBefore.getCreateDate().toString();
		String timeA = albumFileAfter.getCreateDate().toString();

		assertEquals(timeB, timeA);
	}

}
