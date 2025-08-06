package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiException;
import org.example.lab11.Model.Post;
import org.example.lab11.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getSinglePost(Integer id){
        Post post = postRepository.getPostsById(id);

        if (post == null){
            throw new ApiException("<post-service> post not found");
        }

        return post;
    }


    public void addPost(Post post){
        postRepository.save(post);
    }


    public void updatePost(Integer id, Post post){
        Post oldPost = postRepository.getPostsById(id);

        if (oldPost == null){
            throw new ApiException("<post-service> post not found");
        }

        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUserId(post.getUserId());
        oldPost.setPublishDate(post.getPublishDate());
        postRepository.save(oldPost);
    }


    public void deletePost(Integer id){
        Post post = postRepository.getPostsById(id);

        if (post == null){
            throw new ApiException("<post-service> post not found");
        }

        postRepository.delete(post);
    }

    public List<Post> getByCategoryId(Integer categoryId){
        return postRepository.getPostsByCategoryId(categoryId);
    }

    public List<Post> getByUserId(Integer userId){
        return postRepository.getPostsByUserId(userId);
    }

    public List<Post> getByTitle(String title){
        return postRepository.getPostsByTitle(title);
    }

    public List<Post> getByPublishDate(LocalDate from, LocalDate to){
        return postRepository.getPostsByPublishDateRenge(from, to);
    }
}
