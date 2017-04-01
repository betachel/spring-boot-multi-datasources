package com.beta.study.mapper.secondjdbc;

import java.util.List;
import com.beta.study.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * @date 2017/03/27
 */
@Mapper
public interface SecondUserMapper {

    @Select("select * from user")
    @Results(value = {
        @Result(id = true,column = "id",property = "id",jdbcType = JdbcType.INTEGER,javaType = Integer.class),
        @Result(column = "user_name",property = "userName",jdbcType = JdbcType.VARCHAR,javaType = String.class),
        @Result(column = "nick",property = "nick",jdbcType = JdbcType.VARCHAR,javaType = String.class),
        @Result(column = "text",property = "text",jdbcType = JdbcType.VARCHAR,javaType = String.class)
    })
    List<User> getAllUsers();
}
