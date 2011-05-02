package org.smerty.jham;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.MathContext;

public class Passert {

  public static final double NO_ERROR = 0;
  public static final double TINY_ERROR = 0.000125;
  public static final double SMALL_ERROR = 0.005;

  public static void assertMaxError(double expected, double actual, double maxError) {
    double pe = percentError(expected, actual);
    if (pe > maxError) {
      fail("expected: " + expected + " , actual: " + actual + " %error: " + pe);
    }
    //System.out.println("%error: " + pe);
    //System.out.println(pe);
  }

  public static double percentError(double expected, double actual) {
    if (((Double) expected).isNaN() && ((Double) actual).isNaN()) {
      return 0;
    }
    if (expected == 0 && actual == 0) {
      return 0;
    }
    return Math.abs(actual - expected) / expected;
  }

  public static void assertMaxError(BigDecimal expected, BigDecimal actual, BigDecimal maxError) {
    BigDecimal pe = percentError(expected, actual);
    if (pe.compareTo(maxError) > 0) {
      fail("expected: " + expected + " , actual: " + actual + " %error: " + pe);
    }
    System.out.println("%error: " + pe);
  }

  public static BigDecimal percentError(BigDecimal expected, BigDecimal actual) {
    if (expected == null && actual == null) {
      return BigDecimal.ZERO;
    }
    if (expected.compareTo(BigDecimal.ZERO) == 0 && actual.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    return actual.subtract(expected).abs().divide(expected, MathContext.DECIMAL128);
  }
}
