package com.umapper.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.umapper.baidu.bcs.BucketManager;

public class FileManager extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2539722866050249456L;
	private String bucket = "weixindandan11"; 	//这个名字在整个云存储空间中必须是唯一的
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BaiduBCS bcs = BucketManager.getInstance().getBaiduBCS();
		
		Map<String, String> files = BucketManager.getInstance().putFile(bcs, bucket, req);
		StringBuffer sb = new StringBuffer();
		Set<String> keys = files.keySet();
		for (String key : keys)
		{
			sb.append("file:").append(key);
			sb.append(", url=").append(files.get(key)).append("\n");
		}
		PrintWriter out = resp.getWriter();
		out.println("<html><body>" + "<br>" + sb.toString() + "</body></html>");
		out.flush();
		out.close();
	}

}
