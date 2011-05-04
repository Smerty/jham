package org.smerty.jham;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.smerty.jham.Passert.assertMaxError;

import org.junit.Test;

public class LocationTest {

  private static String[] locators = { "CM97cp", "IO91sl", "MN06bg", "MN06bg", "MN06bg", "GA47jm" };
  private static double[] latitudes = { 37.646, 51.479, 46.26519, 46.2508, 46.29106, -82.49518 };
  private static double[] longitudes = { -121.791, -0.458, 60.09951, 60.08436,
      60.1656, -51.1908 };

  private static double[] distances = { 13896, 4345, 0 };
  private static double[] bearings = { 171, 73, Double.NaN, Double.NaN, 301, 295 };

  private static final double STATUTEMILES_PER_KILOMETER = 0.6214;
  private static final double NAUTICALMILES_PER_KILOMETER = 0.54;

  @Test
  public void testLocationdoubles() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(latitudes[n], longitudes[n]);
      assertMaxError(latitudes[n], loc.getLatitude().toDegrees(), Passert.TINY_ERROR);
      assertMaxError(longitudes[n], loc.getLongitude().toDegrees(), Passert.TINY_ERROR);
    }
  }

  @Test
  public void testLocationString() {
    for (int n = 0; n < locators.length; n++) {
      Location loc = new Location(locators[n]);
      assertMaxError(latitudes[n], loc.getLatitude().toDegrees(), Passert.SMALL_ERROR);
      assertMaxError(longitudes[n], loc.getLongitude().toDegrees(), Passert.SMALL_ERROR);
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
  public void testToMaidenheaddoubles() {
    for (int n = 0; n < locators.length; n++) {
      assertEquals(locators[n],
          Location.toMaidenhead(latitudes[n], longitudes[n]));
    }
  }

  @Test
  public void testExtractLat() {
    for (int n = 0; n < locators.length; n++) {
      assertMaxError(latitudes[n], Location.extractLat(locators[n]).toDegrees(), Passert.SMALL_ERROR);
    }
  }

  @Test
  public void testExtractLon() {
    for (int n = 0; n < locators.length; n++) {
      assertMaxError(longitudes[n], Location.extractLon(locators[n]).toDegrees(), Passert.SMALL_ERROR);
    }
  }

  @Test
  public void testGetSetLat() {
    Location loc = new Location();
    loc.setLatitude(Latitude.fromDegrees(latitudes[0]));
    assertMaxError(latitudes[0], loc.getLatitude().toDegrees(), Passert.NO_ERROR);
  }

  @Test
  public void testGetSetLon() {
    Location loc = new Location();
    loc.setLongitude(Longitude.fromDegrees(longitudes[0]));
    assertMaxError(longitudes[0], loc.getLongitude().toDegrees(), Passert.NO_ERROR);
  }

  @Test
  public void testGetDistanceKilometers() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceKm(loc2);
      assertMaxError(distances[n], distance, Passert.SMALL_ERROR);
    }
  }

  @Test
  public void testGetDistanceMiles() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceMi(loc2);
      assertMaxError(distances[n] * STATUTEMILES_PER_KILOMETER, distance, Passert.SMALL_ERROR);
    }
  }

  @Test
  public void testGetDistanceNauticalMiles() {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double distance = loc1.getDistanceNm(loc2);
      assertMaxError(distances[n] * NAUTICALMILES_PER_KILOMETER, distance, Passert.SMALL_ERROR);
    }
  }

  @Test
  public void testGetBearing() throws Exception {
    for (int n = 0; n < locators.length / 2; n++) {
      Location loc1 = new Location(locators[n]);
      Location loc2 = new Location(locators[locators.length - 1 - n]);
      double bearing = loc1.getBearing(loc2);
      assertMaxError(bearings[n], bearing, Passert.SMALL_ERROR);
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
