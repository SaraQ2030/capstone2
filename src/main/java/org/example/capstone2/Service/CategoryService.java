package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.Category;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Model.Profile;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.CategoryRepository;
import org.example.capstone2.Repository.ProfileRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ContentService contentService;


    public List<Category> getAllCategories(){return categoryRepository.findAll();}

    public void addCategory(Category category,Integer id){
        User admin=userRepository.findUserById(id);
      if(contentService.checkAdmin(admin)){
        categoryRepository.save(category);}
    }

    public void updateCategory(Integer id,Category category,Integer adminId){
        Category c=categoryRepository.findCategoryById(id);
        User admin=userRepository.findUserById(adminId);
              if (c==null){
            throw new ApiException("Not found id");
        }
        if(contentService.checkAdmin(admin)) {
            c.setId(category.getId());
            c.setCategoryName(category.getCategoryName());
            categoryRepository.save(c);
        }

    }

    public void deleteCategory(Integer id,Integer admin) {
        Category c = categoryRepository.findCategoryById(id);
        User u=userRepository.findUserById(admin);
        if (c == null) {
            throw new ApiException("Not found id");
        }
        if (contentService.checkAdmin(u)) {
            categoryRepository.delete(c);
        }
    }
}
