/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 10.10.2016 
 * Aufgabe: Praktikum 1
 */

package aufgabenblatt01.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aufgabenblatt01.generics.ArrayList;

/**
 * Test for the {@link ArrayList} class.
 *
 * @author Moritz Höwer
 * @version 3.1 - 16.10.2016
 */
public class TestArrayList {

    private ArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @After
    public void tearDown() {
        list = null;
    }

    /**
     * Test method for {@link ArrayList#insertAt(int, java.lang.Object)}.
     */
    @Test
    public void testInsertAt() {
        // some values
        final int VALUE_1 = 10;
        final int VALUE_2 = 20;
        final int VALUE_3 = 30;
        final int VALUE_4 = 40;

        // negative index
        try {
            list.insertAt(-1, -1);
            fail("IndexOutOfBoundsException should have been thrown for index -1");
        } catch (IndexOutOfBoundsException e) {
        }

        // insert first error
        try {
            list.insertAt(1, 1);
            fail("IndexOutOfBoundsException should have been thrown for index -1");
        } catch (IndexOutOfBoundsException e) {
        }

        // insert first and check chaining
        assertThat("Chaining is broken", list.insertAt(0, VALUE_1), is(list));
        // [VALUE_1]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_1));

        // insert second error
        try {
            list.insertAt(2, 2);
            fail("IndexOutOfBoundsException should have been thrown for index 2");
        } catch (IndexOutOfBoundsException e) {
        }

        // insert behind
        list.insertAt(1, VALUE_2);
        // [VALUE_1, VALUE_2]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_1));
        assertThat("List has wrong element at index 1", list.get(1),
                is(VALUE_2));

        // insert before
        list.insertAt(0, VALUE_3);
        // [VALUE_3, VALUE_1, VALUE_2]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_3));
        assertThat("List has wrong element at index 1", list.get(1),
                is(VALUE_1));
        assertThat("List has wrong element at index 2", list.get(2),
                is(VALUE_2));

        // insert some more at the end
        list.insertAt(3, VALUE_4);
        // [VALUE_3, VALUE_1, VALUE_2, VALUE_4]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_3));
        assertThat("List has wrong element at index 1", list.get(1),
                is(VALUE_1));
        assertThat("List has wrong element at index 2", list.get(2),
                is(VALUE_2));
        assertThat("List has wrong element at index 3", list.get(3),
                is(VALUE_4));

        list.insertAt(0, null);
        assertThat("Element at 0 should be null", list.get(0) == null,
                is(true));
    }

    /**
     * Test method for {@link ArrayList#append(java.lang.Object)}.
     */
    @Test
    public void testAppend() {
        // some values
        final int VALUE_1 = 10;
        final int VALUE_2 = 20;

        // insert first and check chaining
        assertThat("Chaining is broken", list.append(VALUE_1), is(list));
        // [VALUE_1]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_1));

        // insert behind
        list.append(VALUE_2);
        // [VALUE_1, VALUE_2]
        assertThat("List has wrong element at index 0", list.get(0),
                is(VALUE_1));
        assertThat("List has wrong element at index 1", list.get(1),
                is(VALUE_2));

        // check null
        list.append(null);

        // [VALUE_1, VALUE_2, null]
        assertThat("Element at 2 should be null", list.get(2) == null,
                is(true));
    }

    /**
     * Test method for {@link ArrayList#deleteAt(int)}.
     */
    @Test
    public void testDeleteAt() {
        // delete on empty list
        try {
            list.deleteAt(0);
            fail("IndexOutOfBoundsException should have been thrown for empty list");
        } catch (IndexOutOfBoundsException e) {
        }

        // insert some data
        list.insertAt(0, 0).insertAt(1, 1).insertAt(2, 2);
        // [0,1,2]
        assertThat("List does not contain correct number of elements",
                list.size(), is(3));

        // test invalid indices
        try {
            list.deleteAt(-1);
            fail("IndexOutOfBoundsException should have been thrown for index -1");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            list.deleteAt(3);
            fail("IndexOutOfBoundsException should have been thrown for index 3");
        } catch (IndexOutOfBoundsException e) {
        }

        // delete last value and test chaining capabilities
        assertThat("Chaining not possible!", list.deleteAt(2), is(list));
        // [0,1]
        assertThat("List does not contain correct number of elements",
                list.size(), is(2));
        assertThat("List did not store the correct element at index 0",
                list.get(0), is(0));
        assertThat("List did not store the correct element at index 1",
                list.get(1), is(1));

        // delete first element
        list.deleteAt(0);
        // [1]
        assertThat("List did not store the correct element at index 0",
                list.get(0), is(1));

        // delete last element so list is empty
        list.deleteAt(0);
        // []
        assertThat("List should be empty", list.size(), is(0));
    }

    /**
     * Test method for {@link ArrayList#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        final int VALUE = 32;

        // delete on empty list (value not found)
        try {
            list.delete(0);
            fail("IllegalArgumentException should have been thrown for missing element");
        } catch (IllegalArgumentException e) {
        }

        // insert some data
        list.append(0).append(VALUE).append(2);
        // [0,VALUE,2]
        assertThat("List does not contain correct number of elements",
                list.size(), is(3));

        // delete null (also not found)
        try {
            list.delete(null);
            fail("IllegalArgumentException should have been thrown for missing element");
        } catch (IllegalArgumentException e) {
        }

        // delete last value and test chaining capabilities
        assertThat("Chaining not possible!", list.delete(VALUE), is(list));
        // [0,1]
        assertThat("List does not contain correct number of elements",
                list.size(), is(2));
        assertThat("List did not store the correct element at index 0",
                list.get(0), is(0));
        assertThat("List did not store the correct element at index 1",
                list.get(1), is(2));

        // test shrinking
        list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.append(i);
        }
        // [0,1,2,3,4,5,6,7,8,9,10]
        for (int i = 10; i > 6; i--) {
            list.deleteAt(i);
        }
        // [0,1,2,3,4,5,6]
        // list is just about to be shrunk
        list.deleteAt(0);
        // list has now been shrunk back to a length of 10
        assertThat("Size is wrong", list.size(), is(6));
        for (int i = 0; i < 6; i++) {
            assertThat("Element at index " + i + " is wrong", list.get(i),
                    is(i + 1));
        }

    }

    /**
     * Test method for {@link ArrayList#find(java.lang.Object)}.
     */
    @Test
    public void testFind() {
        // some values to be inserted
        final int VALUE_1 = 345;
        final int VALUE_2 = 768;
        final int VALUE_3 = 1337;

        assertTrue("Find on empty list should be empty",
                !list.find(VALUE_1).isPresent());

        // insert values
        list.insertAt(0, VALUE_1).insertAt(0, VALUE_2).insertAt(0, VALUE_3);

        assertTrue("Object should not have been found",
                !list.find(0).isPresent());

        assertTrue("Object should have been found",
                list.find(VALUE_2).isPresent());
        assertThat("Object should be at a different index",
                list.find(VALUE_2).getAsInt(), is(1));
    }

    /**
     * Test method for {@link ArrayList#get(int)}.
     */
    @Test
    public void testGet() {
        // a value to be inserted
        final int VALUE = 100;

        // retrieve on empty list
        try {
            list.get(0);
            fail("IndexOutOfBoundsException should have been thrown for empty list");
        } catch (IndexOutOfBoundsException e) {
        }

        // insert value
        list.insertAt(0, VALUE);

        // test invalid indices
        try {
            list.get(-1);
            fail("IndexOutOfBoundsException should have been thrown for index -1");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            list.get(1);
            fail("IndexOutOfBoundsException should have been thrown for index 1");
        } catch (IndexOutOfBoundsException e) {
        }

        assertThat("List did not store the correct element at index 0",
                list.get(0), is(VALUE));
    }

    /**
     * Test method for {@link ArrayList#size()}.
     */
    @Test
    public void testSize() {
        assertThat("List should be empty", list.size(), is(0));

        list.insertAt(0, 0);
        assertThat("List has wrong size", list.size(), is(1));

        list.insertAt(1, 1);
        assertThat("List has wrong size", list.size(), is(2));

        list.deleteAt(0);
        assertThat("List has wrong size", list.size(), is(1));
    }

    /**
     * Test method for {@link ArrayList#size()}.
     */
    @Test
    public void testGetSmallest() {
        final int VALUE_1 = 10;
        final int VALUE_2 = 24;
        final int VALUE_SMALLEST = 5;

        assertThat("List should be empty", list.size(), is(0));

        // retrieve on empty list
        try {
            list.getSmallest();
            fail("IllegalStateException should have been thrown for empty list");
        } catch (IllegalStateException e) {
        }

        // create List with some Objects (doesn't implement Comparable)
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.append(new Object());

        // retrieve on list that doesn't implement Comparable
        try {
            list.getSmallest();
            fail("IllegalStateException should have been thrown for Object list");
        } catch (IllegalStateException e) {
        }

        // add elements
        list.append(VALUE_1).append(VALUE_SMALLEST).append(VALUE_2);
        assertThat("List has wrong size", list.size(), is(3));

        assertThat("List doesn't return the correct element",
                list.getSmallest(), is(VALUE_SMALLEST));

        list.insertAt(0, null);
        // retrieve on list that has null at the front
        try {
            list.getSmallest();
            fail("IllegalStateException should have been thrown for null at front");
        } catch (IllegalStateException e) {
        }
    }

    /**
     * Test method for {@link ArrayList#isFirstElementNumber(ArrayList)}.
     */
    @Test
    public void testIsFirstElementNumber() {
        assertThat("Empty List should not contain Numbers",
                ArrayList.isFirstElementNumber(list), is(false));

        list.append(1);
        assertThat("Integer List should contain Numbers",
                ArrayList.isFirstElementNumber(list), is(true));

        ArrayList<String> stringList = new ArrayList<>();
        stringList.append("Hello World");
        assertThat("String List should not contain Numbers",
                ArrayList.isFirstElementNumber(stringList), is(false));
    }
    
    /**
     * Test method for {@link ArrayList#toString()}.
     */
    @Test
    public void testToString() {
        assertThat("Empty List String is wrong", list.toString(), is("ArrayList(0)-[]"));
        
        list.append(0);        
        assertThat("List String is wrong", list.toString(), is("ArrayList(1)-[0]"));
        
        list.append(2);
        list.append(15);
        assertThat("List String is wrong", list.toString(), is("ArrayList(3)-[0, 2, 15]"));
    }
    
}
