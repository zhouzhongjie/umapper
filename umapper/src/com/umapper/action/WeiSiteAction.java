package com.umapper.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		
		String action = request.getParameter("action");
		
		if("loadsitepage".equals(action))
		{
			loadWeiSitePage(request,response);
		}
		else if("loadsitelist".equals(action))
		{
			loadWeiSiteList(request,response);
		}
		else if("addweisite".equals(action))
		{
			addWeiSite(request,response);
		}
		
	}
	
	
	private void addWeiSite(HttpServletRequest request,
			HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		
		System.out.println("name:"+name);
		System.out.println("type:"+type);
		System.out.println("content:"+content);
		
		//TODO:处理上传的图片，获取图片的url
//		String picurl = "http://localhost:8080/charisma/img/logo20.png";
		String picurl = request.getParameter("picurl");;
		System.out.println("picurl:"+picurl);
		
		//TODO: 生成静态页面，返回静态页面的url
		String url = "http://localhost:8080/weisite.jsp";
		
		JSONObject desc = new JSONObject();
		desc.put("picurl", picurl);
		desc.put("content", content);
		String description = desc.toString();
		//TODO: 存入数据库
		
		WeiSite site = new WeiSite();
		site.setId(String.valueOf(System.currentTimeMillis()));
		site.setName(name);
		site.setType(type);
		site.setDescription(description);
		
		WeiSiteService service = new WeiSiteService();
		url = service.generateHtml(site);
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"; 
		site.setUrl(basePath+url);
		
		service.addWeiSite(site);
		
		String result = "{\"msg\": \"添加成功\",\"errorcode\": 0}";
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadWeiSitePage(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			this.getServletConfig().getServletContext() 
			.getRequestDispatcher("/weisitelist.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void loadWeiSiteList(HttpServletRequest request, HttpServletResponse response)
	{
		List<WeiSite> site = new WeiSiteService().getWeiSiteList();
		
		JSONArray ja = JSONArray.fromObject(site);
		
		String result = "{\"sEcho\": 1,\"iTotalRecords\": 20,\"iTotalDisplayRecords\": 20,\"aaData\":" +ja.toString()+"}";
		
		//String json = "{'weisitelist':[['201311092321000','home','ten','http://localhost:8080/site/201311092321000','modify']]}";
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
