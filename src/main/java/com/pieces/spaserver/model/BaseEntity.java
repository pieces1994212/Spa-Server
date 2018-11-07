package com.pieces.spaserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class BaseEntity {

    @Id
    private Long id;

    @Transient
    private Integer offset = 0;

    @Transient
    private Integer limit = 10;
    
    @Transient
    private Integer total = 0;
    
    
    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

    
}
