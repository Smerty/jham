package org.smerty.jham;

/**
 * Latitude.
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Latitude {

  /**
   * latitude in degrees, positive for northern hemisphere, negative for
   * southern hemisphere.
   */
  private Angle angle;

  /**
   * no argument constructor.
   *
   */
  public Latitude() {
  }

  /**
   * @param angleIn
   *          angle of latitude
   */
  public Latitude(final Angle angleIn) {
    this.angle = angleIn;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Latitude) {
      return ((Latitude) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  @Override
  public final int hashCode() {
    int hash = 1;
    hash = hash * 17 + this.angle.hashCode();
    return hash;
  }

  /**
   * @return decimal value of latitude
   */
  public final double getDecimalDegrees() {
    return this.angle.toDegrees();
  }

  /**
   * @return angle
   */
  public Angle getAngle() {
    return angle;
  }

  /**
   * @param angleIn
   *          input
   */
  public void setAngle(Angle angleIn) {
    this.angle = angleIn;
  }
}
