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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "<comment> user id cannot be null")
    @Column(columnDefinition = "int not null")   //todo: foreign key
    private Integer userId;


    @NotNull(message = "<comment> postId cannot be null")
    @Column(columnDefinition = "int not null")   //todo: foreign key
    private Integer postId;

    @NotEmpty(message = "<comment> content cannot be empty")
    @Size(max = 255, message = "<comment> content cannot pass 255 chars")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE not null")
    private LocalDate commentDate;
}
