package org.example.lab11.Repository;

import org.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment getCommentById(Integer id);

    @Query("select c from Comment c where c.userId= ?1")
    List<Comment> getCommentsByUserId(Integer userId);  //could be multiple comments

    List<Comment> getCommentsByPostId(Integer postId);


    @Query("select c from Comment c where c.commentDate BETWEEN ?1 and ?2")
    List<Comment> getCommentsByCommentDateRenge(LocalDate from, LocalDate to);
}
