package NatkaBlog.controllers;

import NatkaBlog.models.dto.UserDTO;
import NatkaBlog.models.exceptions.DuplicateEmailException;
import NatkaBlog.models.exceptions.PasswordsDoNotEqualException;
import NatkaBlog.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String renderLogin() {
        return "pages/account/login";
    }

    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {
        return "pages/account/register";
    }

    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors()){
            return renderRegister(userDTO);
        }

        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e){
            result.rejectValue("email", "error", "This email is already used.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "Passwords must equal.");
            result.rejectValue("confirmPassword", "error", "Passwords must equal.");
            return "/pages/account/register";
        }

        redirectAttributes.addFlashAttribute("success", "User registered.");
        return "redirect:/account/login";
    }
}
