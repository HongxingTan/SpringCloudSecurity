package com.alitantan001.security.user;

import com.mysql.cj.PreparedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserInfo update(@RequestBody UserInfo user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserInfo get(@PathVariable Long id, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        if (user == null || !user.getId().equals(id)) {
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }

        return userService.get(id);
    }

    @GetMapping
    public List<UserInfo> query(String name) {
        return userService.query(name);
    }

}
