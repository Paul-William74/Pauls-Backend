package com.example.notes.Service;

import com.example.notes.DTOs.User.LoggedInUser;
import com.example.notes.DTOs.User.LoginRequest;
import com.example.notes.DTOs.User.RegisteringUser;
import com.example.notes.Mapper.UserMapper;
import com.example.notes.Model.User.User;
import com.example.notes.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    /**
     * Logs in a user.
     * @param loginUser is the received user from the frontend
     * @return a response entity with the login result
     */
    public ResponseEntity<?> login(LoginRequest loginUser) {
        //TODO: login needs implementation

        //Finds user from database by their identification number
        Optional<User> DbUser= userRepo.findUserByUsername(loginUser.getUsername());


        if(DbUser.isEmpty()){
            return  new ResponseEntity<>("User does not exist in database", HttpStatus.NOT_FOUND);
        }
        User user=DbUser.get();
        //Another option is password Encoder
        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }


        LoggedInUser loggedInUser=userMapper.toLoggedInUser(user);
        return ResponseEntity.ok(loggedInUser);
    }
    //Just wanted to check if showed registered users from database
    public ResponseEntity<?>getAllUsers(){
        List<User> users=userRepo.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>("No List",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(users,HttpStatus.OK);

        }

}