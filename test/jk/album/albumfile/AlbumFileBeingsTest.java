package jk.album.albumfile;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.Being;
import jk.album.domain.Being.Type;

public class AlbumFileBeingsTest {

	private static AlbumFile albumFile = AlbumFileFactory.build();
	private static String fileName = "testXMLFile.xml";


	@BeforeClass
	public static void preparation(){
		Being o1, o2, o3;
		o1 = new Being("Object 1", "this is object one");
		o2 = new Being("Object 2", "this is object two");
		o3 = new Being("Object 3", "this is object three");

		o2.setType(Type.Human);
		o3.setType(Type.Building);

		o1.setGlobalId("abc");
		o2.setGlobalId("");
		o3.setGlobalId(null);

		ArrayList<Being> beings = new ArrayList<Being>();
		beings.add(o1);
		beings.add(o2);
		beings.add(o3);
		albumFile.setBeings(beings);

		albumFile.save(fileName);

		albumFile = AlbumFileFactory.build();
		albumFile.load(fileName);
	}

	@AfterClass
	public static void clear(){
		new File(fileName).delete();
	}

	@Test
	public void countOfBeings() {
		int size = albumFile.getBeings().size();

		assertEquals(3, size);
	}

	@Test
	public void firstName() {
		String name = albumFile.getBeings().get(0).getName();
		assertEquals("Object 1", name);
	}

	@Test
	public void lastName() {
		int size = albumFile.getBeings().size();
		String name = albumFile.getBeings().get(size-1).getName();
		assertEquals("Object 3", name);
	}

	@Test
	public void firstDescription() {
		String des = albumFile.getBeings().get(0).getDescription();
		assertEquals("this is object one", des);
	}

	@Test
	public void lastDescription() {
		int size = albumFile.getBeings().size();
		String des = albumFile.getBeings().get(size-1).getDescription();
		assertEquals("this is object three", des);
	}

	@Test
	public void defaultTypeIsOther() {
		Type type = albumFile.getBeings().get(0).getType();
		assertEquals(Type.Other, type);
	}

	@Test
	public void saveAndReadTypeHuman() {
		Type type = albumFile.getBeings().get(1).getType();
		assertEquals(Type.Human, type);
	}

	@Test
	public void saveAndReadTypeBuilding() {
		Type type = albumFile.getBeings().get(2).getType();
		assertEquals(Type.Building, type);
	}

	@Test
	public void notNullAndNotEmptyGlobalId() {
		String gId = albumFile.getBeings().get(0).getGlobalId();
		assertEquals("abc", gId);
	}

	@Test
	public void nullGlobalId() {
		String gId = albumFile.getBeings().get(1).getGlobalId();
		assertNull(gId);
	}

	@Test
	public void emptyGlobalId() {
		String gId = albumFile.getBeings().get(2).getGlobalId();
		assertNull(gId);
	}


}
