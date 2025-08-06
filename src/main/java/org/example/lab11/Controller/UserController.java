package org.example.lab11.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiResponse;
import org.example.lab11.Model.User;
import org.example.lab11.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);

        return ResponseEntity.ok(new ApiResponse("<user-controller> user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        userService.updateUser(id, user);

        return ResponseEntity.ok(new ApiResponse("<user-controller> user updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("<user-service> user deleted successfully"));
    }

    @GetMapping("/get/username")
    public ResponseEntity<?> getByUsername(@RequestParam String value){
        return ResponseEntity.ok(userService.getByUsername(value));
    }

    @GetMapping("/get/register_date")
    public ResponseEntity<?> getByRegistrationDate(@RequestParam LocalDate value){
        return ResponseEntity.ok(userService.getByRegistrationDate(value));
    }
}
