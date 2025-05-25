package com.proj.eventease.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message= "first name required")
    @Size(min=3,message = "Min 3 characters")
    private String firstname;
    @NotBlank(message= "last name required")
    @Size(min=3,message = "Min 3 characters")
    private String lastname;
    @NotBlank(message= "Email is required")
    private String email;
    @NotBlank(message= "password is required")
    @Size(min=6,message = "Min 3 characters reqd.")
    private String password;
    @NotBlank(message= "fill your dept.")
    private String about;
    @Size(min=8,max=12,message = "Invalid number")
    private String phoneNumber;

}
