package com.epam.project.exceptions.model;

import com.epam.project.exceptions.enums.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    private Code code;
    private String message;
}
