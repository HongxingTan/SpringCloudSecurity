package com.alitantan001.security.order;

import com.alitantan001.security.server.resource.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public String create(@AuthenticationPrincipal User user){

        return String.valueOf(user.getId());
    }
}
