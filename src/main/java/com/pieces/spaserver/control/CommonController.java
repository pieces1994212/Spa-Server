package com.pieces.spaserver.control;

import com.pieces.spaserver.model.user.Role;
import com.pieces.spaserver.model.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:
 * @PackageName: com.pieces.spaserver.control
 * @date 15:55 2018/11/15
 * @编辑：
 * @描述：
 */
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @RequestMapping("/queryCurrentUserInfo")
    public User queryCurrentUserInfo(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInfo = new User();
        userInfo.setLoginName(user.getLoginName());
        userInfo.setNo(user.getNo());
        userInfo.setName(user.getName());
        userInfo.setRoles(new ArrayList<>());
        return userInfo;
    }

}
