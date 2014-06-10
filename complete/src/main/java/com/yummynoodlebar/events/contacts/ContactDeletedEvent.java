package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.DeletedEvent;

import java.util.UUID;

public class ContactDeletedEvent extends DeletedEvent {

  private String key;
  private ContactDetails details;
  private boolean deletionCompleted;

  private ContactDeletedEvent(String key) {
    this.key = key;
  }

  public ContactDeletedEvent(String key, ContactDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public String getKey() {
    return key;
  }

  public ContactDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static ContactDeletedEvent deletionForbidden(String key, ContactDetails details) {
    ContactDeletedEvent ev = new ContactDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static ContactDeletedEvent notFound(String key) {
    ContactDeletedEvent ev = new ContactDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
