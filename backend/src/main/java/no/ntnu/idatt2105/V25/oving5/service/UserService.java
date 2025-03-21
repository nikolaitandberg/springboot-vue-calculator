package no.ntnu.idatt2105.V25.oving5.service;


import no.ntnu.idatt2105.V25.oving5.model.User;
import no.ntnu.idatt2105.V25.oving5.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) throws Exception{

        if (repo.findByUsername(user.getUsername()) != null) {
            throw new Exception("User with username " + user.getUsername() + " already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}