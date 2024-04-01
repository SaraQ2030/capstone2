package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.*;
import org.example.capstone2.Repository.ContentRepository;
import org.example.capstone2.Repository.PurchesRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class PurchesService {
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final PurchesRepository purchesRepository;
    private final ContentService contentService;
    public List<Purches> getAllPurches(){
        return purchesRepository.findAll();
    }

    public void addPurches(Purches purches){
        User u=userRepository.findUserById(purches.getUserId());
        Content c=contentRepository.findContentById(purches.getContentId());
        if (u==null){
            throw new ApiException("not found user Id");
        }
        else if(c==null){
            throw new ApiException("not found content Id");
        }
        purchesRepository.save(purches);
        checkProgress(purches);

        //update user count
        u.setUserCount(u.getUserCount()+1);
        userRepository.save(u);
        //update content count
        c.setCount(c.getCount()+1);
        contentRepository.save(c);


    }

    public void updatePurches(Integer id, Integer adminId,Purches purches){
        Purches p=purchesRepository.findPurchesById(id);
        User u=userRepository.findUserById(purches.getUserId());
        Content c=contentRepository.findContentById(purches.getContentId());
        User admin=userRepository.findUserById(adminId);
        if (u==null){
            throw new ApiException("not found user Id");
        }
        else if(c==null){
            throw new ApiException("not found content Id");
        }
        else if (p==null){
            throw new ApiException("not found purches Id");
        }
        if (contentService.checkAdmin(admin)){
            p.setId(purches.getId());
            p.setPurchesDate(purches.getPurchesDate());
            p.setUserId(purches.getUserId());
            p.setContentId(purches.getContentId());
            p.setStarDate(purches.getStarDate());
            p.setEndDate(purches.getEndDate());
            p.setInProgress(purches.getInProgress());
            p.setStatus(purches.getStatus());
            purchesRepository.save(p);
        }
    }

    public void deletePurches(Integer id,Integer adminId){
        Purches p=purchesRepository.findPurchesById(id);
        User admin=userRepository.findUserById(adminId);
        if (p==null){
            throw new ApiException("Not found purches id");
        }
        if (contentService.checkAdmin(admin)) {
            purchesRepository.delete(p);
        }

    }

// 2--------------method to set purches inProgress if start date begin  used in add method
    public void checkProgress(Purches purches){
        Purches p=purchesRepository.findPurchesById(purches.getId());
        LocalDate todayDate=LocalDate.now();
        if (p.getStarDate().isEqual(p.getPurchesDate()) ){
            p.setInProgress(true);
            p.setStatus("in-progress");
        }else if( p.getStarDate().isEqual(todayDate)){
            p.setInProgress(true);
            p.setStatus("in-progress");
        }
        else if( p.getStarDate().isBefore(todayDate)){
            p.setInProgress(true);
            p.setStatus("in-progress");
        }
        else{
            p.setInProgress(false);
            p.setStatus("not-started");
        }
        purchesRepository.save(p);
    }

    //method to retrieve all purches by user id
 public List<Purches> purchesByUser(Integer userId){
        List<Purches>  purchesList=purchesRepository.findPurchesByUserId(userId);
        if(purchesList.isEmpty()){
            throw new ApiException("Not found purches ");
        }
        return purchesList;}

    //method to check progress
   public List<Purches> purchesProgress(Boolean b){
        List<Purches> list=purchesRepository.findPurchesByInProgress(b);
        if (list.isEmpty()){
            throw new ApiException("Not found result");
        }
        return list;

   }

   public List<Purches> purchesStatus(String status){
        List<Purches> list=purchesRepository.findPurchesByStatus(status);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;

   }

   public List<Purches> purchesEndDate(LocalDate date){
        List<Purches> list=purchesRepository.findPurchesByEndDate(date);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
   }

    public List<Purches> purchesContentId(Integer id){
        List<Purches> list=purchesRepository.findPurchesByContentId(id);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }

}
