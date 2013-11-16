package com.umapper.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.umapper.baidu.bcs.BucketManager;
import com.umapper.po.Material;
import com.umapper.service.MaterialService;

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
		String action = req.getParameter("action");
		if (action.equals("delete"))
		{
			delete(req);
		}
		else if (action.equals("upload"))
		{
			upload(req, resp);
		}
	}
	
	private void delete(HttpServletRequest req)
	{
		String url = req.getParameter("url");
		if (url.isEmpty())
		{
			return;
		}
		Material material = MaterialService.getMaterial(url);
		if (material == null)
		{
			System.out.println("url not exist");
			return;
		}

		BaiduBCS bcs = BucketManager.getInstance().getBaiduBCS();
		BucketManager.getInstance().deleteFile(bcs, material.getBucket(), material.getFileName());
		MaterialService.deleteMaterial(url);
	}
	
	private void upload(HttpServletRequest req, HttpServletResponse resp)
	{
		BaiduBCS bcs = BucketManager.getInstance().getBaiduBCS();
		
		List<Material> materials = BucketManager.getInstance().putFile(bcs, bucket, req);
		StringBuffer sb = new StringBuffer();
		for (Material m : materials)
		{
			sb.append("file:").append(m.getFileName());
			sb.append(", url=").append(m.getUrl()).append("\n");
			MaterialService.addMaterial(m);
			System.out.println(m.getUrl());
		}
		
//		try {
//			PrintWriter out = resp.getWriter();
//			out.println("<html><body>" + "<br>" + sb.toString() + "</body></html>");
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
