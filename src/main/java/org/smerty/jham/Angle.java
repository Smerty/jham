package org.smerty.jham;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Allow for easy use of angles without messy conversions to and from degrees
 * and radians.
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Angle {

  /**
   * pi.
   */
  private static final BigDecimal BIG_PI = new BigDecimal(Math.PI);

  /**
   * representation of the angle in radians.
   */
  private BigDecimal radians;

  /**
   * no argument constructor.
   *
   */
  public Angle() {
  }

  /**
   * @param radiansIn
   *          angle in radians
   */
  public Angle(final double radiansIn) {
    this.radians = new BigDecimal(radiansIn);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Angle) {
      return ((Angle) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  @Override
  public final int hashCode() {
    int hash = 1;
    hash = hash * 17 + this.radians.hashCode();
    return hash;
  }

  /**
   * @param degrees
   *          angle in degrees
   * @return angle
   */
  public static Angle fromDegrees(final double degrees) {
    Angle angle = new Angle();
    angle.radians = degreesToRadians(new BigDecimal(degrees),
        MathContext.DECIMAL128);
    return angle;
  }

  /**
   * @param radiansIn
   *          the radians to set
   */
  public void setRadians(final double radiansIn) {
    this.radians = new BigDecimal(radiansIn);
  }

  /**
   * @return the radians
   */
  public double getRadians() {
    return radians.doubleValue();
  }

  /**
   * @return degrees
   */
  public double toDegrees() {
    return radiansToDegrees(this.radians, MathContext.DECIMAL128).doubleValue();
  }

  /**
   * @param radians
   *          input
   * @return degrees
   */
  public static double radiansToDegrees(final double radians) {
    return radiansToDegrees(new BigDecimal(radians), MathContext.DECIMAL128)
        .doubleValue();
  }

  /**
   * @param degrees
   *          input
   * @return radians
   */
  public static double degreesToRadians(final double degrees) {
    return degreesToRadians(new BigDecimal(degrees), MathContext.DECIMAL128)
        .doubleValue();
  }

  /**
   * @param radians
   *          input
   * @param mc
   *          MathContext for divsion
   * @return degrees
   */
  private static BigDecimal radiansToDegrees(final BigDecimal radians,
      final MathContext mc) {
    return radians.multiply(new BigDecimal(180)).divide(BIG_PI, mc);
  }

  /**
   * @param degrees
   *          input
   * @param mc
   *          MathContext for divsion
   * @return radians
   */
  private static BigDecimal degreesToRadians(final BigDecimal degrees,
      final MathContext mc) {
    return degrees.divide(new BigDecimal(180), mc).multiply(BIG_PI);
  }
}
