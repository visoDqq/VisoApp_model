package com.wz.VisoApp.model.entity;


import com.wz.VisoApp.common.util.BaseUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chenwuxiong on 2017/11/30.
 */
@Entity
public class Test {

    @Id
    private String id = BaseUtil.UUID();

    @Column(nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
