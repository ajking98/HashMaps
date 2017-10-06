import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * HashMapStudentTests
 *
 * @author Sanath Nagaraj
 * @version 1.0
 */
public class HashMapTestsSanath {

    private HashMap<Integer, String> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<>();
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testConstructor() {
        directory = new HashMap<>();
        assertEquals(0, directory.size());
        MapEntry<Integer, String>[] exp = (MapEntry<Integer, String>[]) (new MapEntry[13]);
        assertArrayEquals(exp, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testConstructor2() {
        directory = new HashMap<>(5);
        assertEquals(0, directory.size());
        MapEntry<Integer, String>[] exp = (MapEntry<Integer, String>[]) (new MapEntry[5]);
        assertArrayEquals(exp, directory.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutException() {
        directory.put(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutException2() {
        directory.put(11111, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutException3() {
        directory.put(null, "Should produce exception");
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPut() {
        directory = new HashMap<>(5);
        MapEntry<Integer, String>[] exp = (MapEntry<Integer, String>[]) (new MapEntry[5]);
        assertEquals(0, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(3, "Luke"));
        exp[3] = new MapEntry<>(3, "Luke");
        assertEquals(1, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(13, "Han"));
        exp[4] = new MapEntry<>(13, "Han");
        assertEquals(2, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(8, "Chewbacca"));
        exp[0] = new MapEntry<>(8, "Chewbacca");
        assertEquals(3, directory.size());
        assertArrayEquals(exp, directory.getTable());

        exp = (MapEntry<Integer, String>[]) (new MapEntry[11]);
        assertEquals(null, directory.put(-5, "Leia"));
        exp[2] = new MapEntry<>(13, "Han");
        exp[3] = new MapEntry<>(3, "Luke");
        exp[5] = new MapEntry<>(-5, "Leia");
        exp[8] = new MapEntry<>(8, "Chewbacca");
        assertEquals(4, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(15, "C3PO"));
        exp[4] = new MapEntry<>(15, "C3PO");
        assertEquals(5, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(-2, "R2D2"));
        exp[6] = new MapEntry<>(-2, "R2D2");
        assertEquals(6, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Chewbacca", directory.put(8, "Chewie"));
        exp[8] = new MapEntry<>(8, "Chewie");
        assertEquals(6, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("R2D2", directory.put(-2, "Artoo"));
        exp[6] = new MapEntry<>(-2, "Artoo");
        assertEquals(6, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("C3PO", directory.put(15, "3PO"));
        exp[4] = new MapEntry<>(15, "3PO");
        assertEquals(6, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(19, "Lando"));
        exp[9] = new MapEntry<>(19, "Lando");
        assertEquals(7, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.remove(13);
        assertEquals(6, directory.size());
        exp[2].setRemoved(true);

        assertEquals("Artoo", directory.put(-2, "R2D2"));
        exp[6] = new MapEntry<>(-2, "R2D2");
        assertEquals(6, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(13, "Han Solo"));
        exp[2] = new MapEntry<>(13, "Han Solo");
        assertEquals(7, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.remove(19);
        assertEquals(6, directory.size());
        exp[9].setRemoved(true);

        assertEquals(null, directory.put(30, "Calrissian"));
        exp[9] = new MapEntry<>(30, "Calrissian");
        assertEquals(7, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.remove(30);
        assertEquals(6, directory.size());
        exp[9].setRemoved(true);

        assertEquals(null, directory.put(-13, "Calrissian"));
        exp[7] = new MapEntry<>(-13, "Calrissian");
        assertEquals(7, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Calrissian", directory.put(-13, "Lando"));
        exp = (MapEntry<Integer, String>[]) (new MapEntry[23]);
        exp[2] = new MapEntry<>(-2, "R2D2");
        exp[3] = new MapEntry<>(3, "Luke");
        exp[5] = new MapEntry<>(-5, "Leia");
        exp[8] = new MapEntry<>(8, "Chewie");
        exp[13] = new MapEntry<>(13, "Han Solo");
        exp[14] = new MapEntry<>(-13, "Lando");
        exp[15] = new MapEntry<>(15, "3PO");
        assertEquals(7, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(49, "Anakin"));
        exp[4] = new MapEntry<>(49, "Anakin");
        assertEquals(8, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(-25, "Obi Wan"));
        exp[6] = new MapEntry<>(-25, "Obi Wan");
        assertEquals(9, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(26, "Jar Jar"));
        exp[7] = new MapEntry<>(26, "Jar Jar");
        assertEquals(10, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.remove(3);
        assertEquals(9, directory.size());
        exp[3].setRemoved(true);

        assertEquals("Jar Jar", directory.put(26, "Ew"));
        exp[7] = new MapEntry<>(26, "Ew");
        assertEquals(9, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals(null, directory.put(3, "Master Luke"));
        exp[3] = new MapEntry<>(3, "Master Luke");
        assertEquals(10, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Anakin", directory.put(49, "Darth Vader"));
        exp[4] = new MapEntry<>(49, "Darth Vader");
        assertEquals(10, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Obi Wan", directory.put(-25, "Old Ben"));
        exp[6] = new MapEntry<>(-25, "Old Ben");
        assertEquals(10, directory.size());
        assertArrayEquals(exp, directory.getTable());
    }

    public MapEntry<Integer, String>[] populate() {
        directory = new HashMap<>(23);
        directory.put(-2, "R2D2");
        directory.put(3, "Luke");
        directory.put(49, "Anakin");
        directory.put(-5, "Leia");
        directory.put(-25, "Obi Wan");
        directory.put(26, "Jar Jar Sucks");
        directory.put(8, "Chewie");
        directory.put(13, "Han Solo");
        directory.put(-13, "Lando");
        directory.put(15, "3PO");
        directory.put(36, "Rey");
        directory.put(38, "Finn");
        directory.put(37, "Poe");

        MapEntry<Integer, String>[] exp = (MapEntry<Integer, String>[]) (new MapEntry[23]);
        exp[2] = new MapEntry<>(-2, "R2D2");
        exp[3] = new MapEntry<>(3, "Luke");
        exp[4] = new MapEntry<>(49, "Anakin");
        exp[5] = new MapEntry<>(-5, "Leia");
        exp[6] = new MapEntry<>(-25, "Obi Wan");
        exp[7] = new MapEntry<>(26, "Jar Jar Sucks");
        exp[8] = new MapEntry<>(8, "Chewie");
        exp[13] = new MapEntry<>(13, "Han Solo");
        exp[14] = new MapEntry<>(-13, "Lando");
        exp[15] = new MapEntry<>(15, "3PO");
        exp[16] = new MapEntry<>(36, "Rey");
        exp[17] = new MapEntry<>(38, "Finn");
        exp[18] = new MapEntry<>(37, "Poe");

        return exp;
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemove() {
        MapEntry<Integer, String>[] exp =  populate();
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Chewie", directory.remove(8));
        assertEquals(12, directory.size());
        exp[8].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Poe", directory.remove(37));
        assertEquals(11, directory.size());
        exp[18].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Luke", directory.remove(3));
        assertEquals(10, directory.size());
        exp[3].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Anakin", directory.remove(49));
        assertEquals(9, directory.size());
        exp[4].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Obi Wan", directory.remove(-25));
        assertEquals(8, directory.size());
        exp[6].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Leia", directory.remove(-5));
        assertEquals(7, directory.size());
        exp[5].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("R2D2", directory.remove(-2));
        assertEquals(6, directory.size());
        exp[2].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Jar Jar Sucks", directory.remove(26));
        assertEquals(5, directory.size());
        exp[7].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Lando", directory.remove(-13));
        assertEquals(4, directory.size());
        exp[14].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("3PO", directory.remove(15));
        assertEquals(3, directory.size());
        exp[15].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Rey", directory.remove(36));
        assertEquals(2, directory.size());
        exp[16].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Finn", directory.remove(38));
        assertEquals(1, directory.size());
        exp[17].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Han Solo", directory.remove(13));
        assertEquals(0, directory.size());
        exp[13].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Jar Jar Sucks", directory.remove(26));
        assertEquals(5, directory.size());
        exp[7].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveException1() {
        directory.put(1, "Snoke");
        directory.remove(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveException2() {
        directory.put(1, "Snoke");
        directory.remove(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveException3() {
        directory.put(1, "Snoke");
        directory.put(2, "Kylo");
        directory.remove(2);
        directory.remove(2);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGet() {
        MapEntry<Integer, String>[] exp =  populate();
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Chewie", directory.get(8));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Leia", directory.get(-5));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Poe", directory.get(37));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Jar Jar Sucks", directory.get(26));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Luke", directory.remove(3));
        assertEquals(12, directory.size());
        exp[3].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Anakin", directory.get(49));
        assertEquals(12, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Obi Wan", directory.get(-25));
        assertEquals(12, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Lando", directory.remove(-13));
        assertEquals(11, directory.size());
        exp[14].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Han Solo", directory.get(13));
        assertEquals(11, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("3PO", directory.get(15));
        assertEquals(11, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Rey", directory.get(36));
        assertEquals(11, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Finn", directory.get(38));
        assertEquals(11, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.get(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetException1() {
        directory.put(1, "Snoke");
        directory.get(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetException2() {
        directory.put(1, "Snoke");
        directory.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetException3() {
        directory.put(1, "Snoke");
        directory.put(2, "Kylo");
        directory.remove(2);
        directory.get(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsKey() {
        MapEntry<Integer, String>[] exp =  populate();

        assertTrue(directory.containsKey(-2));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(3));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(49));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(-5));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(-25));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(26));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(8));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(13));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(-13));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(15));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(36));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(38));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertTrue(directory.containsKey(37));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(2));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(1));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(-49));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(69));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(72));
        assertEquals(13, directory.size());
        assertArrayEquals(exp, directory.getTable());

        assertEquals("Luke", directory.remove(3));
        assertEquals(12, directory.size());
        exp[3].setRemoved(true);
        assertArrayEquals(exp, directory.getTable());

        assertFalse(directory.containsKey(3));
        assertEquals(12, directory.size());
        assertArrayEquals(exp, directory.getTable());

        directory.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testClear() {
        MapEntry<Integer, String>[] exp =  populate();
        directory.put(69, "Yoda");
        directory.put(0, "Ahsoka");

        directory.clear();
        exp = (MapEntry<Integer, String>[]) (new MapEntry[13]);
        assertArrayEquals(exp, directory.getTable());
        assertEquals(0, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        MapEntry<Integer, String>[] exp =  populate();
        HashSet<Integer> keys = new HashSet<>();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] != null && !exp[i].isRemoved()) {
                keys.add(exp[i].getKey());
            }
        }
        assertEquals(keys, directory.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        MapEntry<Integer, String>[] exp =  populate();
        LinkedList<String> vals = new LinkedList<>();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] != null && !exp[i].isRemoved()) {
                vals.add(exp[i].getValue());
            }
        }
        assertEquals(vals, directory.values());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResizeTable() {
        MapEntry<Integer, String>[] exp =  populate();
        directory.put(23, "Windu");
        directory.resizeBackingTable(14);
        MapEntry<Integer, String>[] exp2 = (MapEntry<Integer, String>[]) new MapEntry[14];

        exp2[0] = new MapEntry<>(-13, "Lando");
        exp2[1] = new MapEntry<>(15, "3PO");
        exp2[2] = new MapEntry<>(-2, "R2D2");
        exp2[3] = new MapEntry<>(3, "Luke");
        exp2[4] = new MapEntry<>(38, "Finn");
        exp2[5] = new MapEntry<>(-5, "Leia");
        exp2[6] = new MapEntry<>(37, "Poe");
        exp2[7] = new MapEntry<>(49, "Anakin");
        exp2[8] = new MapEntry<>(8, "Chewie");
        exp2[9] = new MapEntry<>(23, "Windu");
        exp2[10] = new MapEntry<>(36, "Rey");
        exp2[11] = new MapEntry<>(-25, "Obi Wan");
        exp2[12] = new MapEntry<>(26, "Jar Jar Sucks");
        exp2[13] = new MapEntry<>(13, "Han Solo");

        assertEquals(14, directory.size());
        assertArrayEquals(exp2, directory.getTable());

        directory.resizeBackingTable(23);
        exp[0] = new MapEntry<>(23, "Windu");
        exp[13] = new MapEntry<>(-13, "Lando");
        exp[14] = new MapEntry<>(37, "Poe");
        exp[15] = new MapEntry<>(15, "3PO");
        exp[16] = new MapEntry<>(38, "Finn");
        exp[17] = new MapEntry<>(36, "Rey");
        exp[18] = new MapEntry<>(13, "Han Solo");
        assertEquals(14, directory.size());
        assertArrayEquals(exp, directory.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeTableException1() {
        MapEntry<Integer, String>[] exp =  populate();
        directory.resizeBackingTable(12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeTableException2() {
        MapEntry<Integer, String>[] exp =  populate();
        directory.resizeBackingTable(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeTableException3() {
        MapEntry<Integer, String>[] exp =  populate();
        directory.resizeBackingTable(-1);
    }

    /**
     * Method to help debug
     */
    private void printTable() {
        for (int i = 0; i < directory.getTable().length; i++) {
            if (i == directory.getTable().length - 1) {
                System.out.print(directory.getTable()[i]);
            } else {
                System.out.print(directory.getTable()[i] + ", ");
            }
        }
        System.out.println();
    }

}