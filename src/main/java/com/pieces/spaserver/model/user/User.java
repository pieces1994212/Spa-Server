package com.pieces.spaserver.model.user;

import com.pieces.spaserver.model.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Xujing
 * @ClassName: User
 * @Description: User实体类，实现Security提供的UserDetails接口
 * @PackageName: com.pieces.spaserver.model.user
 * @date 15:25 2018/11/6
 * @编辑：
 * @描述：
 */
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String no ;

    /**
     * 姓名
     */
    private String name ;

    /**
     * 账号名称
     */
    private String loginName ;

    /**
     * 登陆密码
     */
    private String loginPassWord ;

    /**
     * 登陆次数
     */
    private Integer loginCount;

    /**
     * 是否被锁定
     */
    private Integer lockFlag;

    /**
     * 锁定事件
     */
    private Date lockTime ;

    /**
     * 锁定原有
     */
    private String lockReason;

    /**
     * 是否停用
     */
    private Integer disableFlag;

    /**
     * 移动电话
     */
    private String mobileTel;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 角色权限
     */
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Integer lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }


    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }

    /**
     * 重写getAuthorities()方法，正确加载当前用户权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.loginPassWord;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(null!=lockFlag && lockFlag == 1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(null!=disableFlag && disableFlag == 1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return this.loginName;
    }

    @Override
    public int hashCode() {
        return loginName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
