package org.example.capstone2.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.capstone2.API.ApiException;
import org.example.capstone2.Model.Content;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.ContentRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User u=userRepository.findUserById(id);
        if (u==null){
            throw new ApiException("not found id");
        }
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(u);
    }
    public void deleteUser(Integer id){
        User u=userRepository.findUserById(id);
        if (u==null){
            throw new ApiException("not found id");
        }
        userRepository.delete(u);
    }
public User findUserByEmail(String email){
        User u=userRepository.findUserByEmail(email);
        if (u==null){
            throw new ApiException("not found result");
        }
        return u;
}

    public User findUserByUserName(String username){
        User u=userRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("not found result");
        }
        return u;
    }
    public User findUserByPhoneNumbe(Integer num){
        User u=userRepository.findUserByPhoneNumber(num);
        if (u==null){
            throw new ApiException("not found result");
        }
        return u;
    }




}
