package kr.hs.dgsw.web_2_0326.Controller;

import kr.hs.dgsw.web_2_0326.Domain.User;
import kr.hs.dgsw.web_2_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @GetMapping("/user")
    public List<User> listUsers() {
        return this.userService.listUsers();
    }

    @GetMapping("/user/{id}")
    public User viewUser(@PathVariable Long id) {
        return this.userService.viewUser(id);
    }

    @PutMapping("/user/{id}")
    public  User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return this.userService.deleteUser(id);
    }
}
