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
    Longitude lon = new Longitude(Angle.fromDegrees(SIMPLE_LON_VALUE));
    assertMaxError(SIMPLE_LON_VALUE, lon.getDecimalDegrees(), Passert.SMALL_ERROR);
  }

  @Test
  public void testGetterSetter() {
    Longitude lon = new Longitude();
    lon.setLongitudeAngle(Angle.fromDegrees(SIMPLE_LON_VALUE));
    assertMaxError(SIMPLE_LON_VALUE, lon.getDecimalDegrees(), Passert.NO_ERROR);
  }

  @Test
  public void testEquals() {
    Longitude lon = new Longitude(Angle.fromDegrees(SIMPLE_LON_VALUE));
    Longitude lon2 = new Longitude(Angle.fromDegrees(SIMPLE_LON_VALUE));
    Longitude lon3 = new Longitude(Angle.fromDegrees(DIFFERENT_LON_VALUE));
    assertTrue(lon.equals(lon));
    assertTrue(lon.equals(lon2));
    assertTrue(lon2.equals(lon));
    assertFalse(lon.equals(lon3));
    assertFalse(lon.equals(null));
    assertFalse(lon.equals(new String("hello")));
  }

  @Test
  public void testAngleGetSet() {
    Longitude lon = new Longitude();
    lon.setLongitudeAngle(Angle.fromDegrees(SIMPLE_LON_VALUE));
    assertMaxError(SIMPLE_LON_VALUE, lon.getLongitudeAngle().toDegrees(), Passert.NO_ERROR);
  }

}
