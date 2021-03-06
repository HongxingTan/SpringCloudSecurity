package com.alitantan001.security.user;

import java.util.List;

public interface UserService {


    UserInfo create(UserInfo user);


    UserInfo update(UserInfo user);


    void delete(Long id);


    UserInfo get(Long id);


    List<UserInfo> query(String name);

    UserInfo login(UserInfo user);
}
