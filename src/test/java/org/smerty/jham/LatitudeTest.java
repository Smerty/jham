package org.smerty.jham;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.smerty.jham.Passert.assertMaxError;

import org.junit.Test;

public class LatitudeTest {

  private static final double SIMPLE_LAT_VALUE = 32.533;
  private static final double DIFFERENT_LAT_VALUE = 2.571;

  @Test
  public void testIt() {
    Latitude lat = Latitude.fromDegrees(SIMPLE_LAT_VALUE);
    assertMaxError(SIMPLE_LAT_VALUE, lat.toDegrees(),
        Passert.NO_ERROR);
  }

  @Test
  public void testItDeep() {
    Latitude lat = Latitude.fromDegrees(SIMPLE_LAT_VALUE);
    Latitude lat2 = Latitude.fromDegrees(lat.toDegrees());
    assertMaxError(lat.toDegrees(), lat2.toDegrees(),
        Passert.NO_ERROR);
  }

  @Test
  public void testEquals() {
    Latitude lat = Latitude.fromDegrees(SIMPLE_LAT_VALUE);
    Latitude lat2 = Latitude.fromDegrees(SIMPLE_LAT_VALUE);
    Latitude lat3 = Latitude.fromDegrees(DIFFERENT_LAT_VALUE);
    Longitude lon = Longitude.fromDegrees(SIMPLE_LAT_VALUE);
    Latitude lat4 = new Latitude();
    Angle angle = Angle.fromDegrees(SIMPLE_LAT_VALUE);
    assertTrue(lat.equals(lat));
    assertTrue(lat.equals(lat2));
    assertTrue(lat2.equals(lat));
    assertFalse(lat.equals(lat3));
    assertFalse(lat.equals(null));
    assertFalse(lat.equals(new String("hello")));
    assertFalse(lat.equals(lon));
    assertFalse(lon.equals(lat));
    assertFalse(angle.equals(lat));
    assertFalse(lat.equals(angle));
    assertFalse(lat.equals(lat4));
  }

}
