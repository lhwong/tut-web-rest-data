package com.yummynoodlebar.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yummynoodlebar.persistence.domain.Contact;

public interface ContactsRepository extends CrudRepository<Contact, String> {

  

}

