package com.company.library.domains;

import com.company.library.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.USER;
}
