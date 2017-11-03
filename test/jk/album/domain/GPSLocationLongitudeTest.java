package jk.album.domain;

import static org.junit.Assert.*;

import javax.naming.directory.InvalidAttributeValueException;

import org.junit.BeforeClass;
import org.junit.Test;

import jk.album.domain.GPSLocation.lonDir;

public class GPSLocationLongitudeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void longitude10() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(10d);
		assertEquals(10d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus10() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-10d);
		assertEquals(-10d, gps.getLongitude(),0);
	}

	@Test
	public void longitude0Point75() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(0.75);
		assertEquals(0.75, gps.getLongitude(),0);
	}

	@Test
	public void longitude0() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(0d);
		assertEquals(0d, gps.getLongitude(),0);
	}

	@Test
	public void longitude180() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(180d);
		assertEquals(180d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus180() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-180d);
		assertEquals(-180d, gps.getLongitude(),0);
	}

	@Test
	public void longitude181() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(181d);
		assertEquals(-179d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus181() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-181d);
		assertEquals(179d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus100() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-100d);
		assertEquals(-100d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus400() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(400d);
		assertEquals(40d, gps.getLongitude(),0);
	}

	@Test
	public void longitude450() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(450d);
		assertEquals(90d, gps.getLongitude(),0);
	}

	@Test
	public void longitude20West() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(20d,lonDir.West);
		assertEquals(-20d, gps.getLongitude(),0);
	}

	@Test
	public void longitude20East() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(20d,lonDir.East);
		assertEquals(20d, gps.getLongitude(),0);
	}

	@Test
	public void longitude190West() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(190d,lonDir.West);
		assertEquals(170d, gps.getLongitude(),0);
	}

	@Test
	public void longitude190East() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(190d,lonDir.East);
		assertEquals(-170d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus190West() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-190d,lonDir.West);
		assertEquals(-170d, gps.getLongitude(),0);
	}

	@Test
	public void longitudeMinus190East() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLongitude(-190d,lonDir.East);
		assertEquals(170d, gps.getLongitude(),0);
	}

}
