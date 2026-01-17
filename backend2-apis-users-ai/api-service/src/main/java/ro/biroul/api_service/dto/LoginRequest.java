package ro.biroul.api_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Adresa de e-mail este obligatorie.")
    @Email(message = "Adresa de e-mail nu este validă.")
    private String email;

    @NotBlank(message = "Parola este obligatorie.")
    private String password;

    // getters & setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
