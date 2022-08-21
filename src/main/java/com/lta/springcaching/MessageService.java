package com.lta.springcaching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
	
	private List<Message> messagesList;
	
	public MessageService() {
		
		this.messagesList = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			
			Message message = new Message();
			message.setTitle("title " + i);
			message.setText("text " + i);
			
			this.messagesList.add(message);
		}
	}

	@Cacheable(value="messages")
	public String getMessageToStringByTitle(String title) {

		slow();
		
		for (Message message : this.messagesList) {

			if (message.getTitle().equals(title)) {

				return message.toString();
			}
		}

		return null;
	}
	
	private void slow() {
		
		try {
			
			Thread.sleep(4000L);
			
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}	
	
	public void updateAllEntriesText(String suffix) {
		
		int i = 0;
		
		for (Message message : this.messagesList) {

			message.setText("text " + i + " " + suffix);
			i++;
		}		
	}	
	
	@CacheEvict(value="messages", allEntries=true)
	public void clearCache() {
		// blank method
	}	
	
	@CacheEvict(value="messages")
	public void clearCacheByTitle(String title) {
		// blank method
	}	
	
	@CachePut(value="messages")
	public String getAndUpdateCacheByTitle(String title) {
		
		slow();

		for (Message message : this.messagesList) {

			if (message.getTitle().equals(title)) {

				return message.toString();
			}
		}

		return null;
	}	
}