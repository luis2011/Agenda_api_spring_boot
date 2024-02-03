package com.hotmail.luiszelarrayan.contactlistapi.repository;

import com.hotmail.luiszelarrayan.contactlistapi.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository <Contact, Integer> {
}
