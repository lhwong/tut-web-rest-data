package com.yummynoodlebar.core.services;

import com.yummynoodlebar.core.domain.Contact;
import com.yummynoodlebar.events.contacts.*;
import com.yummynoodlebar.persistence.services.ContactPersistenceService;

import java.util.Date;
import java.util.UUID;

public class ContactEventHandler implements ContactService {

  private final ContactPersistenceService contactsPersistenceService;

  public ContactEventHandler(final ContactPersistenceService contactsPersistenceService) {
    this.contactsPersistenceService = contactsPersistenceService;
  }

  @Override
  public ContactCreatedEvent createContact(CreateContactEvent createContactEvent) {

    //TODO, add validation of menu items
    //TODO, add contact total calculation
    //TODO, add contact time estimate calculation
	//TODO  Think transaction boundary. Contact and ContactStatus should be atomic
    ContactCreatedEvent event = contactsPersistenceService.createContact(createContactEvent);

   

    return event;
  }

  @Override
  public AllContactsEvent requestAllContacts(RequestAllContactsEvent requestAllCurrentContactsEvent) {
    return contactsPersistenceService.requestAllContacts(requestAllCurrentContactsEvent);
  }

  @Override
  public ContactDetailsEvent requestContactDetails(RequestContactDetailsEvent requestContactDetailsEvent) {
    return contactsPersistenceService.requestContactDetails(requestContactDetailsEvent);
  }


  @Override
  public ContactDeletedEvent deleteContact(DeleteContactEvent deleteContactEvent) {

    ContactDetailsEvent contactDetailsEvent = contactsPersistenceService.requestContactDetails(new RequestContactDetailsEvent(deleteContactEvent.getKey()));

    if (!contactDetailsEvent.isEntityFound()) {
      return ContactDeletedEvent.notFound(deleteContactEvent.getKey());
    }

    Contact contact = Contact.fromContactDetails(contactDetailsEvent.getContactDetails());

    
        

    contactsPersistenceService.deleteContact(deleteContactEvent);

    return new ContactDeletedEvent(deleteContactEvent.getKey(), contact.toContactDetails());
  }

  @Override
  public ContactUpdatedEvent updateContact(UpdateContactEvent updateContactEvent) {
	
	  
	ContactUpdatedEvent event = contactsPersistenceService.updateContact(updateContactEvent);
	  
	return event;
  }

  
}
