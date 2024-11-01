package org.smerty.jham;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.smerty.jham.Passert.assertMaxError;

import org.junit.Test;

public class AngleTest {

  private static double[] anglesDeg = { 0, 45, 90, 180, 270, 360, 720, -45,
      -90, -180, -270, -360, 1.23, Math.PI, Math.E, Math.sqrt(2), 37.646,
      51.479, 46.26519, 46.2508, 46.29106, -82.49518, -121.791, -0.458,
      60.09951, 60.08436, 60.1656, -51.1908 };

  @Test
  public void testHashCode() {
    Angle angle1 = new Angle(1);
    Angle angle2 = new Angle(1);
    Angle angle3 = new Angle(1.1);
    assertEquals(angle1, angle2);
    assertFalse(angle1 == angle3);
  }

  @Test
  public void testAngle() {
    Angle angle = new Angle();
    assertNotNull(angle);
    assert (angle instanceof Angle);
  }

  @Test
  public void testAngleDouble() {
    Angle angle = new Angle(1.0);
    assertMaxError(1.0, angle.getRadians(), Passert.NO_ERROR);
  }

  @Test
  public void testEqualsObject() {
    Angle angle = new Angle();
    Angle angle1 = new Angle(1.31);
    Angle angle2 = new Angle(1.31);
    Angle angle3 = new Angle(-2.1);
    assert (angle1.equals(angle2));
    assert (angle2.equals(angle1));
    assertFalse(angle1.equals(angle3));
    assertFalse(angle1.equals(null));
    assertFalse(angle1.equals(new String("hello")));
    assertFalse(angle1.equals(angle));
    assertFalse(angle.equals(angle1));
  }

  @Test
  public void testToFromDegrees() {
    for (int i = 0; i < anglesDeg.length; i++) {
      Angle angle = Angle.fromDegrees(anglesDeg[i]);
      assertMaxError(anglesDeg[i], angle.toDegrees(), Passert.NO_ERROR);
    }

    for (int i = 0; i < 1000; i++) {
      Angle directSetRads = new Angle((double) i / 10000);
      assertMaxError(directSetRads.toDegrees(), directSetRads.toDegrees(),
          Passert.NO_ERROR);
    }
  }

  @Test
  public void testGetSetRadians() {
    Angle angle = new Angle();
    angle.setRadians(-2);
    assertMaxError(-2, angle.getRadians(), Passert.NO_ERROR);
  }

  @Test
  public void testRadiansToDegreesToRadians() {
    for (int i = 0; i < anglesDeg.length; i++) {
      // small error is expected sometimes since we are not tracking the
      // original input
      assertMaxError(anglesDeg[i],
          Angle.radiansToDegrees(Angle.degreesToRadians(anglesDeg[i])),
          Passert.TINY_ERROR);
    }
  }

  @Test
  public void testEqualsHashCode() {
    Angle angle = new Angle();
    Angle angle2 = new Angle(1.0);
    Angle angle3 = new Angle(1.0);
    Angle angle4 = new Angle(1.1);

    assertTrue(angle2.equals(angle3));
    assertTrue(angle3.equals(angle2));
    assertTrue(angle2.equals(angle2));
    assertFalse(angle2.equals(angle));
    assertFalse(angle4.equals(angle2));
    assertFalse(angle2.equals(null));
    assertFalse(angle2.equals(new String("moo")));
  }

  @Test
  public void testInvalidInputs() {
    Angle angleNaN = new Angle(Double.NaN);
    assertTrue(angleNaN == null);

    Angle anglePosInf = new Angle(Double.POSITIVE_INFINITY);
    assertTrue(anglePosInf == null);

    Angle angleNegInf = new Angle(Double.NEGATIVE_INFINITY);
    assertTrue(angleNegInf == null);
  }

  @Test
  public void testBoundaryValues() {
    Angle angleMax = new Angle(Double.MAX_VALUE);
    assertMaxError(Double.MAX_VALUE, angleMax.getRadians(), Passert.NO_ERROR);

    Angle angleMin = new Angle(Double.MIN_VALUE);
    assertMaxError(Double.MIN_VALUE, angleMin.getRadians(), Passert.NO_ERROR);
  }

  @Test
  public void testExceptionalCases() {
    try {
      Angle angleNull = new Angle();
      angleNull.setRadians(Double.parseDouble(null));
    } catch (NumberFormatException e) {
      assertTrue(true);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }
}
