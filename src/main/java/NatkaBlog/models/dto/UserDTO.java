package NatkaBlog.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @Email(message = "Fill in valid mail.")
    @NotBlank(message = "Fill in login email.")
    private String email;

    @NotBlank(message = "Fill in password.")
    @Size(min = 6, message = "Password must have at least 6 characters.")
    private String password;

    @NotBlank(message = "Confirm your password.")
    @Size(min = 6, message = "Password must have at least 6 characters.")
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }



}
