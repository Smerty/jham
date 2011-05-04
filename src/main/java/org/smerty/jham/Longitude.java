package org.smerty.jham;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Longitude.
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Longitude extends Angle {

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Longitude) {
      return ((Longitude) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  /**
   * @param degrees input
   * @return longitude object
   */
  public static Longitude fromDegrees(final double degrees) {
    Longitude longitude = new Longitude();
    longitude.radians = Angle.degreesToRadians(new BigDecimal(degrees),
        MathContext.DECIMAL128);
    return longitude;
  }


}
