package com.umapper.baidu.bcs;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.http.HttpMethodName;
import com.baidu.inf.iis.bcs.model.BucketSummary;
import com.baidu.inf.iis.bcs.model.Empty;
import com.baidu.inf.iis.bcs.model.ObjectListing;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.ObjectSummary;
import com.baidu.inf.iis.bcs.request.CreateBucketRequest;
import com.baidu.inf.iis.bcs.request.DeleteObjectRequest;
import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
import com.baidu.inf.iis.bcs.request.ListBucketRequest;
import com.baidu.inf.iis.bcs.request.ListObjectRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

public final class BucketManager{
	/**
	 * 
	 */
	static String host = "bcs.duapp.com";
	static String accessKey = "M6dEeVOhxCGaOZlQ6uY4IAHW";		//改成百度工程的API Key
	static String secretKey = "o9mZ8qFfIh8f5arozmMKtXIhRpaLGEIS";	//改成百度工程的Secret key

	private static BucketManager manager = new BucketManager();
	
	public static BucketManager getInstance()
	{
		return manager;
	}
	private BucketManager()
	{
		
	}
		
	public BaiduBCS getBaiduBCS()
	{
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
		baiduBCS.setDefaultEncoding("UTF-8");
		return baiduBCS;
	}
	
	public List<String> listBucket(BaiduBCS baiduBCS) {
		ListBucketRequest listBucketRequest = new ListBucketRequest();
		BaiduBCSResponse<List<BucketSummary>> response = baiduBCS.listBucket(listBucketRequest);
		List<String> res = new ArrayList<String>();
		for (BucketSummary bucket : response.getResult()) {
			res.add(bucket.getBucket());
		}
		return res;
	}
	
	/**
	 * 新建一个bucket
	 * @param bcs
	 * @param name bucket名字
	 * @return
	 */
	public String createBucket(BaiduBCS bcs, String name)
	{
		BaiduBCSResponse<Empty> response = bcs.createBucket(new CreateBucketRequest(name));
		return response.getRequestId();
	}
	
	
	/**
	 * 列出某bucket下的所有文件
	 * @param bcs
	 * @param bucket bucket名字
	 * @return
	 */
	public List<String> listFiles(BaiduBCS bcs, String bucket)
	{
		ListObjectRequest req = new ListObjectRequest(bucket);
		BaiduBCSResponse<ObjectListing> rsp = bcs.listObject(req);
		List<ObjectSummary> lists = rsp.getResult().getObjectSummaries();
		List<String> resList = new ArrayList<String>();
		for (ObjectSummary os: lists)
		{
			resList.add(os.getName());
		}
		
		return resList;
	}
	
	/**
	 * 判断某个文件在bucket下是否存在
	 * @param bcs
	 * @param bucket	bucket名字
	 * @param file	文件名
	 * @return
	 */
	public boolean isFileExist(BaiduBCS bcs, String bucket, String file)
	{
		List<String> files = listFiles(bcs, bucket);
		if (files.contains(file))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 删除某个bucket下的某个文件
	 * @param bcs
	 * @param bucket
	 * @param file	文件名
	 * @return
	 */
	public String deleteFile(BaiduBCS bcs, String bucket, String file)
	{
		DeleteObjectRequest req = new DeleteObjectRequest(bucket, file);
		BaiduBCSResponse<Empty> rsp = bcs.deleteObject(req);
		return rsp.getRequestId();
	}
	
	/**
	 * 用于处理浏览器上传文件的请求，将请求中的文件都上传到bcs上
	 * @param bcs
	 * @param bucket
	 * @param request 文件上传的post请求
	 * @return
	 */
	public List<MaterialObject> putFile(BaiduBCS bcs, String bucket, HttpServletRequest request)
	{
        DiskFileItemFactory diskFactory = new DiskFileItemFactory();       
        ServletFileUpload upload = new ServletFileUpload(diskFactory);  
        List fileItems = null;
        List<MaterialObject> materials = new ArrayList<MaterialObject>();
        
		try {
			fileItems = upload.parseRequest(request);
	        Iterator iter = fileItems.iterator();  
	        
	        while (iter.hasNext())
	        {
	        	FileItem item = (FileItem) iter.next();
	        	if (item.isFormField())
	        	{
	        		continue;
	        	}	        	
	        	if (item.getName().isEmpty())
	        	{
	        		continue;
	        	}
	    		String object = "/" + item.getName();
	    		if (isFileExist(bcs, bucket, object))
	    		{
	    			continue;
	    		}
	        	ObjectMetadata metadata = new ObjectMetadata();
	    		metadata.setContentLength(item.getSize());
	    		metadata.setContentType(item.getContentType());
	    		metadata.setContentEncoding("ASCII");
	    		PutObjectRequest objReq = new PutObjectRequest(bucket, "/" + item.getName(), item.getInputStream(), metadata);
	    		BaiduBCSResponse<ObjectMetadata> res = bcs.putObject(objReq);
	    		item.getInputStream().close();
	    		GenerateUrlRequest urlReq = new GenerateUrlRequest(HttpMethodName.POST, bucket, object);
	    		String urlOrigin = URLDecoder.decode(bcs.generateUrl(urlReq));
	    		String url = urlOrigin;
	    		if (urlOrigin.endsWith("="))
	    		{
	    			url = urlOrigin.substring(0, urlOrigin.length()-2);
	    			url += "%3D";
	    		}
	    		MaterialObject material = new MaterialObject(bucket, item.getName());
	    		material.setUrl(url);
	    		materials.add(material);
	        }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			return null;
		}  catch (IOException e1)
		{
			return null;
		}
		return materials;
	}
	
	/**
	 * 上传一个文件到bucket下
	 * @param bcs
	 * @param bucket
	 * @param file
	 * @return 返回文件的url
	 */
	public MaterialObject putFile(BaiduBCS bcs, String bucket, File file)
	{      
    	ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.length());
		metadata.setContentEncoding("ASCII");
		String object = "/" + file.getName();
		PutObjectRequest objReq = new PutObjectRequest(bucket, object, file);
		BaiduBCSResponse<ObjectMetadata> res = bcs.putObject(objReq);
		GenerateUrlRequest urlReq = new GenerateUrlRequest(HttpMethodName.POST, bucket, object);
		String urlOrigin = URLDecoder.decode(bcs.generateUrl(urlReq));
		String url = urlOrigin;
		if (urlOrigin.endsWith("="))
		{
			url = urlOrigin.substring(0, urlOrigin.length()-2);
			url += "%3D";
		}

		MaterialObject material = new MaterialObject(bucket, file.getName());
		material.setUrl(url);
		return material;
	}

}
