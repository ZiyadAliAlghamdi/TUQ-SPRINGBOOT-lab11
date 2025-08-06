package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiException;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        User user = userRepository.getUserById(id);

        if (user == null){
            throw new ApiException("<user-service> user not found");
        }

        return user;
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.getUserById(id);

        if (oldUser == null){
            throw new ApiException("<user-service> user not found");
        }

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRegistrationDate(user.getRegistrationDate());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.getUserById(id);

        if (user == null){
            throw new ApiException("<user-service> user not found");
        }

        userRepository.delete(user);
    }

    public User getByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public List<User> getByRegistrationDate(LocalDate date){
        return userRepository.getUsersByRegistrationDate(date);
    }
}
