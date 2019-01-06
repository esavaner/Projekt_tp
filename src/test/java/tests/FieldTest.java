package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import table.Field;

public class FieldTest {

	@Test
	public void testFieldInt() {
		Field f = new Field(1);
		assertEquals(f.getNumber(), 1);
	}

	@Test
	public void testGetColor() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		assertEquals(f.getColor(), Color.WHITE);
	}

	@Test
	public void testGetNumber() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		assertEquals(f.getNumber(), 1);
	}

	@Test
	public void testIsOccupied() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		assertEquals(f.occupied, false);
	}

	@Test
	public void testSetEmpty() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		f.setEmpty();
		assertEquals(f.occupied, false);
	}

	@Test
	public void testSetOccupied() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		f.setOccupied();
		assertEquals(f.occupied, true);
	}

	@Test
	public void testChangeColor() {
		Field f = new Field(Color.WHITE, 1, 10, 15);
		f.changeColor(Color.RED);
		assertEquals(f.getColor(), Color.RED);
	}

}
