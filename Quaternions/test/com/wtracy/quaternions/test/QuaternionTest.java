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
