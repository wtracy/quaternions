package android.util;

import java.lang.Math;

/**
 * Mock implementation for android.util.FloatMath. Uses java.lang.Math internally.
 * @author wtracy
 *
 */
public class FloatMath {
	public static float sqrt(float x) {
		return (float)Math.sqrt(x);
	}
	
	public static float sin(float x) {
		return (float)Math.sin(x);
	}
	
	public static float cos(float x) {
		return (float)Math.cos(x);
	}
}
