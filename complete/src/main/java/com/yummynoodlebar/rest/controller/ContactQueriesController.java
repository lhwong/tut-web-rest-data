package com.yummynoodlebar.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yummynoodlebar.core.domain.Contact;
import com.yummynoodlebar.core.services.ContactService;
import com.yummynoodlebar.core.services.OrderService;
import com.yummynoodlebar.events.contacts.ContactDetails;
import com.yummynoodlebar.events.contacts.ContactDetailsEvent;
import com.yummynoodlebar.events.contacts.RequestAllContactsEvent;
import com.yummynoodlebar.events.contacts.RequestContactDetailsEvent;
import com.yummynoodlebar.events.orders.OrderDetails;
import com.yummynoodlebar.events.orders.OrderDetailsEvent;
import com.yummynoodlebar.events.orders.RequestAllOrdersEvent;
import com.yummynoodlebar.events.orders.RequestOrderDetailsEvent;
import com.yummynoodlebar.rest.domain.Order;

@Controller
@RequestMapping("/contacts")
public class ContactQueriesController {

	@Autowired
    private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Iterable<Contact>> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        
        for (ContactDetails detail : contactService.requestAllContacts(new RequestAllContactsEvent()).getContactsDetails()) {
            contacts.add(Contact.fromContactDetails(detail));
        }
        Map<String, Iterable<Contact>> map = new HashMap<String, Iterable<Contact>>();
    	map.put("contacts",  contacts);
        return map;
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public  ResponseEntity<Map<String, Contact>> getContact(@PathVariable String id) {
		ContactDetailsEvent contactDetailsEvent = contactService.requestContactDetails(new RequestContactDetailsEvent(id));

		if (!contactDetailsEvent.isEntityFound()) {
			throw new EntityNotFoundException();
			//return new ResponseEntity<Map<String, Contact>>(HttpStatus.NOT_FOUND);
		}
        Map<String, Contact> map = new HashMap<String, Contact>();
    	map.put("contact",  Contact.fromContactDetails(contactDetailsEvent.getContactDetails()));
        return new ResponseEntity<Map<String, Contact>>(map, HttpStatus.OK);
    }
}
