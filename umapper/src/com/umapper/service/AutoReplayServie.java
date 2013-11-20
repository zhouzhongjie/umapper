package com.umapper.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.umapper.dao.IAutoReplyDao;
import com.umapper.message.autoreplay.AutoReplyKey;
import com.umapper.message.autoreplay.AutoReplyMsg;
import com.umapper.po.ReplyRule;
import com.umapper.util.MyBatisUtil;

public class AutoReplayServie {
	static SqlSessionFactory sqlSessionFactory = null; 
	static SqlSession sqlSession = null;
	static IAutoReplyDao mapper = null;
    static { 
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
       sqlSession = sqlSessionFactory.openSession();
	   mapper = sqlSession.getMapper(IAutoReplyDao.class); 
    }
    
    public static void start()
    {
       sqlSession.close();
       sqlSession = sqlSessionFactory.openSession();
 	   mapper = sqlSession.getMapper(IAutoReplyDao.class);     	
    }
    
    public static void stop()
    {
    	sqlSession.close();
    }
    
    /**
     * 添加一个自定义回复规则
     * @param user 用户
     * @param key	关键词与匹配规则
     * @param replay	回复内容
     */
    public static void addReplyRule(String user, AutoReplyKey replyKey, AutoReplyMsg replyMsg)
    {
    	String[] keys = replyKey.getKeys();
    	
    	for (String key : keys)
    	{
    		ReplyRule rule = new ReplyRule();
    		rule.setUser(user);
    		rule.setKey(key);
    		rule.setMatchRule(replyKey.getMatchRule());
    		rule.setType(replyMsg.getType());
    		rule.setContent(replyMsg.getContent());
    		
    		//如果key已存在，则更新回复内容
//    		if (mapper.getReplyByKey(user, key) != null)
//    		{
//    			mapper.updateRule(rule);
//    		}
//    		else
//    		{
//    			mapper.addRule(rule);
//    		}
    		mapper.addRule(rule);
    	}
    }
    
    /**
     * 删除某个用户的某个自定义规则
     * @param user 用户
     * @param key 关键字字符串，可能包含多个关键字，关键字用,隔开
     */
    public static void deleteRule(String user, String keyStr)
    {
    	String[] keys = keyStr.split(",|，");
    	for (String key : keys)
    	{
        	if (mapper.getReplyByKey(user, key) != null)
        	{
        		mapper.deleteRule(user, key);
        	}    		
    	}
    }
    
    /**
     * 获取某个用户的所有自定义关键字
     * @param user
     * @return
     */
    public static List<HashMap<String, String>> getKeys(String user)
    {
    	List<HashMap<String, String>> keyList = mapper.getKeys(user);
    	return keyList;
    }
    
    /**
     * 获取一个匹配的回复消息
     * @param user  用户
     * @param key	关键字
     * @return
     */
    public static AutoReplyMsg getReplyMsg(String user, String key)
    {
    	HashMap<String, String> replayRule = mapper.getReplyByKey(user, key);
    	if (replayRule.size() == 0)
    	{
    		return null;
    	}
    	String msgType = replayRule.get("type");
    	String msgContent = replayRule.get("content");
    	return AutoReplyMsg.generateReplay(msgType, msgContent);
    }
}
