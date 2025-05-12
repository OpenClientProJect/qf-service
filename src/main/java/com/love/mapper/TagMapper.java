package com.love.mapper;

import com.love.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TagMapper {

    /**
     * 发布公告
     */
    @Insert("insert into tag(first_tag,last_tag)" +
            "values(#{firstTag},#{lastTag}")
    void publish(Tag tag);

    /**
     * 获取公告
     */
    @Select("select first_tag from tag")
    List<Tag> getFirstTag();

    @Select("select last_tag from tag")
    List<Tag> getLastTag();

    /**
     * 删除公告
     */
    @Delete("delete from tag where id=#{id}")
    void deleteTag(Integer id);

    /**
     * 編輯公告
     */
    @Update("UPDATE tag SET first_tag = #{firstTag}, last_tag = #{lastTag} WHERE id=#{id}")
    void updateTag(Tag tag);
}
