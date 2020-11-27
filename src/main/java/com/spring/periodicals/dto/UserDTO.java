package com.spring.periodicals.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @Pattern(regexp = "[A-Za-z А-Яа-я]{2,30}", message = "Name must be correct!")
    private String name;
    @Email
    private String email;
    private String password;
    private RoleDTO role;
}
