package com.umapper.dao;

import java.util.List;

import com.umapper.po.WeiSite;

  
public interface IWeiSiteDao { 
    public List<WeiSite> getWeiSiteList(); 
    public void addWeiSite(WeiSite site); 
} 