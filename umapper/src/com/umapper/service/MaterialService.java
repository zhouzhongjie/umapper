package com.umapper.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.IMaterialDao;
import com.umapper.po.Material;
import com.umapper.util.MyBatisUtil;

public class MaterialService {

	static SqlSessionFactory sqlSessionFactory = null; 
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 
    }
    
    public static Material getMaterial(String url) { 
       SqlSession sqlSession = sqlSessionFactory.openSession(); 
       try { 
    	   IMaterialDao mapper = sqlSession.getMapper(IMaterialDao.class); 
    	   Material material = mapper.getMaterial(url); 
           return material;
       } finally { 
           sqlSession.close();	           
       }
    } 
    
    public static void addMaterial(Material material)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(); 
        try { 
     	   IMaterialDao mapper = sqlSession.getMapper(IMaterialDao.class); 
     	   mapper.addMaterial(material); 
     	   sqlSession.commit();
        } finally { 
            sqlSession.close();	           
        }
    }
    
    public static void deleteMaterial(String url)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(); 
        try { 
     	   IMaterialDao mapper = sqlSession.getMapper(IMaterialDao.class); 
     	   mapper.deleteMaterial(url); 
     	   sqlSession.commit();
        } finally { 
            sqlSession.close();	           
        }    	
    }
}
