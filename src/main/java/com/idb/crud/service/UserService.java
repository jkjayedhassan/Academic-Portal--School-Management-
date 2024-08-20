package com.idb.crud.service;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.User;

public interface UserService extends BaseService<User, Long> {

    Response<String> login(User user);


}
