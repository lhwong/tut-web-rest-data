package com.yummynoodlebar.persistence.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;


import com.yummynoodlebar.events.contacts.ContactDetails;

@Document(collection = "contact")
public class Contact {

	@Id
	private String id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String notes;
	// Standard Keys

	private String description;
	private String organizationName;
	private String passTypeIdentifier;
	private String serialNumber;
	private String teamIdentifier;

	//@JsonProperty("formatVersion") private final int $formatVersion = 1;

/*
	// Visual Appearance Keys

	private Barcode barcode;
	private Color backgroundColor;
	private Color foregroundColor;
	private String groupingIdentifier;
	private Color labelColor;
	private String logoText;
	@JsonInclude(Include.NON_DEFAULT) private boolean suppressStripShine = false;


	// Web Service Keys

	private String webServiceURL;
	private String authenticationToken;


	// Relevance Keys
	private boolean ignoresTimeZone = false;
	private List<Location> locations;
	private List<Beacon> beacons;
	private Integer maxDistance;
	private Date relevantDate;


	// Associated App Keys

	private List<Long> associatedStoreIdentifiers;
	private String appLaunchURL;


	// Companion App Keys

	private JsonNode userInfo;


	// Expiration Keys

	private Date expirationDate;
	@JsonInclude(Include.NON_DEFAULT) private boolean voided = false;


	@JsonIgnore private PassInformation passInformation;
	@JsonIgnore private List<NamedInputStreamSupplier> files;
*/
	
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public ContactDetails toContactDetails() {
	    ContactDetails details = new ContactDetails();

	    
	    details.setId(getId());
	    details.setFirstName(getFirstName());
	    details.setLastName(getLastName());
	    details.setEmail(getEmail());
		  

	    return details;
	  }
	
	public static Contact fromContactDetails(ContactDetails contactDetails) {
	  Contact contact = new Contact();

	  //BeanUtils.copyProperties(contactDetails, contact);
	  contact.setId(contactDetails.getId());
	  contact.setFirstName(contactDetails.getFirstName());
	  contact.setLastName(contactDetails.getLastName());
	  contact.setEmail(contactDetails.getEmail());
	  

	  return contact;
	}
	
/*	
	public List<NamedInputStreamSupplier> files() {
		if (this.files == null) {
			return Collections.emptyList();
		}
		return this.files;
	}

	public PassTemplate files(List<NamedInputStreamSupplier> values) {
		this.files = values;
		return this;
	}

	public PassTemplate files(NamedInputStreamSupplier... values) {
		this.files = Arrays.asList(values);
		return this;
	}

	public List<Location> locations() {
		return this.locations;
	}

	public PassTemplate locations(List<Location> values) {
		this.locations = values;
		return this;
	}

	public PassTemplate locations(Location... values) {
		this.locations = Arrays.asList(values);
		return this;
	}

	public List<Beacon> beacons() {
		return this.beacons;
	}

	public PassTemplate beacons(List<Beacon> values) {
		this.beacons = values;
		return this;
	}

	public PassTemplate beacons(Beacon... values) {
		this.beacons = Arrays.asList(values);
		return this;
	}

	public List<Long> associatedStoreIdentifiers() {
		return this.associatedStoreIdentifiers;
	}

	public PassTemplate associatedStoreIdentifiers(List<Long> values) {
		this.associatedStoreIdentifiers = values;
		return this;
	}

	public PassTemplate associatedStoreIdentifiers(Long... values) {
		this.associatedStoreIdentifiers = Arrays.asList(values);
		return this;
	}

	public PassTemplate relevantDate(Date relevantDate) {
		this.relevantDate = relevantDate;
		return this;
	}

	public PassTemplate relevantDate(long timestamp) {
		this.relevantDate = new Date(timestamp);
		return this;
	}

	public PassTemplate relevantDate(String iso8601dateString) {
		this.relevantDate = ISO8601Utils.parse(iso8601dateString);
		return this;
	}

	public PassTemplate expirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
		return this;
	}

	public PassTemplate expirationDate(long timestamp) {
		this.expirationDate = new Date(timestamp);
		return this;
	}

	public PassTemplate expirationDate(String iso8601dateString) {
		this.expirationDate = ISO8601Utils.parse(iso8601dateString);
		return this;
	}
*/
}
