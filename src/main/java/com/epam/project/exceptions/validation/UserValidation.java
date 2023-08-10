package com.epam.project.exceptions.validation;

import com.epam.project.exceptions.UserAlreadyExistsException;
import com.epam.project.exceptions.UserNotFoundException;
import com.epam.project.exceptions.enums.Code;
import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.entity.User;
import com.epam.project.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidation {

    private final Validator validator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public <T> void loginValidator(UserCreateDto userCreateDto) {
        User user = userMapper.toCreateEntity(userCreateDto);
        UserCreateDto userCreateDto1 = userMapper.toCreateDto(user);
        Set<ConstraintViolation<UserCreateDto>> result = validator.validate(userCreateDto1);
        User userName = userRepository.findByLogin(user.getLogin());

        if (userName != null) {
            String resultValidation = result.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((s1, s2) -> s1 + ". " + s2).orElse("The user name " + userName.getUsername() + " already exists in database");
            log.info("Json not valid: {}", resultValidation);
            throw UserAlreadyExistsException.builder().codeStatus(Code.USER_NAME_EXISTS).message(resultValidation)
                    .httpStatus(HttpStatus.CONFLICT).build();
        }

        if (!result.isEmpty()) {
            String resultValidations = result.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
            log.info("Json not valid: {}", resultValidations);
            throw UserAlreadyExistsException.builder().codeStatus(Code.NULL_NOT_ALLOWED).message(resultValidations)
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    public <T> void deleteValidator(UUID id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            log.info("User with id: " + id + " deleted");
        } else {
            throw new UserNotFoundException("User with id: " + id + " not available in database");
        }
    }

}
