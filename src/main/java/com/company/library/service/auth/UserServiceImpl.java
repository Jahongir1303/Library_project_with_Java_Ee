package com.company.library.service.auth;

import com.company.library.configs.ApplicationContextHolder;
import com.company.library.dao.AbstractDAO;
import com.company.library.dao.UserDAO;
import com.company.library.domains.User;
import com.company.library.dto.auth.UserCreateDTO;
import com.company.library.dto.auth.UserDTO;
import com.company.library.dto.auth.UserLoginDTO;
import com.company.library.dto.auth.UserUpdateDTO;
import com.company.library.exceptions.AuthenticationException;
import com.company.library.exceptions.InvalidInputException;
import com.company.library.exceptions.NotFoundException;
import com.company.library.utils.BaseUtils;

import java.lang.module.InvalidModuleDescriptorException;
import java.util.Optional;

public class UserServiceImpl extends AbstractDAO<UserDAO> implements UserService {
    private static UserServiceImpl userService;

    private final BaseUtils baseUtils = ApplicationContextHolder.getBean(BaseUtils.class);

    public UserServiceImpl() {
        super(ApplicationContextHolder.getBean(UserDAO.class));
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO) throws InvalidInputException {
        Optional<User> byEmail = dao.findByEmail(userCreateDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new InvalidInputException("User by email %s already exists".formatted(byEmail.get()));
        }

        User build = User.builder()
                .email(userCreateDTO.getEmail())
                .name(userCreateDTO.getName())
                .password(baseUtils.encode(userCreateDTO.getPassword()))
                .surname(userCreateDTO.getSurname())
                .build();

        User savedUser = dao.save(build);

        return UserDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .surname(savedUser.getSurname())
                .name(savedUser.getName())
                .build();
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(UserUpdateDTO dto) {

    }

    @Override
    public UserDTO login(UserLoginDTO dto) throws InvalidInputException, AuthenticationException {
        String email = dto.getEmail();

        Optional<User> byEmail = dao.findByEmail(email);

        User user = byEmail.orElseThrow(() -> new InvalidInputException("User not found by email %s".formatted(email)));

        if (!baseUtils.matchPassword(dto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Bad credentials");
        }


        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .status(user.getStatus())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDTO getByEmail(String email) throws NotFoundException {
        Optional<User> byEmail = dao.findByEmail(email);
        User user = byEmail.orElseThrow(() -> new NotFoundException("User not found by email"));
        return UserDTO.builder()
                .id(user.getId())
                .status(user.getStatus())
                .email(user.getEmail())
                .build();
    }

    @Override
    public Boolean hasNext(String search, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Boolean hasPrevious(Integer offset) {
        return null;
    }

    @Override
    public Integer totalPage(String search, Integer limit) {
        return null;
    }
}
