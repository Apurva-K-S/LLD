package com.example.demo;

import com.example.demo.Entity.CacheLayerImpl;
import com.example.demo.Entity.CacheResponse;
import com.example.demo.Service.CacheLayer;
import com.example.demo.Service.CacheStrategies;
import com.example.demo.Service.LRUCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiLevelCacheService {

    private List<CacheLayer> cacheLayers;

    public MultiLevelCacheService(int numOfLayers, int[] capacity, int[] read, int[] write, String []cacheStrategies) {

        List<CacheLayer> cacheLayers1 = new ArrayList<>();
        for(int i=0;i<numOfLayers;i++)
        {
            cacheLayers1.add(new CacheLayerImpl(capacity[i], read[i], write[i], getCacheStrategy(cacheStrategies[i])));
        }

        this.cacheLayers = cacheLayers1;
    }

    public CacheResponse read(String key)
    {
        long totalTime = 0;
        String foundValue = null;
        int foundLevel = -1;

        for(int i=0;i<cacheLayers.size();i++)
        {
            CacheResponse cacheResponse = cacheLayers.get(i).read(key);
            totalTime += cacheResponse.getTimeTakenInMs();
            if(cacheResponse.isFound())
            {
                foundValue = cacheResponse.getValue();
                foundLevel = i;
                break;
            }
        }

        if(foundLevel<0)
        {
            return new CacheResponse(key, null,totalTime, -1, false);
        }

        //2. write to all other layers.
        for(int i=0;i<cacheLayers.size();i++)
        {
            CacheResponse peek = cacheLayers.get(i).read(key);
            if(!peek.isFound() || !foundValue.equals(peek.getValue()))
            {
                totalTime += cacheLayers.get(i).write(key, foundValue);
            }
        }

        return new CacheResponse(key, foundValue, totalTime, foundLevel+1, true);
    }

    public long write(String key, String value)
    {
        long totalTime=0;
        for(int i=cacheLayers.size()-1;i>=0;i--)
        {
            CacheResponse peek = cacheLayers.get(i).read(key);
            totalTime += peek.getTimeTakenInMs();
            if(peek.isFound() && value.equals(peek.getValue()))
            {
                break;
            }
            totalTime += cacheLayers.get(i).write(key, value);
        }
        return totalTime;
    }

    public CacheStrategies getCacheStrategy(String cacheStrategy)
    {
        switch (cacheStrategy){
            case "LRU":
                return new LRUCacheStrategy();
        }
        return null;
    }
}
