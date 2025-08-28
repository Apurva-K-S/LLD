package com.example.demo.Entity;

import com.example.demo.Service.CacheLayer;
import com.example.demo.Service.CacheStrategies;

import java.util.HashMap;

public class CacheLayerImpl implements CacheLayer {
    private HashMap<String, String> cache;
    private int capcity;
    private long readTimeInMs;
    private long writeTimeInMs;
    private CacheStrategies cacheStrategies;

    public CacheLayerImpl(int capcity, long readTimeInMs, long writeTimeInMs, CacheStrategies cacheStrategies) {
        this.cache = new HashMap<>();
        this.capcity = capcity;
        this.readTimeInMs = readTimeInMs;
        this.writeTimeInMs = writeTimeInMs;
        this.cacheStrategies = cacheStrategies;
    }

    public HashMap<String, String> getCache() {
        return cache;
    }

    public void setCache(HashMap<String, String> cache) {
        this.cache = cache;
    }

    public int getCapcity() {
        return capcity;
    }

    public void setCapcity(int capcity) {
        this.capcity = capcity;
    }

    public long getReadTimeInMs() {
        return readTimeInMs;
    }

    public void setReadTimeInMs(long readTimeInMs) {
        this.readTimeInMs = readTimeInMs;
    }

    public long getWriteTimeInMs() {
        return writeTimeInMs;
    }

    public void setWriteTimeInMs(long writeTimeInMs) {
        this.writeTimeInMs = writeTimeInMs;
    }

    @Override
    public CacheResponse read(String key)
    {
        boolean found = cache.containsKey(key);
        String value = null;
        if(found)
        {
             value = cache.get(key);
        }
        return new CacheResponse(key, value, readTimeInMs, -1, found);
    }

    @Override
    public long write(String key, String value)
    {
//        return cacheStrategies.write(key, value);
        // take lock
        cache.put(key, value);
        // release lock
        return writeTimeInMs;
    }

    @Override
    public long delete(String key)
    {
        cache.remove(key);
        return writeTimeInMs;
    }
}
