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
    Latitude lat = new Latitude(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    assertMaxError(SIMPLE_LAT_VALUE, lat.getDecimalDegrees(), Passert.SMALL_ERROR);
  }

  @Test
  public void testItDeep() {
    Latitude lat = new Latitude(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    Latitude lat2 = new Latitude(Angle.fromDegrees(lat.getDecimalDegrees()));
    assertMaxError(lat.getDecimalDegrees(), lat2.getDecimalDegrees(), Passert.NO_ERROR);
  }

  @Test
  public void testGetterSetter() {
    Latitude lat = new Latitude();
    lat.setLatitudeAngle(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    assertMaxError(SIMPLE_LAT_VALUE, lat.getDecimalDegrees(), Passert.SMALL_ERROR);
  }

  @Test
  public void testEquals() {
    Latitude lat = new Latitude(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    Latitude lat2 = new Latitude(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    Latitude lat3 = new Latitude(Angle.fromDegrees(DIFFERENT_LAT_VALUE));
    assertTrue(lat.equals(lat));
    assertTrue(lat.equals(lat2));
    assertTrue(lat2.equals(lat));
    assertFalse(lat.equals(lat3));
    assertFalse(lat.equals(null));
    assertFalse(lat.equals(new String("hello")));
  }

  @Test
  public void testAngleGetSet() {
    Latitude lat = new Latitude();
    lat.setLatitudeAngle(Angle.fromDegrees(SIMPLE_LAT_VALUE));
    assertMaxError(SIMPLE_LAT_VALUE, lat.getLatitudeAngle().toDegrees(), Passert.NO_ERROR);
  }

}
