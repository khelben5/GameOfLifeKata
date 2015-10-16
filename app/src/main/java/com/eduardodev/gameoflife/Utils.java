package com.eduardodev.gameoflife;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Some utils to not replicate code.
 */
public class Utils {

    /**
     * Gets the mathematical union between two sets.
     *
     * @param firstSet  The first set.
     * @param secondSet The second set.
     * @return A set containing the common elements.
     */
    public static <T> Set<T> getUnion(Set<T> firstSet, Set<T> secondSet) {
        Set<T> union = new HashSet<>();
        for (T firstSetItem : firstSet) {
            if (existsInSet(secondSet, firstSetItem)) {
                union.add(firstSetItem);
            }
        }
        return union;
    }

    /**
     * Checks if an element exists in a set.
     *
     * @param set           The set where to search for the element.
     * @param itemToBeFound The item to be found.
     * @return True if the element exists in the set and false otherwise.
     */
    public static <T> boolean existsInSet(Set<T> set, T itemToBeFound) {
        try {
            findInSet(set, itemToBeFound);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    /**
     * Finds an element in a set.
     *
     * @param set  The set where to search.
     * @param item The searched item.
     * @return The found item.
     * @throws NoSuchElementException In case the element is not found.
     */
    @NonNull
    public static <T> T findInSet(Set<T> set, T item) throws NoSuchElementException {
        for (T currentItem : set) {
            if (currentItem.equals(item)) {
                return currentItem;
            }
        }
        throw new NoSuchElementException("not found");
    }
}
