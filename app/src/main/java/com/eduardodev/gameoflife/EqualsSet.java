package com.eduardodev.gameoflife;

import java.util.HashSet;
import java.util.Set;

import static com.eduardodev.gameoflife.Utils.existsInSet;

/**
 * This class represents a set in its mathematical sense. It makes use of equals method to compare
 * objects. It cannot contain twice the same conceptual item, i.e. this cannot happen: [x,y] where
 * x.equals(y).
 */
public class EqualsSet<T> {

    private final Set<T> mItems;

    public EqualsSet() {
        mItems = new HashSet<>();
    }

    /**
     * Tries to add a new item to this set. If the item does not exist in the set, it is added
     * normally, otherwise the set remains the same. It makes use of equals method to compare
     * objects.
     *
     * @param item The item to be added.
     */
    public void add(T item) {
        if (!existsInSet(mItems, item)) {
            mItems.add(item);
        }
    }

    /**
     * @return The number of elements in this set.
     */
    public int size() {
        return mItems.size();
    }

    /**
     * @return A {@link Set} containing the objects in this set.
     */
    public Set<T> toSet() {
        return mItems;
    }
}
