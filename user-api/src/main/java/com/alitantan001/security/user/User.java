package com.alitantan001.security.user;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotBlank(message = "用户名不能为空")
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    public UserInfo buildInfo() {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(this, info);
        return info;
    }
}
