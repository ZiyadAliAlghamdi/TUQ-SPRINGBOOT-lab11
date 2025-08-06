package org.example.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiResponse;
import org.example.lab11.Model.Comment;
import org.example.lab11.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/comment")

@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleComment(@PathVariable Integer id){
        return ResponseEntity.ok(commentService.getSingleComment(id));
    }


    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        commentService.addComment(comment);
        return ResponseEntity.ok(new ApiResponse("<comment-controller> comment added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.ok(new ApiResponse("<comment-controller> comment updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(new ApiResponse("<comment-controller> comment deleted successfully"));
    }

    @GetMapping("/get/user")
    public ResponseEntity<?> getByUser(@RequestParam Integer id){
        return ResponseEntity.ok(commentService.getByUser(id));
    }

    @GetMapping("/get/post")
    public ResponseEntity<?> getByPost(@RequestParam Integer id){
        return ResponseEntity.ok(commentService.getByPost(id));
    }


    @GetMapping("/get/date")
    public ResponseEntity<?> getByDate(@RequestParam LocalDate from, @RequestParam LocalDate to){
        return ResponseEntity.ok(commentService.getByCommentDate(from, to));
    }
}
