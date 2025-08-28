package com.example.demo.Service;

import com.example.demo.Entity.CacheResponse;

public interface CacheStrategies {
    long evict(String key,String value);
    CacheResponse access(String key);
}
