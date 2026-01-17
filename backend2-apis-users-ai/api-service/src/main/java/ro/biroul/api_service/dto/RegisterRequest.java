package ro.biroul.api_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Adresa de e-mail este obligatorie.")
    @Email(message = "Adresa de e-mail nu este validă.")
    private String email;

    @NotBlank(message = "Numele este obligatoriu.")
    private String firstName;

    @NotBlank(message = "Prenumele este obligatoriu.")
    private String lastName;

    @NotBlank(message = "Parola este obligatorie.")
    @Size(min = 8, message = "Parola trebuie să aibă minim 8 caractere.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$", message = "Parola trebuie să conțină: literă mică, literă mare, cifră și caracter special.")
    private String password;

    @NotBlank(message = "Confirmarea parolei este obligatorie.")
    private String passwordConfirm;

    // getters & setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
