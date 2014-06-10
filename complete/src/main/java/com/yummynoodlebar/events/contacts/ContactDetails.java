package com.yummynoodlebar.events.contacts;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class ContactDetails {

  private String id;
  
  private String firstName;
	
  private String lastName;
	
  private String email;
	
  private String notes;
  

  public ContactDetails() {
    id = null;
  }

  public ContactDetails(String id) {
    this.id = id;
  }


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
}
