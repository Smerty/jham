package org.smerty.jham;

public class Location {

	private double latitude;
	private double longitude;

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(String maidenhead) {
		this.latitude = extractLat(maidenhead);
		this.longitude = extractLon(maidenhead);
	}

	public String toMaidenhead() {
		return toMaidenhead(this.latitude, this.longitude);
	}

	public static String toMaidenhead(double latitude, double longitude) {

		longitude += 180;
		longitude /= 2;
		char lon_first = (char) ('A' + (Math.floor(longitude / 10)));
		char lon_second = (char) ('0' + Math.floor(longitude % 10));
		char lon_third = (char) ('A' + Math.floor((longitude % 1) * 24));

		latitude += 90;
		char lat_first = (char) ('A' + (Math.floor(latitude / 10)));
		char lat_second = (char) ('0' + Math.floor(latitude % 10));
		char lat_third = (char) ('A' + Math.floor((latitude % 1) * 24));

		StringBuilder sb = new StringBuilder();
		sb.append(lon_first);
		sb.append(lat_first);
		sb.append(lon_second);
		sb.append(lat_second);
		sb.append(("" + lon_third).toLowerCase());
		sb.append(("" + lat_third).toLowerCase());

		return sb.toString();
	}

	public static double extractLat(String maidenhead) {
		maidenhead = maidenhead.toUpperCase();
		double latitude = -90 + 10 * (maidenhead.charAt(1) - 'A') + 1 * (maidenhead.charAt(3) - '0') + 2.5/60 * (maidenhead.charAt(5) - 'A') + 2.5/60/2;
		return latitude;
	}

	public static double extractLon(String maidenhead) {
		maidenhead = maidenhead.toUpperCase();
		double longitude = -180 + 20 * (maidenhead.charAt(0) - 'A') + 2 * (maidenhead.charAt(2) - '0') + 5.0/60 * (maidenhead.charAt(4) - 'A') + 5.0/60/2;
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
