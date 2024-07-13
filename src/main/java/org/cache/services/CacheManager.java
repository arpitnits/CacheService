package org.cache.services;

import org.cache.Cache;
import org.cache.eviction.EvictionPolicy;
import org.cache.storage.StorageService;

public class CacheManager<K,V> implements Cache<K,V> {

    private final StorageService<K,V> storageService;
    private final EvictionPolicy<K> evictionPolicy;
    private final int capacity;

    public CacheManager(StorageService<K,V> inMemoryStorage, EvictionPolicy<K> evictionPolicy, int capacity) {
        this.storageService = inMemoryStorage;
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
    }

    @Override
    public void put(K key, V value) {
        if(this.storageService.size()>=capacity) {
            K evictKey = this.evictionPolicy.evict();
            this.storageService.remove(evictKey);
        }
        this.storageService.save(key, value);
        this.evictionPolicy.keyAccessed(key);
    }

    @Override
    public V get(K key) {
       V value = this.storageService.retrieve(key);
       if(value!=null) {
           this.evictionPolicy.keyAccessed(key);
       }
       return value;
    }

    @Override
    public void remove(K key) {
        this.storageService.remove(key);
        this.evictionPolicy.keyRemoved(key);
    }


}
