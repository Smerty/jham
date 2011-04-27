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

  /**
   * latitude in degrees, positive for northern hemisphere, negative for
   * southern hemisphere.
   */
  private double latitude;
  /**
   * longitude in degrees, positive for eastern hemisphere, negative for western
   * hemisphere.
   */
  private double longitude;

  /** No argument constructor.
   *
   */
  public Location() {
  }

  /**
   * @param latitudeIn initial latitude
   * @param longitudeIn initial longitude
   */
  public Location(final double latitudeIn, final double longitudeIn) {
    this.latitude = latitudeIn;
    this.longitude = longitudeIn;
  }

  /**
   * @param maidenhead used construct location from maidenhead locator string
   */
  public Location(final String maidenhead) {
    this.latitude = extractLat(maidenhead);
    this.longitude = extractLon(maidenhead);
  }

  /**
   * @return maidenhead locator string
   */
  final public String toMaidenhead() {
    return toMaidenhead(this.latitude, this.longitude);
  }

  /**
   * @param latitudeIn latitude component of locator string
   * @param longitudeIn longitude component of locator string
   * @return maidenhead locator string
   */
  public static String toMaidenhead(final double latitudeIn,
      final double longitudeIn) {

    double longitude = longitudeIn + 180;
    longitude /= 2;
    char lonfirst = (char) ('A' + (Math.floor(longitude / 10)));
    char lonsecond = (char) ('0' + Math.floor(longitude % 10));
    char lonthird = (char) ('A' + Math.floor((longitude % 1) * 24));

    double latitude = latitudeIn + 90;
    char latfirst = (char) ('A' + (Math.floor(latitude / 10)));
    char latsecond = (char) ('0' + Math.floor(latitude % 10));
    char latthird = (char) ('A' + Math.floor((latitude % 1) * 24));

    StringBuilder sb = new StringBuilder();
    sb.append(lonfirst);
    sb.append(latfirst);
    sb.append(lonsecond);
    sb.append(latsecond);
    sb.append(("" + lonthird).toLowerCase());
    sb.append(("" + latthird).toLowerCase());

    return sb.toString();
  }

  /**
   * @param maidenheadIn locator string to be converted
   * @return latitude
   */
  public static double extractLat(final String maidenheadIn) {
    String maidenhead = maidenheadIn.toUpperCase();
    double latitude = -90 + 10 * (maidenhead.charAt(1) - 'A') + 1
        * (maidenhead.charAt(3) - '0') + 2.5 / 60
        * (maidenhead.charAt(5) - 'A') + 2.5 / 60 / 2;
    return latitude;
  }

  /**
   * @param maidenheadIn locator string to be converted
   * @return longitude
   */
  public static double extractLon(final String maidenheadIn) {
    String maidenhead = maidenheadIn.toUpperCase();
    double longitude = -180 + 20 * (maidenhead.charAt(0) - 'A') + 2
        * (maidenhead.charAt(2) - '0') + 5.0 / 60
        * (maidenhead.charAt(4) - 'A') + 5.0 / 60 / 2;
    return longitude;
  }

  /**
   * @return latitude
   */
  final public double getLatitude() {
    return latitude;
  }

  /**
   * @return longitude
   */
  final public double getLongitude() {
    return longitude;
  }

  /**
   * @param latitudeIn north/south component of location
   */
  final public void setLatitude(final double latitudeIn) {
    this.latitude = latitudeIn;
  }

  /**
   * @param longitudeIn east/west component of location
   */
  final public void setLongitude(final double longitudeIn) {
    this.longitude = longitudeIn;
  }
}
