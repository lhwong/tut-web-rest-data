package com.yummynoodlebar.core.services;

import com.yummynoodlebar.events.contacts.*;

//TODOCUMENT THis is an event driven service.
// Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface ContactService {

  public AllContactsEvent requestAllContacts(RequestAllContactsEvent requestAllCurrentContactsEvent);

  public ContactDetailsEvent requestContactDetails(RequestContactDetailsEvent requestContactDetailsEvent);

  
  public ContactCreatedEvent createContact(CreateContactEvent event);

  public ContactUpdatedEvent updateContact(UpdateContactEvent event);
 
  public ContactDeletedEvent deleteContact(DeleteContactEvent deleteContactEvent);
}
