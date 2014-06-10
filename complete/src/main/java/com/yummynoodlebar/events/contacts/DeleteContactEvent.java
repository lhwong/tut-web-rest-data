package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.DeleteEvent;

import java.util.UUID;

public class DeleteContactEvent extends DeleteEvent {

  private final String key;

  public DeleteContactEvent(final String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
