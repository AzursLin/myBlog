package com.lxz.myBlog.Mapper;

import com.lxz.myBlog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2017/8/1.
 */
@Mapper
public interface  userMapper {

    @Select("select * from t_user where user_id=1")
    User getUser();

    @Select("select Count(*) from t_user where user_name=#{0} and password=#{1}")
    int getMatchCount(String userName,String password);

    @Select("select * from t_user where user_name=#{userName}")
    User findUserByUserName(String userName);

    @Update("update t_user set credits=#{credits},last_visit=#{lastVisit},last_ip=#{lastIp} where user_id=#{userId}")
    void updateLoginInfo(User user);
}
