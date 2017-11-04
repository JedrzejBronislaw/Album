package jk.album.domain.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PathToolMakeAbsPathTest {

	private static String testDir = "testDir";
	private static String testFile = "album.alb";
	private static String absPathToTestDir;
	private static String absPathToTestFile;

	@BeforeClass
	public static void prepare() throws IOException{
		String mainPath = PathTool.tryCanonicalPath(new File("").getAbsolutePath());
		new File(mainPath + "\\" + testDir).mkdir();
		new File(mainPath + "\\" + testDir + "\\" + testFile).createNewFile();
		absPathToTestDir  = PathTool.tryCanonicalPath(new File(mainPath + "\\" + testDir).getAbsolutePath());
		absPathToTestFile = PathTool.tryCanonicalPath(new File(mainPath + "\\" + testDir + "\\" + testFile).getAbsolutePath());
	}

	@AfterClass
	public static void clear(){
		new File(testDir + "\\" + testFile).delete();
		new File(testDir).delete();
	}

	private String makeAbsolutePath(String filePath, String placePath){
		return PathTool.makeAbsolutePath(filePath, placePath);
	}

	@Test
	public void absoluteFilePathAndRelativePlacePath() {
		String ap = makeAbsolutePath("C:\\dir\\file.txt", "abc\\def\\");
		assertEquals("C:\\dir\\file.txt", ap);
	}

	@Test
	public void absoluteFilePathAndAbsolutePlacePath() {
		String ap = makeAbsolutePath("C:\\dir\\file.txt", "D:\\abc\\def\\");
		assertEquals("C:\\dir\\file.txt", ap);
	}

	@Test
	public void relatveFilePathAndRelativePlacePath() {
		String ap = makeAbsolutePath("dir\\file.txt", "abc\\def\\");
		assertNull(ap);
	}

	@Test
	public void relatveFilePathAndAbsolutePlacePath_commonCase() {
		String ap = makeAbsolutePath("dir\\file.txt", "D:\\abc\\def\\");
		assertEquals("D:\\abc\\def\\dir\\file.txt", ap);
	}

	@Test
	public void filePathWithOneBack() {
		String ap = makeAbsolutePath("..\\dir\\file.txt", "D:\\abc\\def\\");
		assertEquals("D:\\abc\\dir\\file.txt", ap);
	}

	@Test
	public void filePathWithTwoBack() {
		String ap = makeAbsolutePath("..\\..\\dir\\file.txt", "D:\\abc\\def\\");
		assertEquals("D:\\dir\\file.txt", ap);
	}

	@Test
	public void filePathWithTooMoreBack() {
		String ap = makeAbsolutePath("..\\..\\..\\dir\\file.txt", "D:\\abc\\def\\");
		assertNull(ap);
	}

//	@Test
//	public void failWithRelativeFilePathAndAbsolutePlacePath() {
//		String rp = prepare("dir\\file.txt", "C:\\abc\\dir\\");
//		assertNull(rp);
//	}

	@Test
	public void placePathIsPathToAnotherFile_theSameDir() {
		String rp = makeAbsolutePath("file.txt", absPathToTestFile);
		assertEquals(absPathToTestDir + "\\file.txt", rp);
	}

	@Test
	public void placePathIsPathToAnotherFile_descendantDir() {
		String rp = makeAbsolutePath("des\\file.txt", absPathToTestFile);
		assertEquals(absPathToTestDir + "\\des\\file.txt", rp);
	}

	@Test
	public void placePathIsPathToAnotherFile_ancestorDir() {
		String rp = makeAbsolutePath("..\\des\\file.txt", absPathToTestFile);
		String expectedPath = PathTool.tryCanonicalPath(absPathToTestDir + "\\..\\des\\file.txt");
		assertEquals(expectedPath, rp);
	}

	@Test
	public void fileIsChildOfPlace() {
		String rp = makeAbsolutePath("file.txt", "C:\\abc\\dir\\");
		assertEquals("C:\\abc\\dir\\file.txt", rp);
	}

//	@Test
//	public void fileIsChildOfPlace_BothRelativePath() {
//		String rp = prepare("abc\\dir\\file.txt", "abc\\dir\\");
//		assertNull(rp);
//	}

//	@Test
//	public void fileIsDescendantOfPlace_BothAbsolutePath() {
//		String rp = prepare("C:\\abc\\dir\\dir2\\dir3\\file.txt", "C:\\abc\\dir\\");
//		assertEquals("dir2\\dir3\\file.txt", rp);
//	}

//	@Test
//	public void fileIsAncestorOfPlace_BothAbsolutePath() {
//		String rp = prepare("..\\..\\file.txt", "C:\\abc\\dir\\");
//		assertEquals("C:\\file.txt", rp);
//	}

	@Test
	public void fileIsSibilingsOfPlace() {
		String rp = makeAbsolutePath("..\\file.txt", "C:\\abc\\dir\\");
		assertEquals("C:\\abc\\file.txt", rp);
	}

	@Test
	public void fileIsCousinsOfPlace() {
		String rp = makeAbsolutePath("..\\..\\def1\\file.txt", "C:\\abc\\def2\\dir\\");
		assertEquals("C:\\abc\\def1\\file.txt", rp);
	}

	@Test
	public void fileOnAnotherDisc_BothAbsolutePath() {
		String rp = makeAbsolutePath("D:\\abc\\dir\\file.txt", "C:\\abc\\dir\\");
		assertEquals("D:\\abc\\dir\\file.txt", rp);
	}

	@Test
	public void fileOnAnotherDisc_FileRelativePath() {
		String ap = makeAbsolutePath("..\\..\\D:\\abc\\dir\\file.txt", "C:\\abc\\");
		assertNull(ap);
	}

}