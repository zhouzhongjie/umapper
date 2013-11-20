package com.umapper.dao;

import java.util.HashMap;
import java.util.List;

import com.umapper.po.ReplyRule;

public interface IAutoReplyDao {
	
	/**
	 * 获取某个用户的所有自定义关键字
	 * @param user 用户
	 * @return
	 */
	public List<HashMap<String, String>> getKeys(String user);

	/**
	 * 添加一个自定义回复规则
	 * @param user 用户，每个用户都有自己的一套规则
	 * @param key	关键字，多个关键字以,分开
	 * @param replay	回复内容
	 */
	public void addRule(ReplyRule rule);
	
	/**
	 * 更新自定义规则
	 * @param user 用户，每个用户都有自己的一套规则
	 * @param key	关键字，多个关键字以,分开
	 * @param replay	回复内容
	 */
	public void updateRule(ReplyRule rule);
	
	/**
	 * 获取自定义回复内容
	 * @param user	用户
	 * @param key	关键字
	 * @return
	 */
	public HashMap<String, String> getReplyByKey(String user, String key);
	
	/**
	 * 删除一个自定义规则
	 * @param user	用户
	 * @param key	关键字
	 */
	public void deleteRule(String user, String key);
	
}
