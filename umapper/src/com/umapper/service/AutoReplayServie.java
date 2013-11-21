package com.umapper.service;

import java.util.ArrayList;
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
     * 添加一个规则，如果规则已存在则更新已有规则
     * @param user
     * @param keywords
     * @param matchrule
     * @param msgtype
     * @param msgContent
     */
    public static void addReplyRule(String user, String keywords, String matchrule, 
    		AutoReplyMsg replyMsg)
    {
    	String[] keys = keywords.split(",|，");
    	
    	for (String key : keys)
    	{
    		key.trim();
    		ReplyRule rule = new ReplyRule();
    		rule.setWxurl(user);
    		rule.setKeyword(key);
    		rule.setMatchRule(matchrule);
    		rule.setMsgype(replyMsg.getType());
    		rule.setContent(replyMsg.getContent());

    		if (mapper.getReplyByKey(user, key) != null)
    		{
    			mapper.updateRule(rule);
    		}
    		else
    		{
    			mapper.addRule(rule);
    		}
    	}
		sqlSession.commit();
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
		sqlSession.commit();
    }
    
    /**
     * 获取某个用户的所有自定义关键字
     * @param user
     * @return
     */
    public static List<AutoReplyKey> getKeys(String user)
    {
    	List<AutoReplyKey> msgList = new ArrayList<AutoReplyKey>();
    	List<HashMap<String, String>> keyList = mapper.getKeys(user);
    	
    	AutoReplyKey keyrule = null;
    	for (HashMap<String, String> key : keyList)
    	{
    		String keyword = key.get("keyword");
    		String matchrule = key.get("matchrule");
    		keyrule = new AutoReplyKey(keyword, matchrule);
    		msgList.add(keyrule);
    	}
		return msgList;
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
    	String msgType = replayRule.get("msgtype");
    	String msgContent = replayRule.get("content");
    	return AutoReplyMsg.generateReplay(msgType, msgContent);
    }
}
