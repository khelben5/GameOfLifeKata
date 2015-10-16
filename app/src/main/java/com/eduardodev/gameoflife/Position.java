package com.eduardodev.gameoflife;

import java.util.HashSet;
import java.util.Set;

/**
 * This data structure represents a position with x and y coordinates.
 */
public class Position {
    private final int mX;
    private final int mY;

    /**
     * Creates a new position from its coordinates.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Position(int x, int y) {
        mX = x;
        mY = y;
    }

    /**
     * @return The X coordinate.
     */
    public int getX() {
        return mX;
    }

    /**
     * @return The Y coordinate.
     */
    public int getY() {
        return mY;
    }

    /**
     * Gets this position's neighbors depending on the limits specified by the parameters.
     *
     * @param maxX The maximum x coordinate allowed.
     * @param maxY The maximum y coordinate allowed.
     * @return A list containing this position's neighbors.
     */
    public Set<Position> getNeighborsWithLimits(int maxX, int maxY) {
        Set<Position> neighbors = new HashSet<>(8);
        if (mX - 1 >= 0 && mY - 1 >= 0) {
            neighbors.add(new Position(mX - 1, mY - 1));
        }
        if (mX - 1 >= 0) {
            neighbors.add(new Position(mX - 1, mY));
        }
        if (mX - 1 >= 0 && mY + 1 < maxY) {
            neighbors.add(new Position(mX - 1, mY + 1));
        }
        if (mY - 1 >= 0) {
            neighbors.add(new Position(mX, mY - 1));
        }
        if (mY + 1 < maxY) {
            neighbors.add(new Position(mX, mY + 1));
        }
        if (mX + 1 < maxX && mY - 1 >= 0) {
            neighbors.add(new Position(mX + 1, mY - 1));
        }
        if (mX + 1 < maxX) {
            neighbors.add(new Position(mX + 1, mY));
        }
        if (mX + 1 < maxX && mY + 1 < maxY) {
            neighbors.add(new Position(mX + 1, mY + 1));
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position anotherPosition = (Position) o;
            return mX == anotherPosition.mX && mY == anotherPosition.mY;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", mX, mY);
    }
}
