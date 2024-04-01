package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiResponse;
import org.example.capstone2.Model.User;
import org.example.capstone2.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/vision/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUSers(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated "));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    @GetMapping("/search-username/{name}")
   public ResponseEntity findUSerByUsername(@PathVariable String name){
      User u=userService.findUserByUserName(name);
        return ResponseEntity.status(200).body(u);
    }

    @GetMapping("/search-email/{email}")
    public ResponseEntity findUSerByEmail(@PathVariable String email){
        User u=userService.findUserByEmail(email);
        return ResponseEntity.status(200).body(u);
    }


    @GetMapping("/search-phonenumber/{num}")
    public ResponseEntity findUSerByPhoneNumber(@PathVariable Integer num){
        User u=userService.findUserByPhoneNumbe(num);
        return ResponseEntity.status(200).body(u);
    }
}
