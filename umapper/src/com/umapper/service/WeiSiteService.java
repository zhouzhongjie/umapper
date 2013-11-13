package com.umapper.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.IWeiSiteDao;
import com.umapper.po.WeiSite;
import com.umapper.util.MyBatisUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class WeiSiteService {

	static SqlSessionFactory sqlSessionFactory = null; 
	static Configuration cfg = null; 
	static Map<String,String> tempMap = new HashMap<String, String>();
	
	
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 

       // Initialize the FreeMarker configuration;
       cfg = new Configuration();
       cfg.setClassForTemplateLoading(WeiSite.class, "/templates");
       cfg.setTemplateExceptionHandler(
				TemplateExceptionHandler.RETHROW_HANDLER);
       
       //临时用map存储template
       tempMap.put("0", "singlePic.ftl");
       tempMap.put("1", "twoPic.ftl");
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
	
	public boolean generateHtml(WeiSite site)
	{
		String templateFileName = tempMap.get(site.getType());
		String description = site.getDescription();
		Map<String,String> propMap = new HashMap<String, String>();
		return geneHtmlFile(templateFileName, propMap, "", "1.html");
	}
	
	   /**
     * 生成静态文件.
     *
     * @param templateFileName 模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl"
     * @param propMap 用于处理模板的属性Object映射
     * @param htmlFilePath 要生成的静态文件的路径,相对设置中的根路径,例如 "/tpxw/1/2005/4/"
     * @param htmlFileName 要生成的文件名,例如 "1.htm"
     */
    public boolean geneHtmlFile(String templateFileName,Map propMap, String htmlFilePath,String htmlFileName )
    {
           //@todo 从配置中取得要静态文件存放的根路径:需要改为自己的属性类调用
        String sRootDir = "static" ;
       
        try
        {
            Template t = cfg.getTemplate(templateFileName);

            File afile = new File(sRootDir +"/" +htmlFilePath + "/" + htmlFileName);

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile)));

            t.process(propMap, out);
        }
        catch (TemplateException e)
        {
            return false;
        }
        catch (IOException e)
        {
            return false;
        }

        return true;
    }
}
