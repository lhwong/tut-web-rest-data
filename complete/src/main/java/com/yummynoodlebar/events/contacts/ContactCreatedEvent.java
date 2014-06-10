package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.CreatedEvent;

import java.util.UUID;

public class ContactCreatedEvent extends CreatedEvent {

  private final String newOrderKey;
  private final ContactDetails details;

  public ContactCreatedEvent(final String string, final ContactDetails details) {
    this.newOrderKey = string;
    this.details = details;
  }

  public ContactDetails getDetails() {
    return details;
  }

  public String getNewContactKey() {
    return newOrderKey;
  }
}
