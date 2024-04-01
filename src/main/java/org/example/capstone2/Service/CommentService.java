package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.Comment;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Model.Purches;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.CommentRepository;
import org.example.capstone2.Repository.ContentRepository;
import org.example.capstone2.Repository.PurchesRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PurchesRepository purchesRepository;
    private final ContentRepository contentRepository;


    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }
    public void addComment(Comment comment){
      User u=userRepository.findUserById(comment.getUserId());
        Content c=contentRepository.findContentById(comment.getContentId());
        Purches p=purchesRepository.findPurchesById(comment.getPurchesId());

        if (u==null && c==null && p==null){
            throw new RuntimeException("not found user id or content id");
        }
        if(! comment.getCommentDate().isEqual(p.getEndDate()) ){
            throw new ApiException("try our service before adding comment");
        }
        if(comment.getCommentDate().isBefore(p.getEndDate()) ){
            throw new ApiException("try our service before adding comment");
        }

        commentRepository.save(comment);
        //update content review
        Content content=contentRepository.findContentById(comment.getContentId());
        content.setReviews(reviewAvrge(comment.getContentId()));
        commentRepository.save(comment);
        //update purches status
        Purches purches=purchesRepository.findPurchesById(comment.getPurchesId());
        purches.setStatus("done");
        purchesRepository.save(purches);



    }

    public void updateComment(Integer id,Comment comment){
        User u=userRepository.findUserById(comment.getUserId());
        Comment c=commentRepository.findCommentById(comment.getId());
        Content cnt=contentRepository.findContentById(comment.getContentId());
        if (c==null || u==null  || cnt==null){
            throw new ApiException("not found comment id or user id ");
        }

if (c.getId()==id){
        c.setComment(comment.getComment());
        c.setReview(comment.getReview());
//        c.setContentId(comment.getContentId());
//        c.setCommentDate(comment.getCommentDate());
//        c.setId(comment.getId());
//        c.setPurchesId(comment.getPurchesId());
//        c.setUserId(comment.getUserId());
        commentRepository.save(c);
    }else {
    throw new ApiException("Not match id");
}
    }

    public void deleteComment(Integer id){
        Comment c=commentRepository.findCommentById(id);
        if (c==null){
            throw new ApiException("not found id");
        }
        commentRepository.delete(c);
    }


//method to retrieve all comments on content
    public List<Comment> commentsOnContent(Integer contentId){
List<Comment>  commentList=commentRepository.findCommentsByContentId(contentId);
         if(commentList.isEmpty()){
           throw new ApiException("Not found comments on this content");
        }
        return commentList;
    }
    //method to retrieve all comments by user id
    public List<Comment> commentsByUser(Integer userId){
        List<Comment>  commentList=commentRepository.findCommentsByUserId(userId);
        if(commentList.isEmpty()){
            throw new ApiException("Not found comments ");
        }
        return commentList;
    }
    //method to retrieve all comments by date
    public List<Comment> commentByDate(LocalDate date){
        List<Comment> list=commentRepository.findCommentsByCommentDate(date);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }
    //method to calculate avr reviews //on content
    public Double reviewAvrge(Integer contentId){
        List<Comment> list=commentRepository.findCommentsByContentId(contentId);
        double totalAvg=0;
        if (list.isEmpty()){throw new ApiException("not found result");}
        for (Comment c:list){
            totalAvg=c.getReview()+totalAvg;
        }
     return    totalAvg/list.size();
    }


}
