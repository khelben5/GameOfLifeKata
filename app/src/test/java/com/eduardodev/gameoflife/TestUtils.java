package com.eduardodev.gameoflife;

import junit.framework.Assert;

import java.util.Set;

/**
 * This class contains utility methods for testing.
 */
public class TestUtils {

    /**
     * Asserts if both sets are equal.
     *
     * @param expected The expected result.
     * @param actual   The result to be compared.
     */
    public static <T> void assertEquals(Set<T> expected, Set<T> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        for (T item : expected) {
            Utils.existsInSet(actual, item);
        }
    }
}
