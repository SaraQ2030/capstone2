package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Model.Profile;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.ContentRepository;
import org.example.capstone2.Repository.ProfileRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
    private final ContentService contentService;

    public List<Profile> getAllProfile(){return profileRepository.findAll();}

    public void addProfile(Profile profile,Integer adminId){
        Content c=contentRepository.findContentById(profile.getContentId());
        User u=userRepository.findUserById(adminId);
        if (c==null){
            throw new ApiException("Not found content id");
        }
if (contentService.checkAdmin(u)){
    profileRepository.save(profile);
}

    }

    public void updateProfile(Integer id,Profile profile,Integer adminId){
        Profile p=profileRepository.findProfileById(id);
        User u=userRepository.findUserById(adminId);
        if (p==null){
            throw new ApiException("Not found profile id");
        }
        if (contentService.checkAdmin(u)){
        p.setId(profile.getId());
        p.setContentId(profile.getContentId());
        p.setDescription(profile.getDescription());
        p.setImage(profile.getImage());
        profileRepository.save(p);}

    }

    public void deleteProfile(Integer id,Integer adminId){
        Profile p=profileRepository.findProfileById(id);
        User u=userRepository.findUserById(adminId);
        if (p==null){
            throw new ApiException("Not found id");
        }
        if (contentService.checkAdmin(u)){
        profileRepository.delete(p);}

    }

    //method to print profile for certain content

    public Profile searchProfileByContentId(Integer contentId){
        Profile p=profileRepository.findProfileByContentId(contentId);
        if (p==null){
            throw new ApiException("Not found profile id");
        }
        return p;

    }

}
