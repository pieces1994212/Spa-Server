package com.pieces.spaserver.mapper.user;

import com.pieces.spaserver.model.user.Role;
import com.pieces.spaserver.model.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 用户mapper
 * @PackageName: com.pieces.spaserver.mapper.user
 * @date 15:33 2018/11/6
 * @编辑：
 * @描述：
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 查找用户角色
     * @param user
     * @return
     */
    List<Role> getUserRoles(User user);

    /**
     * 锁定用户
     * @param user
     * @return
     */
    int lockUser(User user);

    /**
     * 解锁用户
     * @param user
     * @return
     */
    int releaseUser(User user);

    /**
     * 改变登陆次数
     * @param user
     * @return
     */
    int changeLoginCount(User user);
}
