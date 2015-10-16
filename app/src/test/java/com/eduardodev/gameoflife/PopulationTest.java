package com.eduardodev.gameoflife;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.eduardodev.gameoflife.TestUtils.assertEquals;


/**
 * Tests for {@link Population}.
 */
public class PopulationTest {

    private static final int REGION_WIDTH = 5;
    private static final int REGION_HEIGHT = 6;

    @Test
    public void testGetterAndSetter() throws Exception {
        Population state = new Population(REGION_WIDTH, REGION_HEIGHT);
        Set<Position> expectedAlivePositions = createPositionSet1();
        state.setAlivePositions(expectedAlivePositions);
        Set<Position> actualAlivePositions = state.getAlivePositions();
        assertEquals(expectedAlivePositions, actualAlivePositions);
    }

    private Set<Position> createPositionSet1() {
        Set<Position> positions = new HashSet<>(5);
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        return positions;
    }

    @Test
    public void testEvolve_underpopulation() throws Exception {
        assertEvolve(createExpectedUnderpopulation(), createInputSetUnderpopulation());
    }

    private Set<Position> createExpectedUnderpopulation() {
        return new HashSet<>();
    }

    private Set<Position> createInputSetUnderpopulation() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        return positions;
    }

    private static void assertEvolve(Set<Position> expected, Set<Position> input) {
        Population population = new Population(REGION_WIDTH, REGION_HEIGHT);
        population.setAlivePositions(input);
        population.evolve();
        Set<Position> output = population.getAlivePositions();
        assertEquals(expected, output);
    }

    @Test
    public void testEvolve_noUnderpopulation() throws Exception {
        Set<Position> input = createInputSetNoUnderpopulation();
        Set<Position> expected = createExpectedNoUnderpopulation();
        assertEvolve(expected, input);
    }

    private Set<Position> createInputSetNoUnderpopulation() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 0));
        return positions;
    }

    private Set<Position> createExpectedNoUnderpopulation() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        return positions;
    }

    @Test
    public void testEvolve_overcrowding() throws Exception {
        Set<Position> input = createInputOvercrowding();
        Set<Position> expected = createExpectedOvercrowding();
        assertEvolve(expected, input);
    }

    private Set<Position> createInputOvercrowding() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        return positions;
    }

    private Set<Position> createExpectedOvercrowding() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        return positions;
    }

    @Test
    public void testEvolve_noOvercrowding() throws Exception {
        Set<Position> input = createInputNoOvercrowding();
        Set<Position> expected = createExpectedNoOvercrowding();
        assertEvolve(expected, input);
    }

    private Set<Position> createInputNoOvercrowding() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(3, 1));
        positions.add(new Position(4, 0));
        return positions;
    }

    private Set<Position> createExpectedNoOvercrowding() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(1, 2));
        positions.add(new Position(2, 0));
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        positions.add(new Position(3, 0));
        positions.add(new Position(3, 1));
        return positions;
    }

    @Test
    public void testEvolve_births() throws Exception {
        assertEvolve(createExpectedNoBirths(), createInputNoBirths());
    }

    private Set<Position> createExpectedNoBirths() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        return positions;
    }

    private Set<Position> createInputNoBirths() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 0));
        return positions;
    }

    @Test
    public void testEvolve_edgesBirths() throws Exception {
        assertEvolve(createExpectedEdgesBirths(), createInputEdgesBirths());
    }

    private Set<Position> createExpectedEdgesBirths() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        positions.add(new Position(0, 1));
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 3));
        positions.add(new Position(1, 3));
        positions.add(new Position(1, 4));
        positions.add(new Position(4, 0));
        positions.add(new Position(3, 0));
        positions.add(new Position(3, 1));
        positions.add(new Position(4, 1));
        positions.add(new Position(0, 4));
        positions.add(new Position(3, 3));
        positions.add(new Position(3, 4));
        positions.add(new Position(4, 3));
        positions.add(new Position(4, 4));
        return positions;
    }

    private Set<Position> createInputEdgesBirths() {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        positions.add(new Position(0, 1));
        positions.add(new Position(0, 3));
        positions.add(new Position(1, 3));
        positions.add(new Position(1, 4));
        positions.add(new Position(3, 0));
        positions.add(new Position(3, 1));
        positions.add(new Position(4, 1));
        positions.add(new Position(3, 3));
        positions.add(new Position(3, 4));
        positions.add(new Position(4, 3));
        return positions;
    }
}