package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.Category;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.CategoryRepository;
import org.example.capstone2.Repository.ContentRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;


    public List<Content> getAllContent(){
        return contentRepository.findAll();
    }
    public void addContent(Content content,Integer adminId){
        Category c=categoryRepository.findCategoryById(content.getCategoryId());
        User u=userRepository.findUserById(adminId);

        if (c==null){
            throw new ApiException("not found category ");
        }
        if(checkAdmin(u)){
            contentRepository.save(content);

        }
    }

    public void updateContent(Integer id,Content content,Integer adminId){
        Content c=contentRepository.findContentById(id);
        User u=userRepository.findUserById(adminId);
        if (c==null){
            throw new ApiException("Not found Content id");
        }
        if(checkAdmin(u)){
            c.setContentName(content.getContentName());
            c.setPrice(content.getPrice());
            c.setCount(content.getCount());
            c.setCategoryId(content.getCategoryId());
            c.setReviews(content.getReviews());
//            c.setProfileId(content.getProfileId());
//            c.setCommentId(content.getCommentId());
            contentRepository.save(c);
        }
    }

    public void deleteContent(Integer id,Integer adminId){
        Content c=contentRepository.findContentById(id);
        User user=userRepository.findUserById(adminId);
        if (c==null){
            throw new ApiException("not found id");
        }
        if(checkAdmin(user)) {
            contentRepository.delete(c);
        }
    }
    //method to check if user is admin or not
    public Boolean checkAdmin(User user){
        User u=userRepository.findUserById(user.getId());
        if (u.getRole().equalsIgnoreCase("admin")){
            return true;
        }
        throw new ApiException("you are not authorize to add");
    }
    //method to print list of all content with same category then sort by price
    public List<Content> searchCategorySort(Integer catId){
        List<Content>  list_content=contentRepository.findContentsByCategoryId(catId);
        if (list_content.isEmpty()){
            throw new ApiException("not found");
        }
        list_content.sort(Comparator.comparingDouble(Content::getPrice));
        return list_content;}


    public List<Content> findContentByReview(Double review){
        List<Content> list=contentRepository.findContentsByReviewsGreaterThanEqual(review);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        list.sort(Comparator.comparingDouble(Content::getReviews).reversed());
        return list;
    }

    public List<Content> top10Content(Integer num){
        List<Content> list=contentRepository.findContentsByCountGreaterThanEqual(num);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }


    public  List<Content>  discount(Integer num) {
        List<Content> list=contentRepository.findContentsByCountLessThanEqual(num);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }

        for (Content c :list){
          c.setPrice( c.getPrice()-(0.05*c.getPrice()));
        }
        return list;
    }



}
