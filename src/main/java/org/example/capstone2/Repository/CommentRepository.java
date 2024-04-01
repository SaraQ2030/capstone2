package org.example.capstone2.Repository;

import org.example.capstone2.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findCommentById(Integer id);
    List<Comment> findCommentsByContentId(Integer contentId);
    List<Comment> findCommentsByUserId(Integer userId);
    List<Comment> findCommentsByCommentDate(LocalDate date);

    List<Comment> findCommentsByReview(Double review);
}
