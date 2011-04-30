package org.smerty.jham;

public class Longitude {

  /**
   * longitude in degrees, positive for eastern hemisphere, negative for western
   * hemisphere.
   */
  private double decimalDegrees;

  public Longitude() {
  }

  /**
   * @param decimalDegreesIn value of longitude
   */
  public Longitude(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Longitude) {
      return ((Longitude) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 1;
    hash = hash * 17 + ((Double)this.decimalDegrees).hashCode();
    return hash;
  }

  /**
   * @return decimal value of longitude
   */
  public double getDecimalDegrees() {
    return decimalDegrees;
  }

  /**
   * @param decimalDegreesIn decimal value of longitude
   */
  public void setDecimalDegrees(double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
  }
}