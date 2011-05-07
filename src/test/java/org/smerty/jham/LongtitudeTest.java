package org.smerty.jham;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.smerty.jham.Passert.assertMaxError;

import org.junit.Test;

public class LongtitudeTest {

  private static final double SIMPLE_LON_VALUE = 132.5;
  private static final double DIFFERENT_LON_VALUE = 12.5;

  @Test
  public void testIt() {
    Longitude lon = Longitude.fromDegrees(SIMPLE_LON_VALUE);
    assertMaxError(SIMPLE_LON_VALUE, lon.toDegrees(),
        Passert.NO_ERROR);
  }

  @Test
  public void testEquals() {
    Longitude lon = Longitude.fromDegrees(SIMPLE_LON_VALUE);
    Longitude lon2 = Longitude.fromDegrees(SIMPLE_LON_VALUE);
    Longitude lon3 = Longitude.fromDegrees(DIFFERENT_LON_VALUE);
    Latitude lat = Latitude.fromDegrees(SIMPLE_LON_VALUE);
    Angle angle = Angle.fromDegrees(SIMPLE_LON_VALUE);
    Longitude lon4 = new Longitude();

    assertTrue(lon.equals(lon));
    assertTrue(lon.equals(lon2));
    assertTrue(lon2.equals(lon));
    assertFalse(lon.equals(lon3));
    assertFalse(lon.equals(null));
    assertFalse(lon.equals(new String("hello")));
    assertFalse(lon.equals(lat));
    assertFalse(lat.equals(lon));
    assertFalse(angle.equals(lon));
    assertFalse(lon.equals(angle));
    assertFalse(lon.equals(lon4));
  }

}
