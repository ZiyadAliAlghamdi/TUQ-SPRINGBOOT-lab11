package org.example.lab11.Repository;

import org.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserById(Integer id);

    @Query("select u from User u where u.registrationDate= ?1")
    List<User> getUsersByRegistrationDate(LocalDate registrationDate);

    User getUserByUsername(String username);
}
