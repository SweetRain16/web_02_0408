package kr.hs.dgsw.web_2_0326.Service;

import kr.hs.dgsw.web_2_0326.Domain.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    User viewUser(Long id);
    User addUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
