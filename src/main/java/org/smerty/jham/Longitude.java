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
  private Angle longitudeAngle;

  /** no argument constructor.
   *
   */
  public Longitude() {
  }

  /**
   * @param angleIn value of longitude
   */
  public Longitude(final Angle angleIn) {
    this.longitudeAngle = angleIn;
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
    hash = hash * 17 + this.longitudeAngle.hashCode();
    return hash;
  }

  /**
   * @return decimal value of longitude
   */
  public final double getDecimalDegrees() {
    return this.longitudeAngle.toDegrees();
  }

  /**
   * @return angle
   */
  public Angle getLongitudeAngle() {
    return longitudeAngle;
  }

  /**
   * @param longitudeAngleIn input
   */
  public void setLongitudeAngle(Angle longitudeAngleIn) {
    this.longitudeAngle = longitudeAngleIn;
  }
}
