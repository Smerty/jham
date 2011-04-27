package org.smerty.jham;

/**
 * Location class with methods allowing conversion to and from Maidenhead
 * locator (grid squares) based off of
 * "Conversion Between Geodetic and Grid Locator Systems" by Edmund T. Tyson,
 * N5JTY in QST January 1989, pp. 29-30, 43
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Location {

  private double latitude;
  private double longitude;

  /** No argument constructor
   *
   */
  public Location() {
  }

  /**
   * @param latitude
   * @param longitude
   */
  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * @param maidenhead
   */
  public Location(String maidenhead) {
    this.latitude = extractLat(maidenhead);
    this.longitude = extractLon(maidenhead);
  }

  /**
   * @return maidenhead locator string
   */
  public String toMaidenhead() {
    return toMaidenhead(this.latitude, this.longitude);
  }

  /**
   * @param latitude
   * @param longitude
   * @return maidenhead locator string
   */
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

  /**
   * @param maidenhead
   * @return latitude
   */
  public static double extractLat(String maidenhead) {
    maidenhead = maidenhead.toUpperCase();
    double latitude = -90 + 10 * (maidenhead.charAt(1) - 'A') + 1 * (maidenhead.charAt(3) - '0') + 2.5 / 60 * (maidenhead.charAt(5) - 'A') + 2.5 / 60 / 2;
    return latitude;
  }

  /**
   * @param maidenhead
   * @return longitude
   */
  public static double extractLon(String maidenhead) {
    maidenhead = maidenhead.toUpperCase();
    double longitude = -180 + 20 * (maidenhead.charAt(0) - 'A') + 2 * (maidenhead.charAt(2) - '0') + 5.0 / 60 * (maidenhead.charAt(4) - 'A') + 5.0 / 60 / 2;
    return longitude;
  }

  /**
   * @return latitude
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * @return longitude
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * @param latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * @param longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
