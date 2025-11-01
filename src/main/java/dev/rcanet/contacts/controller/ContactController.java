package dev.rcanet.contacts.controller;

import java.util.List;

import dev.rcanet.contacts.exception.ContactNotFoundException;
import dev.rcanet.contacts.model.Contact;
import dev.rcanet.contacts.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    @Operation(summary = "Get all contacts", description = "Retrieve the full list of contacts from the database")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a contact by ID", description = "Retrieve a single contact using its unique ID")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(()-> new ContactNotFoundException(id));

        return ResponseEntity.ok(contact);
    }
    
    @PostMapping
    @Operation(summary = "Create a new contact", description = "Add a new contact to the database")
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact saved = contactRepository.save(contact);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing contact", description = "Modify the details of an existing contact")
    public ResponseEntity<Contact> updateContact(
            @PathVariable Long id, 
            @Valid @RequestBody Contact contactDetails) {

        Contact existingContact = contactRepository.findById(id)
            .orElseThrow(() -> new ContactNotFoundException(id));

        existingContact.setFirstName(contactDetails.getFirstName());
        existingContact.setLastName(contactDetails.getLastName());
        existingContact.setPhone(contactDetails.getPhone());
        existingContact.setEmail(contactDetails.getEmail());

        Contact updatedContact = contactRepository.save(existingContact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact", description = "Remove a contact by its ID")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }  

    @GetMapping("/search")
    @Operation(summary = "Search contacts", description = "Search for contacts by first name, last name, phone, or email")  
    public List<Contact> searchContacts(@RequestParam String query) {
        return contactRepository.search(query);
    }
}
