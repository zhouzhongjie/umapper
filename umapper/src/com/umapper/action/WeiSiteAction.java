package com.umapper.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.umapper.po.WeiSite;
import com.umapper.service.WeiSiteService;


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
		
		//JSONObject jo = JSONObject.fromObject(site);
		
		JSONArray ja = JSONArray.fromObject(site);
		
		System.out.println(ja.toString());
		
		String result = "{\"sEcho\": 1,\"iTotalRecords\": 20,\"iTotalDisplayRecords\": 20,\"aaData\":" +ja.toString()+"}";
		
		String json = "{'weisitelist':[['201311092321000','home','ten','http://localhost:8080/site/201311092321000','modify']]}";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(result);
		
	}
	
	

}
