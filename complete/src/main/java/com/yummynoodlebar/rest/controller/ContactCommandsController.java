package com.yummynoodlebar.rest.controller;


import com.yummynoodlebar.core.domain.Contact;
import com.yummynoodlebar.core.services.ContactService;
import com.yummynoodlebar.events.contacts.ContactCreatedEvent;
import com.yummynoodlebar.events.contacts.ContactDeletedEvent;
import com.yummynoodlebar.events.contacts.ContactUpdatedEvent;
import com.yummynoodlebar.events.contacts.CreateContactEvent;
import com.yummynoodlebar.events.contacts.DeleteContactEvent;
import com.yummynoodlebar.events.contacts.UpdateContactEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/contacts")
public class ContactCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(ContactCommandsController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Contact>> createContact(@RequestBody Contact contact, UriComponentsBuilder builder) {
        
        ContactCreatedEvent contactCreated = contactService.createContact(new CreateContactEvent(contact.toContactDetails()));

        Contact newContact = Contact.fromContactDetails(contactCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/contacts/{id}")
                        .buildAndExpand(contactCreated.getNewContactKey().toString()).toUri());

        Map<String, Contact> map = new HashMap<String, Contact>();
    	map.put("contact", newContact);
    	
        return new ResponseEntity<Map<String, Contact>>(map, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Contact> cancelContact(@PathVariable String id) {

        ContactDeletedEvent contactDeleted = contactService.deleteContact(new DeleteContactEvent(id));

        
        if (!contactDeleted.isEntityFound()) {
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }

        Contact contact = Contact.fromContactDetails(contactDeleted.getDetails());
        
        if (contactDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Contact>(HttpStatus.OK);
        }

        return new ResponseEntity<Contact>(contact, HttpStatus.FORBIDDEN);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Map<String, Contact>> updateContact(@RequestBody Contact contact, @PathVariable String id) {
        
        ContactUpdatedEvent contactUpdated = contactService.updateContact(new UpdateContactEvent(id, contact.toContactDetails()));

        Contact updatedContact = Contact.fromContactDetails(contactUpdated.getContactDetails());

        

        Map<String, Contact> map = new HashMap<String, Contact>();
    	map.put("contact", updatedContact);
    	
        return new ResponseEntity<Map<String, Contact>>(map, HttpStatus.OK);
    }
}
