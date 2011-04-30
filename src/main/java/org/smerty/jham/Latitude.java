package org.smerty.jham;

public class Latitude {

  /**
   * latitude in degrees, positive for northern hemisphere, negative for
   * southern hemisphere.
   */
  private double decimalDegrees;

  public Latitude() {
  }

  /**
   * @param decimalDegreesIn  value of latitude
   */
  public Latitude(final double decimalDegreesIn) {
    this.decimalDegrees = decimalDegreesIn;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Latitude) {
      return ((Latitude) obj).hashCode() == this.hashCode();
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
   * @return decimal value of latitude
   */
  public double getDecimalDegrees() {
    return decimalDegrees;
  }

  /**
   * @param latitudeIn decimal value of latitude
   */
  public void setDecimalDegrees(final double latitudeIn) {
    this.decimalDegrees = latitudeIn;
  }
}