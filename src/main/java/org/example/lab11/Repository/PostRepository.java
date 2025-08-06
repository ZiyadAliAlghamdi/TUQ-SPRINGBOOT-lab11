package org.example.lab11.Repository;

import org.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post getPostsById(Integer id);



    @Query("select p from Post p where p.categoryId= ?1")
    List<Post> getPostsByCategoryId(Integer categoryId);

    @Query("select p from Post p where p.userId = ?1")
    List<Post> getPostsByUserId(Integer userId);

    @Query("select p from Post p where p.title= ?1")
    List<Post> getPostsByTitle(String title);


    @Query("select p from Post p where p.publishDate BETWEEN ?1 and ?2")
    List<Post> getPostsByPublishDateRenge(LocalDate from, LocalDate to);


}
