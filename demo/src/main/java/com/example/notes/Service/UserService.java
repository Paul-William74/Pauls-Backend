package com.example.notes.Service;

import com.example.notes.DTOs.User.LoggedInUser;
import com.example.notes.DTOs.User.RegisteringUser;
import com.example.notes.Mapper.UserMapper;
import com.example.notes.Model.User.User;
import com.example.notes.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling user-related business logic.
 * <p>
 * Provides methods for user registration and login.
 * </p>
 */
@Service
public class UserService {


    /**
     * Mapper for converting between user DTOs and entities.
     * <p>
     * Used to map data between the user model and the DTOs.
     * </p>
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * Repository for user data operations.
     */
    @Autowired
    private UserRepo userRepo;

    /**
     * Registers a new user.
     *
     * @return a response entity with the registration result
     */
    public ResponseEntity<?> register(RegisteringUser user) {
        User newUser = userMapper.toRegisterEntity(user); //returns a new user entity from the DTO
        User savedUser = this.userRepo.save(newUser); //save user to the database
        LoggedInUser loggedInUser = userMapper.toLoggedInUser(savedUser); //convert the saved user to a LoggedInUser DTO
        return ResponseEntity.ok(loggedInUser);
    }
    public ResponseEntity<?> findByUsername(String Username){
        User user=userRepo.findUserByUsername(Username);

        if (user==null){
            return  new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }
    /**
     * Logs in a user.
     * @param user is the received user from the frontend
     * @return a response entity with the login result
     */
    public ResponseEntity<?> login(User user) {
        //TODO: login needs implementation
        //Checks if user exists
        if (user==null||user.getUsername()==null||user.getPassword()==null){
            return new ResponseEntity<>("User is invalid", HttpStatus.BAD_REQUEST);
        }

        //Finds user from database by their identification number
        Optional<User> DbUser= Optional.ofNullable(userRepo.findUserByUsername(user.getUsername()));


        if(DbUser.isEmpty()){
            return  new ResponseEntity<>("User does not exist in database", HttpStatus.NOT_FOUND);
        }
        //Another option is password Encoder
        if (!user.getPassword().matches(DbUser.get().getPassword())){
           return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }

        LoggedInUser loggedInUser=userMapper.toLoggedInUser(DbUser.get());
        return ResponseEntity.ok(loggedInUser);
    }
}