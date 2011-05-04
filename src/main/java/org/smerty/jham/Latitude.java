package org.smerty.jham;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Latitude.
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Latitude extends Angle {

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Latitude) {
      return ((Latitude) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  /**
   * @param degrees input
   * @return latitude object
   */
  public static Latitude fromDegrees(final double degrees) {
    Latitude latitude = new Latitude();
    latitude.radians = Angle.degreesToRadians(new BigDecimal(degrees),
        MathContext.DECIMAL128);
    return latitude;
  }

}
