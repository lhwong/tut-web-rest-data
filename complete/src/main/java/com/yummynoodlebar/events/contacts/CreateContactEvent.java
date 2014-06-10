package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.CreateEvent;

public class CreateContactEvent extends CreateEvent {
  private ContactDetails details;

  public CreateContactEvent(ContactDetails details) {
    this.details = details;
  }

  public ContactDetails getDetails() {
    return details;
  }
}
