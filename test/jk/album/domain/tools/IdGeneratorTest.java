package jk.album.domain.tools;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.AssertionFailedError;

public class IdGeneratorTest {

	public static String[] ids = new String[100];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		IdGenerator gen = new IdGenerator();

		for(int i=0; i<ids.length; i++)
			ids[i] = gen.generate();
	}

	@Test
	public void onlyAlfanum() {
		assertTrue(isAlfanum(ids));
	}

	public boolean isAlfanum(String[] ids) {

		for(String id : ids){
//			System.out.println(id);
			for(int i=0; i<id.length(); i++){
				char c = id.charAt(0);
				if (!Character.isDigit(c) && !Character.isLetter(c)) throw new AssertionFailedError(id + " is not a alfanumeral string.");// return false;
			}
		}
		return true;
	}

}
