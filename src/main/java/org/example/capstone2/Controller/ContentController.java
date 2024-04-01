package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiResponse;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vision/content")
@AllArgsConstructor
public class ContentController {

    private final ContentService contentService;
    @GetMapping("/get")
    public ResponseEntity getContent(){
        return ResponseEntity.status(200).body(contentService.getAllContent());
    }
    @PostMapping("/add/{adminId}")
    public ResponseEntity addContent(@PathVariable Integer adminId,@RequestBody @Valid Content content, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       contentService.addContent(content,adminId);
        return ResponseEntity.status(200).body(new ApiResponse("Content added "));
    }
    @PutMapping("/update/{id}/{adminId}")
    public ResponseEntity updateContent(@PathVariable Integer adminId,@PathVariable Integer id, @RequestBody @Valid Content content, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        contentService.updateContent(id,content,adminId);
        return ResponseEntity.status(200).body(new ApiResponse("Content Updated "));
    }
    @DeleteMapping("/delete/{id}/{adminId}")
    public ResponseEntity deleteContent(@PathVariable Integer id,@PathVariable Integer adminId){
        contentService.deleteContent(id,adminId);
        return ResponseEntity.status(200).body(new ApiResponse("content deleted"));
    }
    @GetMapping("/get-content-category/{catId}")
    public ResponseEntity listContentByCategory(@PathVariable Integer catId){
        List<Content> contentList=contentService.searchCategorySort(catId);
        return ResponseEntity.status(200).body(contentList);
    }

    @GetMapping("/get-reviews/{review}")
    public ResponseEntity listContentByReviews(@PathVariable Double review){
        List<Content> list=contentService.findContentByReview(review);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/get-top/{num}")
    public ResponseEntity listContentByReviews(@PathVariable Integer num){
        List<Content> list=contentService.top10Content(num);
        return ResponseEntity.status(200).body(list);
    }

    @PutMapping("/discount/{num}")
    public ResponseEntity discount(@PathVariable Integer num){
        List<Content> list=contentService.discount(num);
        return ResponseEntity.status(200).body(list);
    }


}
