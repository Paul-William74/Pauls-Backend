package com.example.notes.Repository;

import com.example.notes.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findUserByUsername(String Username);
}
