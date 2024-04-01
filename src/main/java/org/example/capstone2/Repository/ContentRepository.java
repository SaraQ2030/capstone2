package org.example.capstone2.Repository;

import org.example.capstone2.Model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
    Content findContentById(Integer id);
List<Content> findContentsByCategoryId(Integer catId);
   // List<Content> findContentsByReviews(Double review);
    List<Content> findContentsByReviewsGreaterThanEqual(Double review);
    List<Content> findContentsByCountGreaterThanEqual(Integer num);

    List<Content> findContentsByCountLessThanEqual(Integer num);


}
