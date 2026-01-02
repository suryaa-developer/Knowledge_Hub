package com.PKH.Hub.Cotroller;

import com.PKH.Hub.Entity.User;
import com.PKH.Hub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String>  RegisterUser(@RequestBody User user){
        String Result  = userService.signUp(user);
        if(Result.equalsIgnoreCase("User Created Successfully")){
            return  new ResponseEntity<>(Result, HttpStatus.OK);
        } else if (Result.equals("User with this Email Already Exist. Login")) {
            return new ResponseEntity<>("User with this Email Already Exist",HttpStatus.BAD_REQUEST);
        } else if (Result.equals("UserName already Exist")) {
            return  new ResponseEntity<>(Result,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("Server Error. Sorry for the Inconvenience",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> LoginUser(@RequestBody User user){
        String Result  = userService.LoginUser(user.getEmail(), user.getPassword());
        if(Result.equalsIgnoreCase("User doesn't exist. Create a new Account")){
            return new ResponseEntity<>(Result,HttpStatus.NOT_FOUND);
        } else if (Result.equalsIgnoreCase("Invalid Password")) {
            return new ResponseEntity<>(Result,HttpStatus.NOT_ACCEPTABLE);
        }else{
            return new ResponseEntity<>(Result,HttpStatus.OK);
        }
    }
}
