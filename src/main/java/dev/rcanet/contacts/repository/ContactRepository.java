package dev.rcanet.contacts.repository;

import dev.rcanet.contacts.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByFirstName(String firstName);

    List<Contact> findByLastName(String lastName);

    List<Contact> findByFirstNameContaining(String partialName);

    List<Contact> findByLastNameContaining(String partialLastName);

    @Query("""
        SELECT c FROM Contact c
        WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<Contact> search(@Param("query") String query);
}
