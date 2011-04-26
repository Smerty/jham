package org.smerty.jham;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LocationTest {

	private static String[] locators = 		{"CM97cp",	"IO91sl",	"MN06bg",	"MN06bg",	"MN06bg",	"GA47jm"};
	private static double[] latitudes = 	{37.646,	51.479,		46.26519,	46.2508,	46.29106,	-82.49518};
	private static double[] longitudes = 	{-121.791,	-0.458,		60.09951,	60.08436,	60.1656,	-51.1908};

	private static final double FUDGE_FACTOR = 0.05; // we really should verify the point is inside the bounded box of the grid square

	@Test
	public void testLocationDoubleDouble() {
		for (int n = 0; n < locators.length; n++) {
			Location loc = new Location(latitudes[n], longitudes[n]);
			assertEquals(latitudes[n], loc.getLatitude(), 0);
			assertEquals(longitudes[n], loc.getLongitude(), 0);
		}
	}

	@Test
	public void testLocationString() {
		for (int n = 0; n < locators.length; n++) {
			Location loc = new Location(locators[n]);
			assertEquals(latitudes[n], loc.getLatitude(), FUDGE_FACTOR);
			assertEquals(longitudes[n], loc.getLongitude(), FUDGE_FACTOR);
		}
	}

	@Test
	public void testInverses() {
		for (int n = 0; n < locators.length; n++) {
			Location loc = new Location(locators[n]);
			Location loc2 = new Location(loc.getLatitude(), loc.getLongitude());
			assertEquals(locators[n], loc2.toMaidenhead());
			assertEquals(loc.getLatitude(), loc2.getLatitude(), FUDGE_FACTOR);
			assertEquals(loc.getLongitude(), loc2.getLongitude(), FUDGE_FACTOR);
		}
	}

	@Test
	public void testToMaidenhead() {
		for (int n = 0; n < locators.length; n++) {
			Location loc = new Location(latitudes[n], longitudes[n]);
			assertEquals(locators[n], loc.toMaidenhead());
		}
	}

	@Test
	public void testToMaidenheadDoubleDouble() {
		for (int n = 0; n < locators.length; n++) {
			assertEquals(locators[n], Location.toMaidenhead(latitudes[n], longitudes[n]));
		}
	}

	@Test
	public void testExtractLat() {
		for (int n = 0; n < locators.length; n++) {
			assertEquals(latitudes[n], Location.extractLat(locators[n]), FUDGE_FACTOR);
		}
	}

	@Test
	public void testExtractLon() {
		for (int n = 0; n < locators.length; n++) {
			assertEquals(longitudes[n], Location.extractLon(locators[n]), FUDGE_FACTOR);
		}
	}

}
