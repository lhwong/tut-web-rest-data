package com.yummynoodlebar.events.contacts;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestContactDetailsEvent extends RequestReadEvent {
  private String key;

  public RequestContactDetailsEvent(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
