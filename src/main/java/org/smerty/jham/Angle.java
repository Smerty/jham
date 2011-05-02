package org.smerty.jham;


public class Angle {

  private double radians;
  private Integer precision;

  /** no argument constructor.
   *
   */
  public Angle() {
  }

  public Angle(double radiansIn) {
    this.radians = radiansIn;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj instanceof Angle) {
      return ((Angle) obj).hashCode() == this.hashCode();
    }
    return false;
  }

  @Override
  public final int hashCode() {
    int hash = 1;
    hash = hash * 17 + ((Double) this.radians).hashCode();
    return hash;
  }

  public static Angle fromDegrees(double degrees) {
    return new Angle(degreesToRadians(degrees));
  }

  /**
   * @param radiansIn the radians to set
   */
  public void setRadians(final double radiansIn) {
    this.radians = radiansIn;
  }

  /**
   * @return the radians
   */
  public double getRadians() {
    return radians;
  }

  public double toDegrees() {
    return toDegrees(this);
  }

  public Integer getPrecision() {
    return precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public static double toDegrees(Angle angle) {
    return radiansToDegrees(angle.radians);
  }

  public static double radiansToDegrees(double radians) {
    return radians * 180 / Math.PI;
  }

  public static double degreesToRadians(double degrees) {
    return degrees / 180 * Math.PI;
  }
}
