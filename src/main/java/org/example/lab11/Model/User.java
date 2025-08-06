package org.example.lab11.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "<USER>username cannot be empty")
    @Size(min = 6,max = 20, message = "<USER>username length must be between 6 and 20 chars")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;


    @NotEmpty(message = "<USER>password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*?&#]{6,}$", message = "<USER>password must be secure")
    @Size(min = 6, max = 30, message = "<USER>password length must be between 6 and 30 char")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;


    @NotEmpty(message = "<USER>email cannot be empty")
    @Email(message = "<USER>email must be valid")
    @Column(columnDefinition = "varchar(30) not null")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "<USER> date cannot be null")
    @Column(columnDefinition = "DATE not null")
    private LocalDate registrationDate; //using `LocalDate.now()` in the future
}

