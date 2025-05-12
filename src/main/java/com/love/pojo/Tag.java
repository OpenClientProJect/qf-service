package com.love.pojo;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class Tag {
    private Integer id;
    private String firstTag;
    private String lastTag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstTag() {
        return firstTag;
    }

    public void setFirstTag(String firstTag) {
        this.firstTag = firstTag;
    }

    public String getLastTag() {
        return lastTag;
    }

    public void setLastTag(String lastTag) {
        this.lastTag = lastTag;
    }

}
