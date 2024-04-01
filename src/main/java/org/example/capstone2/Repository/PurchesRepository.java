package org.example.capstone2.Repository;

import org.example.capstone2.Model.Purches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchesRepository extends JpaRepository<Purches,Integer> {
   List<Purches> findPurchesByEndDate(LocalDate date);

    Purches findPurchesById(Integer id);

    List<Purches> findPurchesByUserId(Integer userId);
    List<Purches> findPurchesByInProgress(Boolean b);

    List<Purches> findPurchesByStatus(String status);

    List<Purches> findPurchesByContentId(Integer id);



}
