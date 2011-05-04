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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Latitude)) {
      return false;
    }
    return ((Latitude) obj).hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((radians == null) ? 0 : radians.hashCode());
    return result;
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
