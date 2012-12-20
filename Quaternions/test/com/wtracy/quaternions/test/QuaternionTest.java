package com.wtracy.quaternions.test;

import com.wtracy.quaternions.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuaternionTest {
	protected void checkRotationConcatenation(float x, float y, float z) {
		Quaternion q1 = Quaternion.createFromAxisAngle(x, y, z, 1f);
		Quaternion q2 = q1.times(q1);
		Quaternion q3 = Quaternion.createFromAxisAngle(x, y, z, 2f);
		
		System.out.println("Quaternion q:");
		System.out.println(q1);
		System.out.println("Quaternion q*q:");
		System.out.println(q2);
		System.out.println("Quaternion from single rotation:");
		System.out.println(q3);
		System.out.println("===");
		
		assertTrue(q2.isEquivalentTo(q3));
	}
	
	protected void checkArraysAreEquivalent(float[] a, float[] b) {
		assertEquals(a.length, b.length);
		
		for (int i = 0; i < a.length; ++i) {
			assertEquals(a[i], b[i], 0.001d);
		}
	}
	
	@Test
	public void testUnitQuaternion() {
		float fromQuaternion[] = new Quaternion().getMatrix();
		float literal[] = new float[] {
				1f, 0f, 0f, 0f,
				0f, 1f, 0f, 0f,
				0f, 0f, 1f, 0f,
				0f, 0f, 0f, 1f
		};
		checkArraysAreEquivalent(fromQuaternion, literal);
	}
	
	@Test
	public void testXAxisRotation() {
		float fromQuaternion[] = Quaternion.createFromAxisAngle(1f, 0f, 0f, (float)Math.PI/2f).getMatrix();
		float literal[] = new float[] {
				1f, 0f,  0f, 0f,
				0f, 0f, -1f, 0f,
				0f, 1f,  0f, 0f,
				0f, 0f,  0f, 1f
		};
		checkArraysAreEquivalent(fromQuaternion, literal);
	}
	
	@Test
	public void testYAxisRotation() {
		float fromQuaternion[] = Quaternion.createFromAxisAngle(0f, 1f, 0f, (float)Math.PI/2f).getMatrix();
		float literal[] = new float[] {
				 0f, 0f,  1f, 0f,
				 0f, 1f,  0f, 0f,
				-1f, 0f,  0f, 0f,
				 0f, 0f,  0f, 1f
		};
		checkArraysAreEquivalent(fromQuaternion, literal);
	}
	
	@Test
	public void testZAxisRotation() {
		float fromQuaternion[] = Quaternion.createFromAxisAngle(0f, 0f, 1f, (float)Math.PI/2f).getMatrix();
		float literal[] = new float[] {
				0f, -1f,  0f, 0f,
				1f,  0f,  0f, 0f,
				0f,  0f,  1f, 0f,
				0f,  0f,  0f, 1f
		};
		checkArraysAreEquivalent(fromQuaternion, literal);
	}

	@Test
	public void testXAxisRotationConcatenation() {
		checkRotationConcatenation(1f, 0f, 0f);
	}
	
	@Test
	public void testYAxisRotationConcatenation() {
		checkRotationConcatenation(0f, 1f, 0f);
	}
	
	@Test
	public void testZAxisRotationConcatenation() {
		checkRotationConcatenation(0f, 0f, 1f);
	}
	
	@Test
	public void testXZRotationConcatenation() {
		float v = (float)Math.sqrt(2.0);
		checkRotationConcatenation(v, 0f, v);
	}
}
