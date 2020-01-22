package com.example.todoist.serviceImplementer;



import com.example.todoist.model.TodoistUsers;
import com.example.todoist.repository.UserRepository;
import com.example.todoist.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImplmentar implements UsersService, UserDetailsService {

    @Autowired
    UserRepository userRepository;











    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Username" +
                " is"+s);
        TodoistUsers users = (userRepository.findByUserName(s));






        if (users == null )
        {
            System.out.println("No User Found");
            throw new UsernameNotFoundException("Not Found!!");
        } else {




            System.out.println("is user");



            return new UserPrincipal(users);


        }


    }
}
