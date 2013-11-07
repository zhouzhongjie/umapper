package com.umapper.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jdom.JDOMException;

import com.umapper.dao.UserDao;
import com.umapper.po.User;
import com.umapper.service.UserService;
import com.umapper.util.MyBatisUtil;


/**
 * 微信统一入口
 * @author slz 
 * 2013-7-26 上午10:17:08
 */
public class WeixinAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109576102823865330L;

	public WeixinAction() {
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
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String TOKEN = "sun";//Token
		String signature = request.getParameter("signature");//SHA1加密字符串
		String timestamp = request.getParameter("timestamp");//时间
		String nonce = request.getParameter("nonce");//随机数
		String echoStr = request.getParameter("echostr");//随机字符串
		
		PrintWriter out = response.getWriter();
		
		if(nonce.equals("123"))
		{
			out.print(new UserService().getUserAge(signature));
		}
		
		if(echoStr!=null){
		        String[] a = {TOKEN,timestamp,nonce};
		        Arrays.sort(a);//数组排序
		        String str = "";
		        for(int i=0;i<a.length;i++){
		                str += a[i];
		        }
		        out.print(echoStr);

//		        String echo = new SHA1().getDigestOfString(str.getBytes());//SHA1加密
//
//		        if(echo.equals(signature)){
//		                out.print(echoStr);
//		        }else{
//		                out.print("123");
//		        }
		}else{
		        try {
		                InputStream is = request.getInputStream();
		                PushManage push = new PushManage();
		                String getXml = push.PushManageXml(is);
		                System.out.println("getXml:"+getXml);
		                out.print(getXml);
		        } catch (JDOMException e) {
		                out.print("我不懂，你再解释一下吧!");
		        }
		}
		out.flush();
		out.close();
	}
	
	

}
