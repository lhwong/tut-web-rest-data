package com.yummynoodlebar.events.contacts;

import java.util.UUID;

import com.yummynoodlebar.events.UpdateEvent;

public class UpdateContactEvent extends UpdateEvent {
  private final String key;

  private final ContactDetails details;

  public UpdateContactEvent(final String string, final ContactDetails details) {
    this.key = string;
    this.details = details;
  }
  
  

  public String getKey() {
	return key;
  }
  
  public ContactDetails getDetails() {
	return details;
  }
}
