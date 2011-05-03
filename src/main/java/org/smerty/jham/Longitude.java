package org.smerty.jham;

/**
 * Longitude.
 *
 * @author Paul Picazo <ppicazo@gmail.com>
 *
 */
public class Longitude {

  /**
   * longitude in degrees, positive for eastern hemisphere, negative for western
   * hemisphere.
   */
  private Angle angle;

  /**
   * no argument constructor.
   *
   */
  public Longitude() {
  }

  /**
   * @param angleIn
   *          value of longitude
   */
  public Longitude(final Angle angleIn) {
    this.angle = angleIn;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Longitude) {
      return ((Longitude) obj).hashCode() == this.hashCode();
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
   * @return decimal value of longitude
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
