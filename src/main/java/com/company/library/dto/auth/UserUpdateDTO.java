package com.company.library.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;

}
