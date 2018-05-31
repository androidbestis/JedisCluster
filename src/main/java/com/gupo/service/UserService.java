package com.gupo.service;


import com.alibaba.fastjson.JSON;
import com.gupo.config.DS;
import com.gupo.consts.DbAndCacheContants;
import com.gupo.dao.UserDao;
import com.gupo.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * JedisCluster 测试
     */
    @DS("datasource1")
    public User getUser(Integer id){
        //第二次从缓存读取数据
        String userStr = jedisCluster.get(DbAndCacheContants.USER_CACHE_PREFIX + id);
        if(StringUtils.isNotBlank(userStr)){
            return JSON.parseObject(userStr,User.class);
        }
        //第一次缓存没有数据,从数据库查询
        User user = userDao.selectByPrimaryKey(id);
        if(user != null){
            jedisCluster.set(DbAndCacheContants.USER_CACHE_PREFIX + id , JSON.toJSONString(user));
        }
        return user;
    }
}
