package jk.album.domain.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PathToolMakeRelPathTest {


	private String prepare(String filePath, String placePath){
		return PathTool.makeRelativePath(filePath, placePath);
	}

	@Test
	public void failWithAbsoluteFilePathAndRelativePlacePath() {
		String rp = prepare("C:\\dir\\file.txt", "abc\\def\\");
		assertNull(rp);
	}

	@Test
	public void failWithRelativeFilePathAndAbsolutePlacePath() {
		String rp = prepare("dir\\file.txt", "C:\\abc\\dir\\");
		assertNull(rp);
	}

	@Test
	public void fileIsChildOfPlace_BothAbsolutePath() {
		String rp = prepare("C:\\abc\\dir\\file.txt", "C:\\abc\\dir\\");
		assertEquals("file.txt", rp);
	}

	@Test
	public void fileIsChildOfPlace_BothRelativePath() {
		String rp = prepare("abc\\dir\\file.txt", "abc\\dir\\");
		assertNull(rp);
	}

	@Test
	public void fileIsDescendantOfPlace_BothAbsolutePath() {
		String rp = prepare("C:\\abc\\dir\\dir2\\dir3\\file.txt", "C:\\abc\\dir\\");
		assertEquals("dir2\\dir3\\file.txt", rp);
	}

	@Test
	public void fileIsAncestorOfPlace_BothAbsolutePath() {
		String rp = prepare("C:\\file.txt", "C:\\abc\\dir\\");
		assertEquals("..\\..\\file.txt", rp);
	}

	@Test
	public void fileIsSibilingsOfPlace_BothAbsolutePath() {
		String rp = prepare("C:\\abc\\file.txt", "C:\\abc\\dir\\");
		assertEquals("..\\file.txt", rp);
	}

	@Test
	public void fileIsCousinsOfPlace_BothAbsolutePath() {
		String rp = prepare("C:\\abc\\def1\\file.txt", "C:\\abc\\def2\\dir\\");
		assertEquals("..\\..\\def1\\file.txt", rp);
	}

	@Test
	public void fileOnAnotherDisc_BothAbsolutePath() {
		String rp = prepare("D:\\abc\\dir\\file.txt", "C:\\abc\\dir\\");
		assertEquals("D:\\abc\\dir\\file.txt", rp);
	}

}
