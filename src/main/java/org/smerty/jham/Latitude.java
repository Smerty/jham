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
  private Angle latitudeAngle;

  /** no argument constructor.
   *
   */
  public Latitude() {
  }

  /**
   * @param angleIn angle of latitude
   */
  public Latitude(final Angle angleIn) {
    this.latitudeAngle = angleIn;
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
    hash = hash * 17 + this.latitudeAngle.hashCode();
    return hash;
  }

  /**
   * @return decimal value of latitude
   */
  public final double getDecimalDegrees() {
    return this.latitudeAngle.toDegrees();
  }

  /**
   * @return angle
   */
  public Angle getLatitudeAngle() {
    return latitudeAngle;
  }

  /**
   * @param latitudeAngleIn input
   */
  public void setLatitudeAngle(Angle latitudeAngleIn) {
    this.latitudeAngle = latitudeAngleIn;
  }
}
