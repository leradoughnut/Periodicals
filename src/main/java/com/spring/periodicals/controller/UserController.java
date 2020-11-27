package com.spring.periodicals.controller;

import com.spring.periodicals.dto.UserDTO;
import com.spring.periodicals.entity.User;
import com.spring.periodicals.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String sayHello() {
        return "main-page";
    }

    @GetMapping("all-users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }


    @GetMapping("/sign-up")
    public String createUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        //model.addAttribute("userDTO", new UserDTO());
        return "sign-in";
    }

    @PostMapping("/sign-up")
    public String signUp(Model model, @ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", getValidationErrors(bindingResult));
            return "sign-up";
        }
        userService.saveUser(userDTO);
        return "redirect:/sign-in";
    }

    @NotNull
    private List<String> getValidationErrors(BindingResult bindingResult) {
        return bindingResult.
                getAllErrors().
                stream().
                map(ObjectError::getDefaultMessage).
                collect(Collectors.toList());
    }
}
