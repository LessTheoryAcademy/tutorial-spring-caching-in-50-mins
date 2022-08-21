package com.lta.springcaching;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SpringCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCachingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(MessageService messageService, CacheManager cacheManager) {
		
		return args -> {
			
			Cache cache = cacheManager.getCache("messages");
		
			System.out.println(new Date().toString() + " Start...");
			
			String messageFound = messageService.getMessageToStringByTitle("title 1");			
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);
			
			messageFound = messageService.getMessageToStringByTitle("title 1");			
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);
			
			messageFound = messageService.getMessageToStringByTitle("title 2");			
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);	

			// -------
			
			messageService.updateAllEntriesText("update-0");	
			System.out.println(new Date().toString() + " update all entries done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");			
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);	
			
			// CacheEvict
			System.out.println("\n\nCacheEvict...");
			
			messageService.clearCache();	
			System.out.println(new Date().toString() + " clear cache done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);	
			
			messageFound = messageService.getMessageToStringByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);
			
			// CacheEvict by title
			System.out.println("\n\nCacheEvict by title...");
			
			messageService.updateAllEntriesText("update-1");	
			System.out.println(new Date().toString() + " update all entries done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");	
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);	
			
			messageFound = messageService.getMessageToStringByTitle("title 2");
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);	
			
			// -----
			
			messageService.updateAllEntriesText("update-2");	
			System.out.println(new Date().toString() + " update all entries done");

			messageService.clearCacheByTitle("title 1");
			System.out.println("clear cache by title done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");	
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);
			
			messageFound = messageService.getMessageToStringByTitle("title 2");
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);	
			
			// ---------------
			
			// CachePut
			System.out.println("\n\nCachePut...");
			
			messageService.clearCache();
			System.out.println(new Date().toString() + " clear cache done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);		
			
			messageFound = messageService.getMessageToStringByTitle("title 2");
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);			
			
			messageService.updateAllEntriesText("update-3");			
			System.out.println(new Date().toString() + " update all entries done");
			
			messageFound = messageService.getMessageToStringByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);		
			
			messageFound = messageService.getMessageToStringByTitle("title 2");
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);
			
			// ----
			
			messageFound = messageService.getAndUpdateCacheByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound + " - getAndUpdateCacheByTitle");
			System.out.println("get and update cache by title done");
			
			messageFound = messageService.getAndUpdateCacheByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound + " - getAndUpdateCacheByTitle");
			System.out.println("get and update cache by title done");	
			
			messageFound = messageService.getMessageToStringByTitle("title 1");
			System.out.println(new Date().toString() + " Message found title 1 " + messageFound);		
			
			messageFound = messageService.getMessageToStringByTitle("title 2");
			System.out.println(new Date().toString() + " Message found title 2 " + messageFound);			
		};
	}	

}
