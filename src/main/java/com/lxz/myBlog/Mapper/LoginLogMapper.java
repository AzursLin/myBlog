package com.lxz.myBlog.Mapper;

import com.lxz.myBlog.model.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/8/2.
 */
@Mapper
public interface LoginLogMapper {

    @Insert("insert into t_login_log (user_id,ip,login_datetime) VALUES (#{userId},#{ip},#{loginDatetime})")
    void insertLoginLog(LoginLog LoginLog);


}
