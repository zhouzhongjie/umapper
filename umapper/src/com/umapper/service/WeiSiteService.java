package com.umapper.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.IWeiSiteDao;
import com.umapper.po.WeiSite;
import com.umapper.util.MyBatisUtil;

public class WeiSiteService {

	static SqlSessionFactory sqlSessionFactory = null; 
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 
    }
	
	public List<WeiSite> geWeiSiteList() { 
	       SqlSession sqlSession = sqlSessionFactory.openSession(); 
	       try { 
	    	   IWeiSiteDao mapper = sqlSession.getMapper(IWeiSiteDao.class); 
	    	   List<WeiSite> site = mapper.getWeiSiteList(); 
	           return site;
	       } finally { 
	           sqlSession.close();
	           
	       }
	    } 
}
