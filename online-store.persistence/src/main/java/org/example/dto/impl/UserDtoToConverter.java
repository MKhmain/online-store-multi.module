package org.example.dto.impl;


import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.entities.impl.DefaultUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDtoToConverter {
    private final RoleDtoToConverter roleConverter;
    {
        roleConverter=new RoleDtoToConverter();
    }
    public UserDto convertUserToUserDtoWithId(int id){
        UserDto userDto=new UserDto();
        userDto.setId(id);
        return userDto;
    }
    public User convertUserDtoToUser(UserDto userDto){
        if(userDto==null){
            return null;
        }
        User user=new DefaultUser();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setMoney(userDto.getMoney().doubleValue());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCreditCard(userDto.getCreditCard());
        if(userDto.getRole()!=null) {
            user.setRoleName(userDto.getRole().getRoleName());
        }
        return user;
    }
    public UserDto convertUserToUserDto(User user){
        if(user==null){
            return null;
        }
        UserDto userDto=new UserDto();
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setPassword(user.getPassword());
        userDto.setMoney(BigDecimal.valueOf(user.getMoney()));
        userDto.setEmail(user.getEmail());
        userDto.setCreditCard(user.getCreditCard());
        userDto.setRole(roleConverter.convertRoleNameToRoleDto(user.getRoleName()));
        return userDto;
    }
    public List<User> convertUserDtosToUsers(List<UserDto> userDtos){
        List<User> list=new ArrayList<>();
        for(var userDto: userDtos)
        {
            list.add(convertUserDtoToUser(userDto));
        }
        return  list;
    }
}
