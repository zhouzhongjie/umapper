package com.umapper.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.IMaterialDao;
import com.umapper.po.Material;
import com.umapper.util.MyBatisUtil;

public class MaterialService {

	static SqlSessionFactory sqlSessionFactory = null; 
	static SqlSession sqlSession = null;
	static IMaterialDao mapper = null;
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
       sqlSession = sqlSessionFactory.openSession();
	   mapper = sqlSession.getMapper(IMaterialDao.class); 
    }
    
    public static void start()
    {
       sqlSession.close();
       sqlSession = sqlSessionFactory.openSession();
 	   mapper = sqlSession.getMapper(IMaterialDao.class);     	
    }
    
    public static void stop()
    {
    	sqlSession.close();
    }
    
    public static Material getMaterial(String url) { 
	   Material material = mapper.getMaterial(url); 
       return material;
    } 
    
    public static void addMaterial(Material material)
    {
    	Material m = mapper.getMaterial(material.getUrl());
    	if (m != null)
    	{
    		return;
    	}
 	   mapper.addMaterial(material); 
 	   sqlSession.commit();
    }
    
    public static void deleteMaterial(String url)
    {
 	   mapper.deleteMaterial(url); 
 	   sqlSession.commit();
    }
}
