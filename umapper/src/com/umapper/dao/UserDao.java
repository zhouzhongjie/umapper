package com.umapper.dao;

import com.umapper.po.User;

  
public interface UserDao { 
    public void insertUser(User user); 
    public User getUser(String name); 
} 