package com.umapper.baidu.bcs;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.baidu.inf.iis.bcs.model.X_BS_ACL;
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
	
	public BaiduBCSResponse<Empty> putBucket(String bucket)
	{
		BaiduBCS bcs = getBaiduBCS();
		return bcs.createBucket(new CreateBucketRequest(bucket, X_BS_ACL.PublicRead));
	}

	public List<String> listFolder(BaiduBCS baiduBCS) {
		ListBucketRequest listBucketRequest = new ListBucketRequest();
		BaiduBCSResponse<List<BucketSummary>> response = baiduBCS.listBucket(listBucketRequest);
		List<String> res = new ArrayList<String>();
		for (BucketSummary bucket : response.getResult()) {
			res.add(bucket.getBucket());
		}
		return res;
	}
	

	public String createFolder(BaiduBCS bcs, String name)
	{
		BaiduBCSResponse<Empty> response = bcs.createBucket(new CreateBucketRequest(name));
		return response.getRequestId();
	}
	
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
	
	public boolean isFileExist(BaiduBCS bcs, String bucket, String file)
	{
		List<String> files = listFiles(bcs, bucket);
		if (files.contains(files))
		{
			return true;
		}
		return false;
	}
	
	public String deleteFile(BaiduBCS bcs, String bucket, String file)
	{
		DeleteObjectRequest req = new DeleteObjectRequest(bucket, file);
		BaiduBCSResponse<Empty> rsp = bcs.deleteObject(req);
		return rsp.getRequestId();
	}
	
	public Map<String, String> putFile(BaiduBCS bcs, String bucket, HttpServletRequest request)
	{
        DiskFileItemFactory diskFactory = new DiskFileItemFactory();       
        ServletFileUpload upload = new ServletFileUpload(diskFactory);  
        List fileItems = null;
        Map<String, String> fileUrls = new HashMap<String, String>();
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
	        	ObjectMetadata metadata = new ObjectMetadata();
	    		metadata.setContentLength(item.getSize());
	    		metadata.setContentType(item.getContentType());
	    		metadata.setContentEncoding("ASCII");
	    		String object = "/" + item.getName();
	    		PutObjectRequest objReq = new PutObjectRequest(bucket, "/" + item.getName(), item.getInputStream(), metadata);
	    		BaiduBCSResponse<ObjectMetadata> res = bcs.putObject(objReq);
	    		item.getInputStream().close();
	    		GenerateUrlRequest urlReq = new GenerateUrlRequest(HttpMethodName.POST, bucket, object);
	    		String url = URLDecoder.decode(bcs.generateUrl(urlReq));
	    		fileUrls.put(item.getName(), url);
	        }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			return null;
		}  catch (IOException e1)
		{
			return null;
		}
		return fileUrls;
	}

}