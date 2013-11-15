package com.umapper.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.umapper.baidu.bcs.BucketManager;
import com.umapper.baidu.bcs.MaterialObject;

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
		
		List<MaterialObject> materials = BucketManager.getInstance().putFile(bcs, bucket, req);
		StringBuffer sb = new StringBuffer();
		for (MaterialObject m : materials)
		{
			sb.append("file:").append(m.getName());
			sb.append(", url=").append(m.getUrl()).append("\n");
		}
		PrintWriter out = resp.getWriter();
		out.println("<html><body>" + "<br>" + sb.toString() + "</body></html>");
		out.flush();
		out.close();
	}

}
