package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.events.contacts.*;

public interface ContactPersistenceService {

  public AllContactsEvent requestAllContacts(RequestAllContactsEvent requestAllCurrentContactsEvent);

  public ContactDetailsEvent requestContactDetails(RequestContactDetailsEvent requestContactDetailsEvent);


  public ContactCreatedEvent createContact(CreateContactEvent event);



  public ContactDeletedEvent deleteContact(DeleteContactEvent deleteContactEvent);
  
  
  public ContactUpdatedEvent updateContact(UpdateContactEvent event);

}
