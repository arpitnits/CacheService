package org.cache.storage;

public interface StorageService<K,V> {

    void save(K key, V value);

    void remove(K key);

    V retrieve(K key);

    int size();
}
