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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.umapper.dao.IWeiSiteDao;
import com.umapper.po.WeiSite;
import com.umapper.util.Common;
import com.umapper.util.MyBatisUtil;
import com.umapper.util.StaticDataPool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class WeiSiteService {

	static SqlSessionFactory sqlSessionFactory = null;
	static Configuration cfg = null;
	static Map<String, String> tempMap = new HashMap<String, String>();

	static {

		try {
			sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

			// Initialize the FreeMarker configuration;
			cfg = new Configuration();
			File configFile = new File(Common.getWebinfoPath(), "templates");
			cfg.setDirectoryForTemplateLoading(configFile);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			// 临时用map存储template
			tempMap.put("单图文", "singlePic.ftl");
			tempMap.put("十宫格", "twoPic.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<WeiSite> getWeiSiteList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			IWeiSiteDao mapper = sqlSession.getMapper(IWeiSiteDao.class);
			List<WeiSite> site = mapper.getWeiSiteList();
			return site;
		} finally {
			sqlSession.close();

		}
	}
	
	public void addWeiSite(WeiSite site) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			IWeiSiteDao mapper = sqlSession.getMapper(IWeiSiteDao.class);
			mapper.addWeiSite(site);
			generateHtml(site);
			sqlSession.commit();
			System.out.println("add weisite successfully!");
		} finally {
			sqlSession.close();
			
		}
	}

	public String generateHtml(WeiSite site) {
		String templateFileName = tempMap.get(site.getType());
		//todo
		String type = site.getType();
		Map<String, String> propMap = new HashMap<String, String>();
		String description = site.getDescription();
		JsonParser jsonParser = new JsonParser();
		JsonObject obj = jsonParser.parse(description).getAsJsonObject();
		if("单图文".equals(type))
		{
			propMap.put("picurl", obj.get("picurl").getAsString());
			propMap.put("content", obj.get("content").getAsString());
		}
		
		String siteUrl = StaticDataPool.SITE_ROOT + File.separator
				+ site.getId() + File.separator + StaticDataPool.SITE_HOMEPAGE;
		geneHtmlFile(templateFileName, propMap, siteUrl);
		
		return siteUrl;
	}

	/**
	 * 生成静态文件.
	 * 
	 * @param templateFileName
	 *            模板文件名
	 * @param propMap
	 *            用于处理模板的属性Object映射
	 * @param siteUrl
	 *            生成的静态文件的路径，如:site/32/5443.html
	 */
	public void geneHtmlFile(String templateFileName, Map<String,String> propMap,
			String siteUrl) {
		Writer out = null;
		try {
			String localPath = Common.getWebrootPath() + File.separator
					+ siteUrl;
			File localFile = new File(localPath);
			if (!localFile.getParentFile().exists()) {
				localFile.getParentFile().mkdirs();
			}

			Template t = cfg.getTemplate(templateFileName);
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(localFile)));

			t.process(propMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
