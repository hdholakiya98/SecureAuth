package registerLogin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import registerLogin.entities.User;
import registerLogin.payload.UserRequest;
import registerLogin.payload.UserResponse;
import registerLogin.repositories.UserRepository;
import registerLogin.security.JwtTokenHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse findByUsernameAndPassword(UserRequest userRequest) {

        //User user = userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
        User user = userRepository.findByUsername(userRequest.getUsername());

        UserResponse userResponse = new UserResponse();

        if (user!=null) {
            String token = jwtTokenHelper.generateToken(user.getUsername());
            userResponse.setToken(token);
            userResponse.setMessage("User found successfully.");
        } else {
            userResponse.setMessage("Invalid username or password.");
        }
        return userResponse;
    }
}