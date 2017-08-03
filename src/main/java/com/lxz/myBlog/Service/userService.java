package com.lxz.myBlog.Service;

import com.lxz.myBlog.Mapper.LoginLogMapper;
import com.lxz.myBlog.Mapper.userMapper;
import com.lxz.myBlog.model.LoginLog;
import com.lxz.myBlog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.LongLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/8/1.
 */

@Service
public class userService {
    @Autowired
    private userMapper userMapper;
    @Autowired
    private LoginLogMapper LoginLogMapper;

    public boolean hasMatchUser(String userName,String pssword) {
        int matchCount = userMapper.getMatchCount(userName,pssword);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userMapper.findUserByUserName(userName);
    }

    @Transactional
    public boolean loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog LoginLog = new LoginLog();
        LoginLog.setUserId(user.getUserId());
        LoginLog.setIp(user.getLastIp());
        LoginLog.setLoginDatetime(user.getLastVisit());
        userMapper.updateLoginInfo(user);
        LoginLogMapper.insertLoginLog(LoginLog);
        return true;
    }

    @Transactional
    public boolean tryLogin(String userName,String pssword){
        if (hasMatchUser(userName,pssword)) {
            User user = findUserByUserName(userName);
            loginSuccess(user);
            return true;
        }
        else {
            return false;
        }

    }
}
