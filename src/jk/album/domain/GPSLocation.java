package jk.album.domain;

import javax.naming.directory.InvalidAttributeValueException;

public class GPSLocation {

	public enum latDir {North, South};
	public enum lonDir {East, West};

	private double latitude;
	private double longitude;

	private boolean setLat = false, setLon = false;


	public double getLatitude() {
		return (setLat) ? latitude : null;
	}
	public void setLatitude(double latitude, latDir directory) throws InvalidAttributeValueException {
		latitudeValidate(latitude);

		this.latitude = latitude;
		if (directory == latDir.South)
			latitude *= -1;
		setLat = true;
	}
	public void setLatitude(double latitude) throws InvalidAttributeValueException {
		latitudeValidate(latitude);
		this.latitude = latitude;
		setLat = true;
	}
	private void latitudeValidate(double latitude) throws InvalidAttributeValueException{
		if(Math.abs(latitude) > 90) throw new InvalidAttributeValueException("Latitude greater than 90: " + latitude);
	}

	public double getLongitude() {
		return (setLon) ? longitude : null;
	}
	public void setLongitude(double longitude, lonDir directory) {
		longitude = longitudeValidate(longitude);

		if (directory == lonDir.West)
			longitude *= -1;
		this.longitude = longitude;
		setLon = true;
	}
	public void setLongitude(double longitude) {
		longitude =longitudeValidate(longitude);

		this.longitude = longitude;
		setLon = true;
	}

	private double longitudeValidate(double longitude){
		longitude = longitude%360;
		if(longitude > 180)
			longitude = longitude%180 - 180;
		if(longitude < -180)
			longitude = 180 + longitude%180;
		return longitude;
	}
}
