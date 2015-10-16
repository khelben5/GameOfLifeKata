package com.eduardodev.gameoflife;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.eduardodev.gameoflife.TestUtils.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for {@link Position}.
 */
public class PositionTest {

    private static final int MAX_NEIGHBORS = 9;

    @Test
    public void testEquals_equals() throws Exception {
        Position positionA = new Position(5, 75);
        Position positionB = new Position(5, 75);
        assertTrue(positionA.equals(positionB));
    }

    @Test
    public void testEquals_xNotEquals() throws Exception {
        Position positionA = new Position(4, 75);
        Position positionB = new Position(5, 75);
        assertFalse(positionA.equals(positionB));
    }

    @Test
    public void testEquals_yNotEquals() throws Exception {
        Position positionA = new Position(5, 75);
        Position positionB = new Position(5, 74);
        assertFalse(positionA.equals(positionB));
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    public void testEquals_differentClasses() throws Exception {
        Position positionA = new Position(5, 75);
        String notAPosition = "not a position";
        assertFalse(positionA.equals(notAPosition));
    }

    @Test
    public void testGetNeighbors_happyPath() throws Exception {
        Position position = new Position(5, 6);
        Set<Position> neighbors = position.getNeighborsWithLimits(10, 10);
        Set<Position> expectedNeighbors = createExpectedNeighborsSetHappyPath();
        assertEquals(expectedNeighbors, neighbors);
    }

    private Set<Position> createExpectedNeighborsSetHappyPath() {
        Set<Position> neighbors = new HashSet<>(MAX_NEIGHBORS);
        neighbors.add(new Position(4, 5));
        neighbors.add(new Position(4, 6));
        neighbors.add(new Position(4, 7));
        neighbors.add(new Position(5, 5));
        neighbors.add(new Position(5, 7));
        neighbors.add(new Position(6, 5));
        neighbors.add(new Position(6, 6));
        neighbors.add(new Position(6, 7));
        return neighbors;
    }

    @Test
    public void testGetNeighbors_upperLeftCorner() throws Exception {
        Position position = new Position(0, 0);
        Set<Position> neighbors = position.getNeighborsWithLimits(5, 5);
        Set<Position> expectedNeighbors = createExpectedNeighborsUpperLeftCorner();
        assertEquals(expectedNeighbors, neighbors);
    }

    private Set<Position> createExpectedNeighborsUpperLeftCorner() {
        Set<Position> neighbors = new HashSet<>(MAX_NEIGHBORS);
        neighbors.add(new Position(0, 1));
        neighbors.add(new Position(1, 0));
        neighbors.add(new Position(1, 1));
        return neighbors;
    }

    @Test
    public void testGetNeighbors_lowerLeftCorner() throws Exception {
        Position position = new Position(0, 4);
        Set<Position> neighbors = position.getNeighborsWithLimits(5, 5);
        Set<Position> expectedNeighbors = createExpectedNeighborsLowerLeftCorner();
        assertEquals(expectedNeighbors, neighbors);
    }

    private Set<Position> createExpectedNeighborsLowerLeftCorner() {
        Set<Position> neighbors = new HashSet<>(MAX_NEIGHBORS);
        neighbors.add(new Position(0, 3));
        neighbors.add(new Position(1, 3));
        neighbors.add(new Position(1, 4));
        return neighbors;
    }

    @Test
    public void testGetNeighbors_upperRightCorner() throws Exception {
        Position position = new Position(4, 0);
        Set<Position> neighbors = position.getNeighborsWithLimits(5, 5);
        Set<Position> expectedNeighbors = createExpectedNeighborsUpperRightCorner();
        assertEquals(expectedNeighbors, neighbors);
    }

    private Set<Position> createExpectedNeighborsUpperRightCorner() {
        Set<Position> neighbors = new HashSet<>(MAX_NEIGHBORS);
        neighbors.add(new Position(3, 0));
        neighbors.add(new Position(3, 1));
        neighbors.add(new Position(4, 1));
        return neighbors;
    }

    @Test
    public void testGetNeighbors_lowerRightCorner() throws Exception {
        Position position = new Position(4, 4);
        Set<Position> neighbors = position.getNeighborsWithLimits(5, 5);
        Set<Position> expectedNeighbors = createExpectedNeighborsLowerRightCorner();
        assertEquals(expectedNeighbors, neighbors);
    }

    private Set<Position> createExpectedNeighborsLowerRightCorner() {
        Set<Position> neighbors = new HashSet<>(MAX_NEIGHBORS);
        neighbors.add(new Position(3, 3));
        neighbors.add(new Position(3, 4));
        neighbors.add(new Position(4, 3));
        return neighbors;
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("(2,3)", new Position(2, 3).toString());
        Assert.assertEquals("(5,6)", new Position(5, 6).toString());
    }
}