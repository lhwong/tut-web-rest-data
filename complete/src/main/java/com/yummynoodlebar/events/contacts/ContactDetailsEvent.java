package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.ReadEvent;

import java.util.UUID;

public class ContactDetailsEvent extends ReadEvent {
  private String key;
  private ContactDetails contactDetails;

  private ContactDetailsEvent(String key) {
    this.key = key;
  }

  public ContactDetailsEvent(String key, ContactDetails contactDetails) {
    this.key = key;
    this.contactDetails = contactDetails;
  }

  public String getKey() {
    return key;
  }

  public ContactDetails getContactDetails() {
    return contactDetails;
  }

  public static ContactDetailsEvent notFound(String key) {
    ContactDetailsEvent ev = new ContactDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
