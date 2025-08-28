package com.example.demo;

import com.example.demo.Entity.CacheLayerImpl;
import com.example.demo.Entity.CacheResponse;
import com.example.demo.Service.CacheLayer;
import com.example.demo.Service.CacheStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		int numOfLayers = 3;
		int capacity[] = new int[]{3,3,3};
		int read[] = new int[]{5,20,30};
		int write[] = new int[]{10,30,40};
		String cacheStrategies[] = new String[]{"LRU","LRU","LRU"};

		MultiLevelCacheService cacheService = new MultiLevelCacheService(numOfLayers, capacity, read, write, cacheStrategies);

		System.out.println("read1: key: A");
		CacheResponse cacheResponse = cacheService.read("A");
		System.out.println("Details: => "+ cacheResponse.getKey() + " " + cacheResponse.getValue() + " "+cacheResponse.getFoundLevel() + " "+cacheResponse.getTimeTakenInMs());

		System.out.println("write: Key: A");
		long timeTakenForWrite = cacheService.write("A", "A1");
		System.out.println("Time taken for write: "+timeTakenForWrite);

		System.out.println("Read A again");
		cacheResponse = cacheService.read("A");
		System.out.println("Details: => "+ cacheResponse.getKey() + " " + cacheResponse.getValue() + " "+cacheResponse.getFoundLevel() + " "+cacheResponse.getTimeTakenInMs());

//		System.out.println("\n");
//		L2.delete("A");
//		L3.delete("A");
//		System.out.println("write: Key: A");
//		timeTakenForWrite = cacheService.write("A", "A1");
//		System.out.println("Time taken for write: "+timeTakenForWrite);
//
//		System.out.println("\n");
//		L1.delete("A");
//		L2.delete("A");
//		System.out.println("read2: key: A");
//		cacheResponse = cacheService.read("A");
//		System.out.println("Details: => "+ cacheResponse.getKey() + " " + cacheResponse.getValue() + " "+cacheResponse.getFoundLevel() + " "+cacheResponse.getTimeTakenInMs());
	}

}
