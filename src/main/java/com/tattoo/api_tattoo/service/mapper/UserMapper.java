package com.tattoo.api_tattoo.service.mapper;

import com.tattoo.api_tattoo.controller.dto.UserDTO;
import com.tattoo.api_tattoo.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Para integração com Spring (Facade com @Component)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapeamento de UserDTO para User
    User toEntity(UserDTO userDTO);

    // Mapeamento de User para UserDTO
    UserDTO toDTO(User user);

    // Ignora os campos especificos
    @Mapping(target = "password", ignore = true)
    UserDTO toResponseDTO(User user);

}
