package jk.album.domain.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PathToolMakeRelPathTest {


	private String makeRelativePath(String filePath, String placePath){
		return PathTool.makeRelativePath(filePath, placePath);
	}

	@Test
	public void failWithAbsoluteFilePathAndRelativePlacePath() {
		String rp = makeRelativePath("C:\\dir\\file.txt", "abc\\def\\");
		assertNull(rp);
	}

	@Test
	public void failWithRelativeFilePathAndAbsolutePlacePath() {
		String rp = makeRelativePath("dir\\file.txt", "C:\\abc\\dir\\");
		assertNull(rp);
	}

	@Test
	public void fileIsChildOfPlace_BothAbsolutePath() {
		String rp = makeRelativePath("C:\\abc\\dir\\file.txt", "C:\\abc\\dir\\");
		assertEquals("file.txt", rp);
	}

	@Test
	public void fileIsChildOfPlace_BothRelativePath() {
		String rp = makeRelativePath("abc\\dir\\file.txt", "abc\\dir\\");
		assertNull(rp);
	}

	@Test
	public void fileIsDescendantOfPlace_BothAbsolutePath() {
		String rp = makeRelativePath("C:\\abc\\dir\\dir2\\dir3\\file.txt", "C:\\abc\\dir\\");
		assertEquals("dir2\\dir3\\file.txt", rp);
	}

	@Test
	public void fileIsAncestorOfPlace_BothAbsolutePath() {
		String rp = makeRelativePath("C:\\file.txt", "C:\\abc\\dir\\");
		assertEquals("..\\..\\file.txt", rp);
	}

	@Test
	public void fileIsSibilingsOfPlace_BothAbsolutePath() {
		String rp = makeRelativePath("C:\\abc\\file.txt", "C:\\abc\\dir\\");
		assertEquals("..\\file.txt", rp);
	}

	@Test
	public void fileIsCousinsOfPlace_BothAbsolutePath() {
		String rp = makeRelativePath("C:\\abc\\def1\\file.txt", "C:\\abc\\def2\\dir\\");
		assertEquals("..\\..\\def1\\file.txt", rp);
	}

	@Test
	public void fileOnAnotherDisc_BothAbsolutePath() {
		String rp = makeRelativePath("D:\\abc\\dir\\file.txt", "C:\\abc\\dir\\");
		assertEquals("D:\\abc\\dir\\file.txt", rp);
	}

	@Test
	public void testFromRealError() {
		String rp = makeRelativePath("E:\\a\\b\\c\\d\\testLinkFile.alb", "E:\\a\\b\\c\\d\\testDir");
		assertEquals("..\\testLinkFile.alb", rp);
	}
}
