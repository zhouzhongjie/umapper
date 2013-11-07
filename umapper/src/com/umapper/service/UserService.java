package com.umapper.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.UserDao;
import com.umapper.po.User;
import com.umapper.util.MyBatisUtil;

public class UserService {

	static SqlSessionFactory sqlSessionFactory = null; 
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 
    }
	
	public String getUserAge(String name) { 
	       SqlSession sqlSession = sqlSessionFactory.openSession(); 
	       try { 
	           UserDao userMapper = sqlSession.getMapper(UserDao.class); 
	           User user = userMapper.getUser(name); 
	           return name +"，今年 "+ String.valueOf(user.getAge()) + "岁";
	       } finally { 
	           sqlSession.close();
	           
	       }
	    } 
}
