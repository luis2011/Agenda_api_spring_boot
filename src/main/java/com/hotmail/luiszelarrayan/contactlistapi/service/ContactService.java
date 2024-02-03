package com.hotmail.luiszelarrayan.contactlistapi.service;

import com.hotmail.luiszelarrayan.contactlistapi.dto.ContactDTO;
import com.hotmail.luiszelarrayan.contactlistapi.entity.Contact;
import com.hotmail.luiszelarrayan.contactlistapi.exception.ResourceNotFoundException;
import com.hotmail.luiszelarrayan.contactlistapi.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class ContactService {


    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();

    }

    public  Contact findById(Integer id){
        return  contactRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException:: new);
    }
    public Contact create(@RequestBody ContactDTO contactDTO ){

        // ModelMapper mapper = new ModelMapper(); lo reduje con un bean
        Contact contact =  mapper.map(contactDTO, Contact.class );
        // transfiero los datos de una clase a otra asignando sus valores de forma automática
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update( Integer id , ContactDTO contactDTO){
        Contact contactFromDb = findById(id);

        // ModelMapper mapper = new ModelMapper(); lo reduje con un bean
        mapper.map(contactDTO, contactFromDb );

        return contactRepository.save(contactFromDb);
    }

    public void delete( Integer id){
        Contact contactFromDb = findById(id);

        contactRepository.delete(contactFromDb);
    }


}
