package com.company.library.dto.auth;


import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserCreateDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
}
