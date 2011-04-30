package org.smerty.jham;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LatitudeTest {

  private static final double SIMPLE_LAT_VALUE = 32.5;
  private static final double DIFFERENT_LAT_VALUE = 2.5;

  @Test
  public void testIt() {
    Latitude lat = new Latitude(SIMPLE_LAT_VALUE);
    assertEquals(lat.getDecimalDegrees(), SIMPLE_LAT_VALUE, 0);
  }

  @Test
  public void testEquals() {
    Latitude lat = new Latitude(SIMPLE_LAT_VALUE);
    Latitude lat2 = new Latitude(SIMPLE_LAT_VALUE);
    Latitude lat3 = new Latitude(DIFFERENT_LAT_VALUE);
    assertTrue(lat.equals(lat));
    assertTrue(lat.equals(lat2));
    assertTrue(lat2.equals(lat));
    assertFalse(lat.equals(lat3));
    assertFalse(lat.equals(null));
    assertFalse(lat.equals(new String("hello")));
  }

}
