package school.sptech.loginormyou2up.service.extra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashObj<K, V> {
    private static final int TABLE_SIZE = 31;
    private List<LinkedList<HashEntry<K, V>>> table;

    public HashObj() {
        table = new ArrayList<>(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            table.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int index = calculateIndex((Integer) key);
        LinkedList<HashEntry<K, V>> list = table.get(index);


        for (HashEntry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }


        HashEntry<K, V> entry = new HashEntry<>(key, value);
        list.add(entry);
    }

    public V get(int key) {
        int index = calculateIndex(key);
        LinkedList<HashEntry<K, V>> list = table.get(index);


        for (HashEntry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    private int calculateIndex(int key) {
        return key % TABLE_SIZE;
    }
}

class HashEntry<K, V> {
    private final K key;
    private V value;

    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
