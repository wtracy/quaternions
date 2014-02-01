package com.wtracy.quaternions.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.wtracy.quaternions.Vector;

public class VectorTest {
	static final float[] identity = new float[] {
		1, 0, 0, 0,
		0, 1, 0, 1,
		0, 0, 1, 0,
		0, 0, 0, 1
	};
	
	static final float[] swapXY = new float[] {
		0, 1, 0, 0,
		1, 0, 0, 0,
		0, 0, 1, 0,
		0, 0, 0, 1
	};

	@Test
	public void testDotProduct() {
		assertEquals(new Vector(1f, 2f, 3f).dotProduct(new Vector(1f, 2f, 3f)), 14f, 0.001f);
	}

	@Test
	public void testDotProduct2() {
		assertEquals(new Vector(1f, 2f, 3f).dotProduct(new Vector(3f, 2f, 1f)), 10f, 0.001f);
	}
	
	@Test
	public void selfDotProduct() {
		assertEquals(new Vector(0f, 0f, 1f).dotProduct(new Vector(0f, 0f, 1f)), 1f, 0.001f);
	}
	
	@Test
	public void rightAngleDotProduct() {
		assertEquals(new Vector(0f, 0f, 1f).dotProduct(new Vector(0f, 1f, 0f)), 0f, 0.001f);
	}
	
	@Test
	public void testMatrixMultiplicationIdentity() {
		Vector v = new Vector(1, 1, 1);
		assertTrue(v.matrixMultiply(identity).isEquivalentTo(v));
	}
	
	@Test
	public void testMatrixMultiplication() {
		assertTrue(new Vector(1, 0, 0).matrixMultiply(swapXY).isEquivalentTo(new Vector(0, 1, 0)));
	}
	
	@Test
	public void trivialNormalization() {
		Vector l1 = new Vector(1, 0, 0);
		Vector n = new Vector(2, 0, 0).normalize();
		System.out.println(l1);
		System.out.println(n);
		assertTrue(l1.isEquivalentTo(n));
	}
	
	@Test
	public void diagonalNormalization() {
		assertTrue(new Vector(1, 1, 0).normalize().isEquivalentTo(
				new Vector((float)Math.sqrt(0.5), (float)Math.sqrt(0.5), 0f)));
	}
	
	@Test
	public void crossProduct() {
		assertTrue(new Vector(0, 0, -1).crossProduct(new Vector(-1, 0, -1)).normalize().isEquivalentTo(new Vector(0, 1, 0)));
	}
}
