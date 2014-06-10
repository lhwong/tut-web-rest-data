package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.UpdatedEvent;

import java.util.UUID;

public class ContactUpdatedEvent extends UpdatedEvent {

  private String key;
  private ContactDetails contactDetails;

  public ContactUpdatedEvent(String key, ContactDetails contactDetails) {
    this.key = key;
    this.contactDetails = contactDetails;
  }

  public ContactUpdatedEvent(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public ContactDetails getContactDetails() {
    return contactDetails;
  }

  public static ContactUpdatedEvent notFound(String key) {
    ContactUpdatedEvent ev = new ContactUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
