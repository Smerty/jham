package org.smerty.jham;

public class Latitude {

  /**
   * latitude in degrees, positive for northern hemisphere, negative for
   * southern hemisphere.
   */
  private double decimalDegrees;

  /** no argument constructor.
   *
   */
  public Latitude() {
  }

  /**
   * @param decimalDegreesIn  value of latitude
   */
  public Latitude(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
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
    hash = hash * 17 + ((Double) this.decimalDegrees).hashCode();
    return hash;
  }

  /**
   * @return decimal value of latitude
   */
  public final double getDecimalDegrees() {
    return decimalDegrees;
  }

  /**
   * @param decimalDegreesIn decimal value of latitude
   */
  public final void setDecimalDegrees(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
  }
}
