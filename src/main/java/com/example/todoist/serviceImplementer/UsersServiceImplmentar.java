package com.example.todoist.serviceImplementer;


import com.example.todoist.model.TodoistUser;
import com.example.todoist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class UsersServiceImplmentar implements  UserDetailsService {

    @Autowired
    UserRepository userRepository;






    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TodoistUser users = (userRepository.findByEmail(s));





        if (users == null ) {

            System.out.println("both not found");

            throw new UsernameNotFoundException("Not Found!!");

        } else {




            System.out.println("is user");

            System.out.println(users.getId());


            System.out.println("session id set");

            System.out.println("id is" + users.getId());

            return new UserPrincipal(users);


        }


    }
}
