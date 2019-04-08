package kr.hs.dgsw.web_2_0326.Service;

import kr.hs.dgsw.web_2_0326.Domain.User;
import kr.hs.dgsw.web_2_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        Optional<User> found = userRepository.findByEmail(user.getEmail());
        return !found.isPresent() ? userRepository.save(user) : null;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User viewUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> found = userRepository.findById(id);
        if (!found.isPresent()) {
            return null;
        }
        User target = found.get();
        target.setEmail(user.getEmail());
        target.setUsername(user.getUsername());
        target.setPath(user.getPath());
        return userRepository.save(target);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> found = userRepository.findById(id);
        if (!found.isPresent()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
