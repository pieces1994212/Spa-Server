package com.pieces.spaserver.model.function;

import com.pieces.spaserver.model.BaseEntity;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 功能点实体类，包括接口url，菜单，按钮等
 * @PackageName: com.pieces.spaserver.model.function
 * @date 9:10 2018/11/8
 * @编辑：
 * @描述：
 */
public class Function extends BaseEntity {

    /**
     * 编号
     */
    private String No;

    /**
     * 名称
     */
    private String name;

    /**
     * 排列序号
     */
    private Integer seq;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 视图组件
     */
    private String component;

    /**
     * 路径
     */
    private String path;

    /**
     * 父级Id
     */
    private Long parentId;

    /**
     * 备注
     */
    private String remark;

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
