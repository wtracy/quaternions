package com.wtracy.quaternions;

import android.util.FloatMath;

public class Vector {
	protected final float x;
	protected final float y;
	protected final float z;
	
	static final float tolerance = 0.001f;
	
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
	
	public Vector crossProduct(Vector v) {
		Vector u = this;
		float x = u.y * v.z - u.z * v.y;
		float y = u.z * v.x - u.x * v.z;
		float z = u.x * v.y - u.y * v.x;
		
		return new Vector(x, y, z);
	}
	
	public float dotProduct(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public Vector matrixMultiply(float[] matrix) {
		float a = x * matrix[0] + y * matrix[1] + z * matrix[ 2];
		float b = x * matrix[4] + y * matrix[5] + z * matrix[ 6];
		float c = x * matrix[8] + y * matrix[9] + z * matrix[10];
		
		return new Vector(a, b, c);
	}
	
	public boolean isEquivalentTo(Vector v) {
		return 
				Math.abs(x-v.x) < tolerance &&
				Math.abs(y-v.y) < tolerance &&
				Math.abs(z-v.z) < tolerance;
	}
	
	public Vector normalize() {
		float root = FloatMath.sqrt(x*x + y*y + z*z);
		
		return new Vector(x / root, y / root, z / root);
	}
	
	public String toString() {
		return "<" + x + ", " + y + ", " + z + ">";
	}
}
