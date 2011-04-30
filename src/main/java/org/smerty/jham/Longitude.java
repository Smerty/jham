package org.smerty.jham;

public class Longitude {

  /**
   * longitude in degrees, positive for eastern hemisphere, negative for western
   * hemisphere.
   */
  private double decimalDegrees;

  /** no argument constructor.
   *
   */
  public Longitude() {
  }

  /**
   * @param decimalDegreesIn value of longitude
   */
  public Longitude(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
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
    hash = hash * 17 + ((Double) this.decimalDegrees).hashCode();
    return hash;
  }

  /**
   * @return decimal value of longitude
   */
  public final double getDecimalDegrees() {
    return decimalDegrees;
  }

  /**
   * @param decimalDegreesIn decimal value of longitude
   */
  public final void setDecimalDegrees(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
  }
}
