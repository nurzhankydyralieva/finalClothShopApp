package com.epam.project.mapper;

import com.epam.project.model.dto.TokenDto;
import com.epam.project.model.entity.Token;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenDto toDto(Token token);

    Token toEntity(TokenDto tokenDto);

    List<TokenDto> toDtos(List<Token> tokens);

    List<Token> toEntities(List<TokenDto> tokenDtos);
}
