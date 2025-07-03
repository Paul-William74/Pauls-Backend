package com.example.notes.Mapper;

import com.example.notes.DTOs.User.LoggedInUser;
import com.example.notes.DTOs.User.RegisteringUser;
import com.example.notes.DTOs.User.UserDTO;
import com.example.notes.Model.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper  {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /**
     * Converts a RegisteringUser DTO to a User entity.
     *
     * @param user the RegisteringUser DTO to convert
     * @return the converted User entity
     */
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    public abstract User toRegisterEntity(RegisteringUser user);

    public abstract LoggedInUser toLoggedInUser(User user);

    public  abstract List<UserDTO> UserListDTO(List<User> users);
    /**
     * Encodes the given password using the configured password encoder.
     *
     * @param password the plain text password to encode
     * @return the encoded password
     */
    @Named("encodePassword")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
