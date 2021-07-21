package com.esh.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCacheTest {

	//if cached, return; otherwise create, cache and return
	public static void main(String[] args) throws ExecutionException {
		//Loading Cache
		LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000)
				//different types of eviction
				//1.Time based eviction
				//.expireAfterAccess(TimeUnit.MINUTES)
				//.expireAfterWrite(10, TimeUnit.MINUTES)
				//2.Size based eviction
				//.maximumSize(1000)
				//.maximumWeight(1000)
				
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) {
						return "hi";
					}
				});	
		System.out.println(cache.get("A")); //Cache miss - update
		System.out.println(cache.get("A")); //Cache hit
		
		
		//Cache from  callable
		System.out.println(cache.get("B", new Callable<String>(){
			@Override
			public String call() throws Exception {
				return "from callable";
			}
		}));
		
		
		//Inserted directly
		cache.put("A", "hii");
		System.out.println(cache.get("A"));
	}
}
