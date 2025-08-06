package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiException;
import org.example.lab11.Model.Comment;
import org.example.lab11.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment getSingleComment(Integer id){
        Comment comment = commentRepository.getCommentById(id);

        if (comment == null){
            throw new ApiException("<comment-service> comment not found");
        }

        return comment;
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment){
        Comment oldComment = commentRepository.getCommentById(id);

        if (oldComment == null){
            throw new ApiException("<comment-service> comment not found");
        }

        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());
        oldComment.setContent(comment.getContent());
        oldComment.setCommentDate(comment.getCommentDate());
        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.getCommentById(id);

        if (comment == null){
            throw new ApiException("<comment-service> comment not found");
        }

        commentRepository.delete(comment);
    }

    public List<Comment> getByUser(Integer userId){
        return commentRepository.getCommentsByUserId(userId);
    }

    public List<Comment> getByPost(Integer postId){
        return commentRepository.getCommentsByPostId(postId);
    }

    public List<Comment> getByCommentDate(LocalDate from, LocalDate to){
        return commentRepository.getCommentsByCommentDateRenge(from,to);
    }
}
