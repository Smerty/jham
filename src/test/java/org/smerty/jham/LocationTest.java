package org.smerty.jham;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LocationTest {

  private static String[] locators = { "CM97cp", "IO91sl", "MN06bg", "MN06bg", "MN06bg", "GA47jm" };
  private static double[] latitudes = { 37.646, 51.479, 46.26519, 46.2508, 46.29106, -82.49518 };
  private static double[] longitudes = { -121.791, -0.458, 60.09951, 60.08436, 60.1656, -51.1908 };

  private static double[] distances = { 13896, 4345, 0 };
  private static double[] bearings = { 171, 73, Double.NaN, Double.NaN, 301, 295 };

  private static final double STATUTEMILES_PER_KILOMETER = 0.6214;
  private static final double NAUTICALMILES_PER_KILOMETER = 0.54;

  private static final double TOLERATED_PERCENT_ERROR = 0.005;
  private static final double FUDGE_FACTOR = 0.05; // we really should verify
                                                   // the point is inside the
                                                   // bounded box of the grid
                                                   // square

  public static double percentError(double result, double actual) {
    if (result == 0 && actual == 0) {
      return 0;
    }
    if (((Double) result).isNaN() && ((Double) actual).isNaN()) {
      return 0;
    }
    return Math.abs(result - actual) / actual;
  }

  @Test
  public void testLocationDoubleDouble() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(latitudes[n], longitudes[n]);
      assertEquals(latitudes[n], loc.getLatitude().getDecimalDegrees(), 0);
      assertEquals(longitudes[n], loc.getLongitude().getDecimalDegrees(), 0);
    }
  }

  @Test
  public void testLocationString() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(locators[n]);
      assertEquals(latitudes[n], loc.getLatitude().getDecimalDegrees(),
          FUDGE_FACTOR);
      assertEquals(longitudes[n], loc.getLongitude().getDecimalDegrees(),
          FUDGE_FACTOR);
    }
  }

  @Test
  public void testInverses() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(locators[n]);
      Location loc2 = new Location(loc.getLatitude(), loc.getLongitude());
      assertEquals(locators[n], loc2.toMaidenhead());
      assertEquals(loc.getLatitude(), loc2.getLatitude());
      assertEquals(loc.getLongitude(), loc2.getLongitude());
    }
  }

  @Test
  public void testToMaidenhead() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(latitudes[n], longitudes[n]);
      assertEquals(locators[n], loc.toMaidenhead());
    }
  }

  @Test
  public void testToMaidenheadDoubleDouble() {
    for (int n = 0; n < locators.length; n++) {
      assertEquals(locators[n],
          Location.toMaidenhead(latitudes[n], longitudes[n]));
    }
  }

  @Test
  public void testExtractLat() {
    for (int n = 0; n < locators.length; n++) {
      assertEquals(latitudes[n], Location.extractLat(locators[n])
          .getDecimalDegrees(), FUDGE_FACTOR);
    }
  }

  @Test
  public void testExtractLon() {
    for (int n = 0; n < locators.length; n++) {
      assertEquals(longitudes[n], Location.extractLon(locators[n])
          .getDecimalDegrees(), FUDGE_FACTOR);
    }
  }

  @Test
  public void testGetSetLat() {
    Location loc = new Location();
    loc.setLatitude(new Latitude(latitudes[0]));
    assertEquals(latitudes[0], loc.getLatitude().getDecimalDegrees(), 0);
  }

  @Test
  public void testGetSetLon() {
    Location loc = new Location();
    loc.setLongitude(new Longitude(longitudes[0]));
    assertEquals(longitudes[0], loc.getLongitude().getDecimalDegrees(), 0);
  }

  @Test
  public void testGetDistanceKilometers() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceKm(loc2);
      assertTrue((percentError(distance, distances[n]) < TOLERATED_PERCENT_ERROR));
    }
  }

  @Test
  public void testGetDistanceMiles() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceMi(loc2);
      assertTrue((percentError(distance, distances[n]
          * STATUTEMILES_PER_KILOMETER) < TOLERATED_PERCENT_ERROR));
    }
  }

  @Test
  public void testGetDistanceNauticalMiles() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceNm(loc2);
      assertTrue((percentError(distance, distances[n]
          * NAUTICALMILES_PER_KILOMETER) < TOLERATED_PERCENT_ERROR));
    }
  }

  @Test
  public void testGetBearing() throws Exception {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double bearing = loc1.getBearing(loc2);
      assertTrue((percentError(bearing, bearings[n]) < TOLERATED_PERCENT_ERROR));
    }
  }

  @Test
  public void testEquals() {
    Location loc = new Location(locators[0]);
    Location loc2 = new Location(locators[0]);
    Location loc3 = new Location(locators[1]);
    assertFalse(loc.equals(new String("hello")));
    assertFalse(loc.equals(loc3));
    assertTrue(loc.equals(loc));
    assertTrue(loc.equals(loc2));
    assertTrue(loc2.equals(loc));
    assertFalse(loc.equals(null));
  }
}
