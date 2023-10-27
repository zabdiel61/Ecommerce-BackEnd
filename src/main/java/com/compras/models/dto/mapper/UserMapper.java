package com.compras.models.dto.mapper;

import org.mapstruct.Mapper;

import com.compras.models.dto.UserDto;
import com.compras.models.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toUserDto(User user);

}
