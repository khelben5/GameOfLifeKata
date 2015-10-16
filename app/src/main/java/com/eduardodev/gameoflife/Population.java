package com.eduardodev.gameoflife;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a concrete population.
 */
public class Population {

    private static final int MIN_NEIGHBORS_TO_SURVIVE = 2;
    private static final int MAX_NEIGHBORS_TO_SURVIVE = 3;
    private static final int NEIGHBOURS_TO_BIRTH = 3;
    private final boolean[][] mPositions;

    /**
     * Constructs a new population where there are no alive positions.
     */
    public Population(int maxWidth, int maxHeight) {
        mPositions = new boolean[maxHeight][maxWidth];
    }

    /**
     * Specifies which positions should be alive in this population.
     */
    public void setAlivePositions(Set<Position> alivePositions) {
        killAllPositions();
        for (Position position : alivePositions) {
            mPositions[position.getY()][position.getX()] = true;
        }
    }

    private void killAllPositions() {
        for (boolean[] row : mPositions) {
            for (int i = 0; i < row.length; i++) {
                row[i] = false;
            }
        }
    }

    /**
     * @return A list containing all the positions alive.
     */
    public Set<Position> getAlivePositions() {
        Set<Position> alivePositions = new HashSet<>();
        for (int i = 0; i < mPositions.length; i++) {
            for (int j = 0; j < mPositions[i].length; j++) {
                if (mPositions[i][j]) {
                    alivePositions.add(new Position(j, i));
                }
            }
        }
        return alivePositions;
    }

    /**
     * Evolves this population.
     */
    public void evolve() {
        Set<Position> positionsToKill = new HashSet<>();
        Set<Position> positionsToBeBorn = new HashSet<>();
        for (int i = 0; i < mPositions.length; i++) {
            for (int j = 0; j < mPositions[i].length; j++) {
                Position position = new Position(j, i);
                Set<Position> aliveNeighbors = filterAlive(
                        position.getNeighborsWithLimits(mPositions[0].length, mPositions.length));
                if (mPositions[i][j] && aliveNeighbors.size() < MIN_NEIGHBORS_TO_SURVIVE ||
                        aliveNeighbors.size() > MAX_NEIGHBORS_TO_SURVIVE) {
                    positionsToKill.add(position);
                } else if (!mPositions[i][j] && aliveNeighbors.size() == NEIGHBOURS_TO_BIRTH) {
                    positionsToBeBorn.add(position);
                }
            }
        }
// TODO Happy coding!
        setPositionsTo(positionsToKill, false);
        setPositionsTo(positionsToBeBorn, true);
    }

    private void setPositionsTo(Set<Position> positions, boolean value) {
        for (Position position : positions) {
            mPositions[position.getY()][position.getX()] = value;
        }
    }

    private Set<Position> filterAlive(Set<Position> positions) {
        Set<Position> alive = new HashSet<>(positions.size());
        for (Position position : positions) {
            if (mPositions[position.getY()][position.getX()]) {
                alive.add(position);
            }
        }
        return alive;
    }
}
