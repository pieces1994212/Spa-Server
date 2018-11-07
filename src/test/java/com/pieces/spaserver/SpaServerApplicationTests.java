package com.pieces.spaserver;

import com.pieces.spaserver.mapper.user.UserMapper;
import com.pieces.spaserver.model.user.Role;
import com.pieces.spaserver.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpaServerApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();

        user.setId(Long.parseLong("1"));
        List<Role> list = userMapper.getUserRoles(user);
    }

}
