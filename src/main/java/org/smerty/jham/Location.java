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
   * Average earth radius in kilometers, IUGG definition.
   */
  private static final double AVG_EARTH_RADIUS_KM = 6371.009;
  /**
   * Average earth radius in statute miles, IUGG definition.
   */
  private static final double AVG_EARTH_RADIUS_SM = 3958.761;
  /**
   * Average earth radius in nautical miles, IUGG definition.
   */
  private static final double AVG_EARTH_RADIUS_NM = 3440.069;

  /**
   * latitude of location.
   */
  private Latitude latitude;
  /**
   * longitude of location.
   */
  private Longitude longitude;

  /** No argument constructor.
   *
   */
  public Location() {
    this.latitude = new Latitude();
    this.longitude = new Longitude();
  }

  /**
   * @param latitudeIn initial latitude
   * @param longitudeIn initial longitude
   */
  public Location(final Latitude latitudeIn, final Longitude longitudeIn) {
    this.latitude = latitudeIn;
    this.longitude = longitudeIn;
  }

  /**
   * @param latitudeIn initial latitude
   * @param longitudeIn initial longitude
   */
  public Location(final double latitudeIn, final double longitudeIn) {
    this.latitude = new Latitude(latitudeIn);
    this.longitude = new Longitude(longitudeIn);
  }

  /**
   * @param maidenhead used construct location from maidenhead locator string
   */
  public Location(final String maidenhead) {
    this.latitude = extractLat(maidenhead);
    this.longitude = extractLon(maidenhead);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Location) {
      return ((Location) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  @Override
  public final int hashCode() {
    int hash = 1;
    hash = hash * 17 + this.latitude.hashCode();
    hash = hash * 31 + this.longitude.hashCode();
    return hash;
  }

  /**
   * @return maidenhead locator string
   */
  public final String toMaidenhead() {
    return toMaidenhead(this.latitude.getDecimalDegrees(),
        this.longitude.getDecimalDegrees());
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
  public static Latitude extractLat(final String maidenheadIn) {
    String maidenhead = maidenheadIn.toUpperCase();
    double latitude = -90 + 10 * (maidenhead.charAt(1) - 'A')
        + (maidenhead.charAt(3) - '0') + 2.5 / 60
        * (maidenhead.charAt(5) - 'A') + 2.5 / 60 / 2;
    return new Latitude(latitude);
  }

  /**
   * @param maidenheadIn locator string to be converted
   * @return longitude
   */
  public static Longitude extractLon(final String maidenheadIn) {
    String maidenhead = maidenheadIn.toUpperCase();
    double longitude = -180 + 20 * (maidenhead.charAt(0) - 'A') + 2
        * (maidenhead.charAt(2) - '0') + 5.0 / 60
        * (maidenhead.charAt(4) - 'A') + 5.0 / 60 / 2;
    return new Longitude(longitude);
  }

  /**
   * @return latitude
   */
  public final Latitude getLatitude() {
    return latitude;
  }

  /**
   * @return longitude
   */
  public final Longitude getLongitude() {
    return longitude;
  }

  /**
   * @param latitudeIn north/south component of location
   */
  public final  void setLatitude(final Latitude latitudeIn) {
    this.latitude = latitudeIn;
  }

  /**
   * @param longitudeIn east/west component of location
   */
  public final void setLongitude(final Longitude longitudeIn) {
    this.longitude = longitudeIn;
  }

  /**
   * @param loc2 second location
   * @return great circle distance in miles
   */
  public final double getDistanceMi(final Location loc2) {
    return getDistanceMi(this, loc2);
  }

  /**
   * @param loc2 second location
   * @return great circle distance in kilometers
   */
  public final double getDistanceKm(final Location loc2) {
    return getDistanceKm(this, loc2);
  }

  /**
   * @param loc2 second location
   * @return great circle distance in nautical miles
   */
  public final double getDistanceNm(final Location loc2) {
    return getDistanceNm(this, loc2);
  }

  /**
   * @param loc1 first location
   * @param loc2 second location
   * @return great circle distance in miles
   */
  public static double getDistanceMi(final Location loc1, final Location loc2) {
    return getDistance(loc1, loc2, AVG_EARTH_RADIUS_SM);
  }

  /**
   * @param loc1 first location
   * @param loc2 second location
   * @return great circle distance in kilometers
   */
  private static double getDistanceKm(final Location loc1, final Location loc2) {
    return getDistance(loc1, loc2, AVG_EARTH_RADIUS_KM);
  }

  /**
   * @param loc1 first location
   * @param loc2 second location
   * @return great circle distance in nautical miles
   */
  private static double getDistanceNm(final Location loc1, final Location loc2) {
    return getDistance(loc1, loc2, AVG_EARTH_RADIUS_NM);
  }

  /**
   * @param loc1 first location
   * @param loc2 second location
   * @param radius radius of the earth in the units desired for result
   * @return great circle distance between the two locations, result units same of the radius units
   */
  private static double getDistance(final Location loc1, final Location loc2,
      final double radius) {
    if (loc1.equals(loc2)) {
      return 0;
    }
    return Math.acos(Math.sin(loc1.getLatitude().getDecimalDegrees() * Math.PI / 180)
        * Math.sin(loc2.getLatitude().getDecimalDegrees() * Math.PI / 180)
        + Math.cos(loc1.latitude.getDecimalDegrees() * Math.PI / 180)
        * Math.cos(loc2.getLatitude().getDecimalDegrees() * Math.PI / 180)
        * Math.cos(loc2.getLongitude().getDecimalDegrees() * Math.PI / 180 - loc1.getLongitude().getDecimalDegrees()
            * Math.PI / 180))
        * radius;
  }
}
