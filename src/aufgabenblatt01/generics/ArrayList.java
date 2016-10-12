/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 10.10.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package aufgabenblatt01.generics;

import java.util.OptionalInt;

/**
 * A List based on arrays
 *
 * @version 2.0 - 10.10.2016
 */
public class ArrayList<T> {

    /**
     * initial size of the array
     */
    private static final int START_CAPACITY = 10;

    /**
     * the factor to multiply current array size with when expanding
     */
    private static final int GROWTH_FACTOR = 10;

    /**
     * the array used for storing the data in this list
     */
    protected Object[] data;

    /**
     * the number of elements stored
     */
    protected int count;

    public ArrayList() {
        count = 0;
        data = new Object[START_CAPACITY];
    }

    /**
     * Inserts a new value into the list at a given index
     * 
     * @param index
     *            the index at which to insert a new value
     * @param value
     *            the value to insert
     * @return {@code this} for chaining
     * @throws IndexOutOfBoundsException
     *             when index is invalid
     */
    public ArrayList<T> insertAt(int index, T value) {
        if (index < 0 || index > count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        if (count == data.length) {
            // we need a new array
            Object[] temp = new Object[data.length * GROWTH_FACTOR];

            // copy elements before the index to new array
            System.arraycopy(data, 0, temp, 0, index);

            // insert new element
            temp[index] = value;

            // copy elements after index
            System.arraycopy(data, index, temp, index + 1, data.length - index);

            // make data point to new array
            data = temp;

            // increase counter
            count++;

        } else {
            if (index == count) {
                // nice! we can just append without needing to copy anything...
                data[index] = value;

            } else {
                // damn, we need to copy
                for (int i = count; i > index; i--) {
                    // copy every element behind index one back, starting at the
                    // back
                    data[i] = data[i - 1];

                }

                // insert new element
                data[index] = value;

            }

            // increase counter
            count++;
        }

        return this;
    }

    /**
     * Inserts a new value into the list at the end
     * 
     * @param value
     *            the value to insert
     * @return {@code this} for chaining
     */
    public ArrayList<T> append(T value) {
        return insertAt(size(), value);
    }

    /**
     * Delete a value at a specific index
     * 
     * @param index
     *            the index at which to delete
     * @return {@code this} for chaining
     * @throws IndexOutOfBoundsException
     *             when index is invalid
     */
    public ArrayList<T> deleteAt(int index) {
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        if (index == count - 1) {
            // deleting last item - easy
            count--;

        } else {
            // move all elements behind index forward one
            for (int i = index; i < count - 1; i++) {
                data[i] = data[i + 1];

            }

            count--;
        }

        return this;
    }

    /**
     * Delete a value from the list
     * 
     * @param value
     *            the value to delete
     * @return {@code this} for chaining
     * @throws IllegalArgumentException
     *             when value is not found
     */
    public ArrayList<T> delete(T value) {
        int index = find(value).orElseThrow(IllegalArgumentException::new);
        return deleteAt(index);
    }

    /**
     * Find a value in the list
     * 
     * @param value
     *            the value to be searched for
     * @return an {@link java.util.OptionalInt OptionalInt} that contains the
     *         index or is empty if nothing was found
     */
    @SuppressWarnings("unchecked")
    public OptionalInt find(T value) {
        for (int i = 0; i < count; i++) {
            if (((T) data[i]).equals(value)) {
                // unchecked cast is safe because I know array has only "T"
                // objects
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Retrieves a value from the list
     * 
     * @param index
     *            the index where to retrieve a value from
     * @return the value
     * @throws IndexOutOfBoundsException
     *             when index is invalid
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }

        // this unchecked cast is safe, because I know I only put objects of
        // type T into array
        return (T) data[index];
    }

    /**
     * Retrieves the smallest value from the list
     * 
     * @return the smallest value
     * @throws IllegalStateException
     *             when list is empty or if the elements don't implement
     *             Comparable or if the first element in the list is null
     * 
     */
    @SuppressWarnings("unchecked")
    public T getSmallest() {
        if (size() == 0) {
            // list is empty
            throw new IllegalStateException("List is empty");
        }

        /*
         * ************************* ! CAUTION ! ******************************
         * weird code incoming because of generics - be prepared...
         * ********************************************************************
         */

        // get first element for type checking
        T test = (T) data[0];
        if (test instanceof Comparable) {
            // cast first element to Comparable for comparing and assume it's
            // the smallest
            Comparable<T> smallest = (Comparable<T>) test;

            for (int i = 1; i < size(); i++) {
                // go through every element
                T current = (T) data[i];
                if (smallest.compareTo(current) > 0) {
                    // smallest is bigger than the current element => swap them
                    smallest = (Comparable<T>) current;
                }
            }
            // cast smallest back to T and return it
            return (T) smallest;

        } else {
            throw new IllegalStateException(
                    "Elements in List are not comparable");
        }
    }

    /**
     * Gets the number of elements in this list
     * 
     * @return the number of elements
     */
    public int size() {
        return count;
    }

    /**
     * Checks whether the first Element in the List is a Number
     * 
     * @param list
     *            the list to check
     * @return true, if first element is a Number, false if not or if list is
     *         empty
     */
    public static <X> boolean isFirstElementNumber(ArrayList<X> list) {
        if (list.size() == 0) {
            return false;
        }
        
        // get first value
        X value = list.get(0);
        return value instanceof Number;
    }

}
