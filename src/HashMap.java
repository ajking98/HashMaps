import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value can't be "
                    + "null");

        }
        if ((double) (size + 1) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        return putHelper(key, value, hash(key));
    }

    /**
     *
     * @param key the put key
     * @param value the put value
     * @param index the put index
     * @return return the value to be placed
     */
    private V putHelper(K key, V value, int index) {
        if (table[index] == null || table[index].isRemoved()) {
            table[index] = new MapEntry(key, value);
            size++;
            return null;
        } else if (table[index].getKey().equals(key)) {
            V value1 = table[index].getValue();
            table[index].setValue(value);
            return value1;
        } else {
            return putHelper(key, value, (index + 1) % table.length);
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        V out;
        int index = Math.abs(key.hashCode()) % table.length;
        for (int i = 0; i < table.length; i++) {
            if (table[index] != null && table[index].getKey().equals(key) && (!table[index].isRemoved())) {
                out = table[index].getValue();
                table[index].setRemoved(true);
                size--;
                return out;
            }
            index = (index + 1) % table.length;
        }
        throw new NoSuchElementException("Key was not in map");
    }

    @Override
    public V get(K key) {
        checkForIllegalArgumentException(key);
        return getHelper(key, hash(key));
    }

    /**
     *
     * @param key the get key
     * @param index the remove index
     * @return return
     */
    private V getHelper(K key, int index) {
        if (table[index] == null || table[index].isRemoved()) {
            return throwNoSuchElement(key);
        } else if (table[index].getKey().equals(key)) {
            return table[index].getValue();
        } else {
            return getHelper(key, (index + 1) % table.length);
        }


    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        for (int i = 0; i < table.length; i++) {
            if (table[index] != null && table[index].getKey().equals(key) && (!table[index].isRemoved())) {
                return true;
            } else if (table[index] == null) {
                return false;
            }
            index = (index + 1) % table.length;
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> kSet = new HashSet<>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                kSet.add(table[i].getKey());
            }
        }
        return kSet;

    }

    @Override
    public List<V> values() {
        List<V> vList= new ArrayList<>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                vList.add(table[i].getValue());
            }
        }
        return vList;
    }

    @Override
    public void resizeBackingTable(int length) {
        illegalArgumentResize(length);
        size = 0;
        MapEntry<K, V>[] mapEntries = table;
        table = new MapEntry[length];
        for (MapEntry<K, V> entry : mapEntries) {
            if (entry != null && !entry.isRemoved()) {
                putHelper(entry.getKey(), entry.getValue(),
                          hash(entry.getKey()));
            }
        }
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }


    /**
     *
     * @param key the key that will go through the hach function
     * @return the index to place between 0 and the end of the list
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            return Math.abs(key.hashCode() % table.length);
        }
    }

    /**
     *
     * @param key the key to check if it is null
     */
    private void checkForIllegalArgumentException(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    /**
     *
     * @param key the key to help throw a NoSuchElementException
     * @return the exception being thrown
     */
    private V throwNoSuchElement(K key) {
        throw new NoSuchElementException("Cannot find key (" + key + ").");
    }

    /**
     *
     * @param length the length to check if it is less than the size and 0
     */
    private void illegalArgumentResize(int length) {
        if (length < 0 || length < size) {
            throw new IllegalArgumentException(
                    "Table's new length cannot be less than" + "0 or size.");
        }
    }
}
