package com.epam.project.exceptions.validation.order_validation;

import com.epam.project.exceptions.CommonException;
import com.epam.project.exceptions.StatusNotAllowedException;
import com.epam.project.exceptions.enums.Code;
import com.epam.project.mapper.OrderMapper;
import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Status;
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
public class OrderSaveValidator {
    private final Validator validator;
    private final UserRepository userRepository;
    public <T> void orderSaveValidation(OrderDto orderDto, UUID id) {
        User user = userRepository.findUserById(id);
        Status userStatus = user.getStatus();
        if (id != null) {
            Set<ConstraintViolation<UUID>> result = validator.validate(id);
            if (!userStatus.equals(Status.ACTIVE)) {
                String resultValidations = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("Status not ACTIVE");
                log.info("Your: {}", resultValidations);
                throw StatusNotAllowedException.builder().codeStatus(Code.STATUS_VALIDATION_ERROR).message("Your status is " + userStatus + " you are not allowed to create new order")
                        .httpStatus(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
    }
}
