package org.example.lab11.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiResponse;
import org.example.lab11.Model.Post;
import org.example.lab11.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSinglePost(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getSinglePost(id));
    }


    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        postService.addPost(post);
        return ResponseEntity.ok(new ApiResponse("<post-controller> post added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        postService.updatePost(id,post);
        return ResponseEntity.ok(new ApiResponse("<post-controller> post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.ok(new ApiResponse("<post-controller> post deleted successfully"));
    }


    @GetMapping("/get/category")
    public ResponseEntity<?> getByCategoryId(@RequestParam Integer categoryId){
        return ResponseEntity.ok(postService.getByCategoryId(categoryId));
    }

    @GetMapping("/get/userId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer value){
        return ResponseEntity.ok(postService.getByUserId(value));
    }


    @GetMapping("/get/title")
    public ResponseEntity<?> getByTitle(@RequestParam String value){
        return ResponseEntity.ok(postService.getByTitle(value));
    }

    @GetMapping("/get/publish_date")
    public ResponseEntity<?> getByPublishDate(@RequestParam LocalDate from, @RequestParam LocalDate to){
        return ResponseEntity.ok(postService.getByPublishDate(from,to));
    }
}
