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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Longitude)) {
      return false;
    }
    return ((Longitude) obj).hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    final int prime = 29;
    int result = 1;
    result = prime * result + ((radians == null) ? 0 : radians.hashCode());
    return result;
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
