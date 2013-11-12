package com.umapper.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jdom.JDOMException;

import com.umapper.dao.UserDao;
import com.umapper.po.User;
import com.umapper.po.WeiSite;
import com.umapper.service.UserService;
import com.umapper.service.WeiSiteService;
import com.umapper.util.MyBatisUtil;


/**
 * 微信统一入口
 * @author slz 
 * 2013-7-26 上午10:17:08
 */
public class WeiSiteAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109576102823865330L;

	public WeiSiteAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	/**
	 * 微信公众平台  成为开发者验证入口
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 微信公众平台  所有接口入口
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<WeiSite> site = new WeiSiteService().geWeiSiteList();
		
		System.out.println("ddddddddddd"+site);
		
		String json = "{'weisitelist':[['201311092321000','home','ten','http://localhost:8080/site/201311092321000','modify']]}";
		
		response.getWriter().write(json);
		
	}
	
	

}