package hw.esf.serverusermanagement.service;

import hw.esf.serverusermanagement.model.User;
import hw.esf.serverusermanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username){
       return this.userRepository.findByUsername(username).orElse(null);

    }

    @Override
    public List<String> findUsers(List<Long> idList) {

        return this.userRepository.findUserIdList(idList);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }
}
