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
  public final String toMaidenhead() {
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
    char lonFirst = (char) ('A' + (longitude / 10));
    char lonSecond = (char) ('0' + longitude % 10);
    char lonThird = (char) ('A' + (longitude % 1) * 24);

    double latitude = latitudeIn + 90;
    char latFirst = (char) ('A' + (latitude / 10));
    char latSecond = (char) ('0' + latitude % 10);
    char latThird = (char) ('A' + (latitude % 1) * 24);

    StringBuilder sb = new StringBuilder();
    sb.append(lonFirst);
    sb.append(latFirst);
    sb.append(lonSecond);
    sb.append(latSecond);
    sb.append(("" + lonThird).toLowerCase());
    sb.append(("" + latThird).toLowerCase());

    return sb.toString();
  }

  /**
   * @param maidenheadIn locator string to be converted
   * @return latitude
   */
  public static double extractLat(final String maidenheadIn) {
    String maidenhead = maidenheadIn.toUpperCase();
    double latitude = -90 + 10 * (maidenhead.charAt(1) - 'A')
        + (maidenhead.charAt(3) - '0') + 2.5 / 60
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
  public final double getLatitude() {
    return latitude;
  }

  /**
   * @return longitude
   */
  public final double getLongitude() {
    return longitude;
  }

  /**
   * @param latitudeIn north/south component of location
   */
  public final  void setLatitude(final double latitudeIn) {
    this.latitude = latitudeIn;
  }

  /**
   * @param longitudeIn east/west component of location
   */
  public final void setLongitude(final double longitudeIn) {
    this.longitude = longitudeIn;
  }
}
