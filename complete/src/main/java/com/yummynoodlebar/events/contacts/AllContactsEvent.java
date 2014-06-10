package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllContactsEvent extends ReadEvent {

  private final List<ContactDetails> contactsDetails;

  public AllContactsEvent(List<ContactDetails> contacts) {
    this.contactsDetails = Collections.unmodifiableList(contacts);
  }

  public Collection<ContactDetails> getContactsDetails() {
    return this.contactsDetails;
  }
}
