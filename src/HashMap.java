import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
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
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Neither the added key ("
                    + key
                    + ") nor added value ("
                    + value
                    + ") can be null.");
        }
        if ((double) (size + 1) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        return putHelper(key, value, hash(key));
    }

    /**
     *
     * @param key
     * @param value
     * @param index
     * @return
     */
    private V putHelper(K key, V value, int index) {
        if (table[index] == null
                || table[index].isRemoved()) {
            table[index] = new MapEntry<>(key, value);
            size++;
            return null;
        } else if (table[index].getKey().equals(key)) {
            V out = table[index].getValue();
            table[index].setValue(value);
            return out;
        } else {
            return putHelper(key, value, (index + 1) % table.length);
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        System.out.println("Removing <" + key + ">");
        return remove(key, hash(key));
    }

    /**
     *
     * @param key
     * @param index
     * @return
     */
    private V remove(K key, int index) {
        if (table[index] == null
                || table[index].isRemoved()) {
            throw new NoSuchElementException("Cannot find key ("
                    + key
                    + ").");
        } else if (table[index].getKey().equals(key)) {
            V out = table[index].getValue();
            table[index].setRemoved(true);
            size--;
            return out;
        } else {
            return remove(key, (index + 1) % table.length);
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                    "Key cannot be null.");
        }
        return get(key, hash(key));
    }

    /**
     *
     * @param key
     * @param index
     * @return
     */
    private V get(K key, int index) {
        if (table[index] == null
                || table[index].isRemoved()) {
            throw new NoSuchElementException("Cannot find key ("
                    + key
                    + ").");
        } else if (table[index].getKey().equals(key)) {
            return table[index].getValue();
        } else {
            return get(key, (index + 1) % table.length);
        }
    }

    @Override
    public boolean containsKey(K key) {
        try {
            get(key);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
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
        Set<K> keys = new HashSet<K>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null
                    && !entry.isRemoved()) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isRemoved()) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < 0 || length < size) {
            throw new IllegalArgumentException(
                    "Table's new length cannot be less than"
                            + "0 or size.");
        }
        size = 0;
        MapEntry<K, V>[] temp = table;
        table = new MapEntry[length];
        for (MapEntry<K, V> entry : temp) {
            if (entry != null && !entry.isRemoved()) {
                putHelper(entry.getKey(), entry.getValue(), hash(entry
                        .getKey()));
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
     * @param key
     * @return
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            return Math.abs(key.hashCode() % table.length);
        }
    }
}
