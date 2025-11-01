package dev.rcanet.contacts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Contact entity representing a person in the contact list")
@Entity
@Table(name = "contacts")
public class Contact {

    @Schema(description = "Unique identifier", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "First name of the contact", example = "John")
    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;
    
    @Schema(description = "Last name of the contact", example = "Doe")
    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Schema(description = "Phone number", example = "+34 600 123 456")
    @Pattern(regexp = "^[0-9\\-+() ]*$", message = "Phone number contains invalid characters")
    private String phone;
    
    @Schema(description = "Email address", example = "john.doe@example.com")
    @Email(message = "Invalid email format")
    private String email;

    public Contact() {}

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {  
        return id;
    }

    public void setId(Long id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
