package com.wtracy.quaternions;

import android.util.FloatMath;

public class Quaternion {
	static final float tolerance = 0.001f;
	protected final float x;
	protected final float y;
	protected final float z;
	protected final float w;
	
	/**
	 *  Creates a unit Quaternion representing null rotation.
	 */
	public Quaternion() {
		w = 1f;
		y = z = x = 0f;
	}
	
	
	private Quaternion(float w, float x, float y, float z) {
		float magnitudeSquared = w*w + x*x + y*y + z*z;
		
		if ((magnitudeSquared - 1f) > tolerance) {
			float magnitude = FloatMath.sqrt(magnitudeSquared);
			this.w = w / magnitude;
			this.x = x / magnitude;
			this.y = y / magnitude;
			this.z = z / magnitude;
		} else {
			this.w = w;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	/**
	 * Given a unit vector and a rotation, generates a unit Quaternion.
	 */
	public static Quaternion createFromAxisAngle(float x1, float y1, float z1, float angle) {
		float w = FloatMath.cos(angle/2);
		float x = (x1 * FloatMath.sin(angle/2));
		float y = (y1 * FloatMath.sin(angle/2));
		float z = (z1 * FloatMath.sin(angle/2));
		return new Quaternion(w, x, y, z);
	}
	
	
	

	
	/**
	 * Returns a new Quaternion representing the product of this
	 * Quaternion and the second one.
	 * @param q2
	 * @return The product of the Quaternion and q2.
	 */
	public Quaternion times(Quaternion q2) {
		Quaternion q1 = this;
		float w = q1.w*q2.w - q1.x*q2.x - q1.y*q2.y - q1.z*q2.z;
		float x = q1.x*q2.w + q1.w*q2.x + q1.z*q2.y - q1.y*q2.z;
		float y = q1.w*q2.y - q1.x*q2.z + q1.y*q2.w + q1.z*q2.x;
		float z = q1.w*q2.z - q2.x*q2.y + q1.y*q2.x + q1.z*q2.w;
		return new Quaternion(w, x, y, z);
	}
	
	/**
	 * Generates a matrix representing the transformation performed
	 * by the Quaternion's rotation.
	 * @return
	 */
	public float[] getMatrix() {
		return new float[] {
			/* Simplified version of the equation that should hold
			 * true for all unit Quaternions.
			 */
			1 - 2*y*y - 2*z*z, 2*x*y - 2*w*z, 2*x*z + 2*w*y, 0,
			2*x*y + 2*w*z, 1 - 2*x*x - 2*z*z, 2*y*z - 2*w*x, 0,
			2*x*z - 2*w*y, 2*y*z + 2*w*x, 1 - 2*x*x - 2*y*y, 0,
			0,             0,             0,                 1

		};
	}
	
	public String toString() {
		return "w = " + w + ", x = " + x + ", y = " + y + ", z = " + z + ", m^2 = " + (w*w + x*x + y*y + z*z);
	}
	
	public boolean isEquivalentTo(Quaternion q2) {
		return 
				Math.abs(w-q2.w) < tolerance &&
				Math.abs(x-q2.x) < tolerance &&
				Math.abs(y-q2.y) < tolerance &&
				Math.abs(z-q2.z) < tolerance;
	}
	
	public float getAxisAngleX() {
		return x / FloatMath.sqrt(x*x + y*y + z*z);
	}
	
	public float getAxisAngleY() {
		return y / FloatMath.sqrt(x*x + y*y + z*z);
	}
	
	public float getAxisAngleZ() {
		return z / FloatMath.sqrt(x*x + y*y + z*z);
	}
	
	public double getAxisAngleRotation() {
		return Math.acos(w)*2d;
	}
}
