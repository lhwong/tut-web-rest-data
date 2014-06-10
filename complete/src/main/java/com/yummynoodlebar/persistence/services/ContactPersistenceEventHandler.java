package com.yummynoodlebar.persistence.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yummynoodlebar.events.contacts.*;
import com.yummynoodlebar.persistence.domain.Contact;
import com.yummynoodlebar.persistence.repository.ContactsRepository;
import com.yummynoodlebar.web.controller.SiteController;



public class ContactPersistenceEventHandler implements ContactPersistenceService {

  private static final Logger LOG = LoggerFactory.getLogger(ContactPersistenceEventHandler.class);
	
  private final ContactsRepository contactRepository;
  

  public ContactPersistenceEventHandler(
      final ContactsRepository contactRepository) {
    this.contactRepository = contactRepository;
    
  }

  

  @Override
  public ContactCreatedEvent createContact(CreateContactEvent createContactEvent) {
    Contact contact = Contact.fromContactDetails(createContactEvent.getDetails());
    
    contact = contactRepository.save(contact);
    
    return new ContactCreatedEvent(contact.getId(), contact.toContactDetails());
  }

  @Override
  public AllContactsEvent requestAllContacts(RequestAllContactsEvent requestAllCurrentContactsEvent) {
    List<ContactDetails> generatedDetails = new ArrayList<ContactDetails>();
    for (Contact contact : contactRepository.findAll()) {
      generatedDetails.add(contact.toContactDetails());
    }
    return new AllContactsEvent(generatedDetails);
  }

  @Override
  public ContactDetailsEvent requestContactDetails(RequestContactDetailsEvent requestContactDetailsEvent) {

    Contact contact = contactRepository.findOne(requestContactDetailsEvent.getKey().toString());

    if (contact == null) {
      return ContactDetailsEvent.notFound(requestContactDetailsEvent.getKey());
    }

    return new ContactDetailsEvent(
            requestContactDetailsEvent.getKey(),
            contact.toContactDetails());
  }

  

  @Override
  public ContactDeletedEvent deleteContact(DeleteContactEvent deleteContactEvent) {

    Contact contact = contactRepository.findOne(deleteContactEvent.getKey().toString());

    if (contact == null) {
      return ContactDeletedEvent.notFound(deleteContactEvent.getKey());
    }

    contactRepository.delete(deleteContactEvent.getKey().toString());

    return new ContactDeletedEvent(deleteContactEvent.getKey(), contact.toContactDetails());
  }



  @Override
  public ContactUpdatedEvent updateContact(UpdateContactEvent event) {
	Contact contact = Contact.fromContactDetails(event.getDetails());
	contact.setId(event.getKey());
	   
	contact = contactRepository.save(contact);
	    
	return new ContactUpdatedEvent(contact.getId(), contact.toContactDetails());


  }

  
}
