package com.statbroker.backend.mapper;

import com.statbroker.backend.dto.User.UserDto;
import com.statbroker.backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto dto);

}
