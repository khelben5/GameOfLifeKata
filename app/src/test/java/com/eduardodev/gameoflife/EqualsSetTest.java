package com.eduardodev.gameoflife;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link EqualsSet}.
 */
public class EqualsSetTest {

    @Test
    public void testAdd_noDuplicates() throws Exception {
        EqualsSet<Integer> set = new EqualsSet<>();
        set.add(2);
        set.add(3);
        set.add(4);
        assertEquals(3, set.size());
    }

    @Test
    public void testAdd_duplicates() throws Exception {
        EqualsSet<Integer> set = new EqualsSet<>();
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(3);
        assertEquals(3, set.size());
    }
}