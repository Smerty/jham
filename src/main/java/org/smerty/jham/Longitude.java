package org.smerty.jham;


public class Longitude {

  /**
   * longitude in degrees, positive for eastern hemisphere, negative for western
   * hemisphere.
   */
  //private double decimalDegrees;

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

  public Angle getLongitudeAngle() {
    return longitudeAngle;
  }

  public void setLongitudeAngle(Angle longitudeAngleIn) {
    this.longitudeAngle = longitudeAngleIn;
  }
}
