package org.cache.storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<K,V> implements StorageService<K,V> {

    private final Map<K, V> storageMap;

    public InMemoryStorage() {
        this.storageMap = new HashMap<>();
    }

    @Override
    public void save(K key, V value) {
        this.storageMap.put(key, value);
    }

    @Override
    public void remove(K key) {
        this.storageMap.remove(key);
    }

    @Override
    public V retrieve(K key) {
        return this.storageMap.get(key);
    }

    @Override
    public int size() {
        return this.storageMap.size();
    }
}
