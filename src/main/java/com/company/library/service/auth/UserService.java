package com.company.library.service.auth;

import com.company.library.domains.User;
import com.company.library.dto.auth.UserCreateDTO;
import com.company.library.dto.auth.UserDTO;
import com.company.library.dto.auth.UserLoginDTO;
import com.company.library.dto.auth.UserUpdateDTO;
import com.company.library.exceptions.AuthenticationException;
import com.company.library.exceptions.InvalidInputException;
import com.company.library.exceptions.NotFoundException;

public interface UserService {
    UserDTO create(UserCreateDTO userCreateDTO) throws InvalidInputException;

    User get(long id);

    void delete(long id);

    void update(UserUpdateDTO dto);

    UserDTO login(UserLoginDTO dto) throws InvalidInputException, AuthenticationException;

    UserDTO getByEmail(String email) throws NotFoundException;
}
