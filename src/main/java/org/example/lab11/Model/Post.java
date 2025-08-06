package org.example.lab11.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "<post> categoryId cannot be null")
    @Column(columnDefinition = "int not null")   //todo: use foreign key
    private Integer categoryId;

    @NotEmpty(message = "<post> title cannot be empty")
    @Size(min=7 ,max = 30, message = "<post> title must be between 7 and 30 chars")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String title;


    @NotEmpty(message = "<post> content cannot be empty")
    @Size(max = 255,message = "<post> content cannot be more than 255 chars")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @NotNull(message = "<post> userId cannot be null")
    @Column(columnDefinition = "int not null")   //todo: use foreign key
    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE not null")
    private LocalDate publishDate;
}
