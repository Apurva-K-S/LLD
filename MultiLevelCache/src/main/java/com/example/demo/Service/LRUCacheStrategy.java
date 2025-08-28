package com.example.demo.Service;

import com.example.demo.Entity.CacheResponse;

public class LRUCacheStrategy implements CacheStrategies{
    @Override
    public long evict(String key, String value) {
        return 0;
    }

    @Override
    public CacheResponse access(String key) {
        return null;
    }
}
