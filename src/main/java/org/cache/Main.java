package org.cache;

import org.cache.eviction.LRUEviction;
import org.cache.services.CacheManager;
import org.cache.storage.InMemoryStorage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Cache<String, String> cache = new CacheManager<>(new InMemoryStorage<>(), new LRUEviction<>(), 2);

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        cache.get("key1");

        cache.put("key3", "value3");

        System.out.println(cache.get("key1")); // prints: value1
        System.out.println(cache.get("key2")); // prints: null (since key2 was evicted)


        cache.put("key4", "value4");


    }
}