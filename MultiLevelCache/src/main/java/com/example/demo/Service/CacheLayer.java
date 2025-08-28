package com.example.demo.Service;

import com.example.demo.Entity.CacheResponse;

public interface CacheLayer {
    CacheResponse read(String key);
    long write(String key, String value);
    long delete(String key);
}
