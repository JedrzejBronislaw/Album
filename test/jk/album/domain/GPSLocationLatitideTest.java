package jk.album.domain;

import static org.junit.Assert.*;

import javax.naming.directory.InvalidAttributeValueException;

import org.junit.BeforeClass;
import org.junit.Test;

public class GPSLocationLatitideTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void latitideMinus10() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(-10d);
		assertEquals(-10d, gps.getLatitude(),0);
	}

	@Test
	public void latitide0() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(0d);
		assertEquals(0d, gps.getLatitude(),0);
	}

	@Test
	public void latitide90() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(90d);
		assertEquals(90d, gps.getLatitude(),0);
	}

	@Test
	public void latitideMinus90() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(-90d);
		assertEquals(-90d, gps.getLatitude(),0);
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void latitide91() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(91d);
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void latitideMinus100() throws InvalidAttributeValueException {
		GPSLocation gps = new GPSLocation();
		gps.setLatitude(-100d);
	}

}
