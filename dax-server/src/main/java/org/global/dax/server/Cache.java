package org.global.dax.server;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

final class Cache {

    private static final ConcurrentHashMap<String, String> MAP_CACHE = new ConcurrentHashMap<>();

    static String getSingle(String key) {
        return MAP_CACHE.get(key);
    }

    static Set<String> getAll() {
        return Set.copyOf(MAP_CACHE.keySet());
    }

    static String addSingle(String key, String value) {
        return MAP_CACHE.put(key, value);
    }

    static String deleteSingle(String key) {
        return MAP_CACHE.remove(key);
    }

}
