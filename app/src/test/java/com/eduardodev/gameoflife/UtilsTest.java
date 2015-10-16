package com.eduardodev.gameoflife;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.eduardodev.gameoflife.TestUtils.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Tests for {@link Utils}.
 */
public class UtilsTest {

    @Test
    public void testGetUnion_happyPath() throws Exception {
        Set<Integer> firstSet = new HashSet<>(Arrays.asList(5, 6, 8, 1));
        Set<Integer> secondList = new HashSet<>(Arrays.asList(7, 8, 3, 0));
        Set<Integer> expectedResult = new HashSet<>(Collections.singletonList(8));
        assertEquals(expectedResult, Utils.getUnion(firstSet, secondList));
    }

    @Test
    public void testGetUnion_emptyInput() throws Exception {
        Set<Integer> firstList = new HashSet<>(Collections.<Integer>emptyList());
        Set<Integer> secondList = new HashSet<>(Arrays.asList(7, 8, 3, 0));
        Set<Integer> expectedResult = new HashSet<>(Collections.<Integer>emptyList());
        assertEquals(expectedResult, Utils.getUnion(firstList, secondList));
    }

    @Test
    public void testGetUnion_emptyResult() throws Exception {
        Set<Integer> firstList = new HashSet<>(Arrays.asList(5, 6, 8, 1, 10, 13));
        Set<Integer> secondList = new HashSet<>(Arrays.asList(7, 9, 3, 0));
        Set<Integer> expectedResult = new HashSet<>(Collections.<Integer>emptyList());
        assertEquals(expectedResult, Utils.getUnion(firstList, secondList));
    }

    @Test
    public void testExistsInList() throws Exception {
        assertFalse(Utils.existsInSet(new HashSet<>(Arrays.asList(5, 3, 78)), 4));
        assertTrue(Utils.existsInSet(new HashSet<>(Arrays.asList(5, 3, 78)), 78));
        assertFalse(Utils.existsInSet(new HashSet<>(Collections.emptyList()), 78));
    }

    @Test
    public void testFindInList_noSuchElement() throws Exception {
        try {
            Utils.findInSet(new HashSet<>(Arrays.asList(5, 7, 9, 6)), 21);
            fail("must have raised " + NoSuchElementException.class.getSimpleName());
        } catch (NoSuchElementException ignore) {
        }
    }

    @Test
    public void testFindInList_elementExists() throws Exception {
        try {
            Utils.findInSet(new HashSet<>(Arrays.asList(5, 7, 9, 6)), 9);
        } catch (NoSuchElementException ignore) {
            fail("must not have raised " + NoSuchElementException.class);
        }
    }
}