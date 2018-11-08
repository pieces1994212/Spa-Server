package com.pieces.spaserver.mapper.function;

import com.pieces.spaserver.model.user.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 功能点mapper
 * @PackageName: com.pieces.spaserver.mapper.funciton
 * @date 10:17 2018/11/8
 * @编辑：
 * @描述：
 */
@Mapper
public interface FunctionMapper {

    /**
     * 查询接口所需要权限角色名称
     * @param url
     * @return
     */
    List<String> queryRolesByUrl(String url);
}
