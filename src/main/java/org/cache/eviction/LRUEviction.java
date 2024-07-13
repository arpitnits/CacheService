package org.cache.eviction;

import java.util.LinkedHashSet;

public class LRUEviction<K> implements EvictionPolicy<K> {

    private final LinkedHashSet<K> cacheKeys;

    public LRUEviction() {
        this.cacheKeys = new LinkedHashSet<>();
    }

    @Override
    public void keyAccessed(K key) {
        this.cacheKeys.remove(key);
        this.cacheKeys.add(key);
    }

    @Override
    public K evict() {
        K key = cacheKeys.iterator().next();
        this.cacheKeys.remove(key); //remove first
        return key;
    }

    @Override
    public void keyRemoved(K key) {
        this.cacheKeys.remove(key);
    }
}
