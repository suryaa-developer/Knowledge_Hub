package com.PKH.Hub.Service;

import com.PKH.Hub.Config.BCryptUtil;
import com.PKH.Hub.Entity.User;
import com.PKH.Hub.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptUtil bCryptUtil;

        public String signUp(User user) {
            if(userRepo.existsByUsername(user.getUsername()) )  {
                return "UserName already exist";
            }
            System.err.println("email : "+userRepo.existsByEmail(user.getEmail()));
            if(userRepo.existsByEmail(user.getEmail())){
                return "User with this Email Already Exist. Please Login";
            }

            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            String password = bCryptUtil.PasswordEncoder().encode(user.getPassword());
            newUser.setPassword(password);
            userRepo.save(newUser);
            return "User Created Successfully";
        }


        public String LoginUser(String mail,String password) {

            Optional<User> existUser = userRepo.findByEmail(mail);
            if (existUser.isEmpty()) {
                return "User doesn't exist. Create a new Account";
            }

            User user = existUser.get();
            if(!bCryptUtil.checkPassword(password, user.getPassword())) {
                return "Invalid Password";
            }

            return "Login Successfully";
        }
}
