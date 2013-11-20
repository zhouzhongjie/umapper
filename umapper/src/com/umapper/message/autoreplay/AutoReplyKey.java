package com.umapper.message.autoreplay;

import java.util.Arrays;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 定义了关键字及匹配规则
 * @author 
 *
 */
public class AutoReplyKey {
	
	public static final String MATCH_ACCURATE = "accurate";	   //精确匹配
	public static final String MATCH_APPROXIMATE = "approximate"; //模糊匹配
	private String matchRule;
	private JSONArray  keyArray = new JSONArray();
	private JSONObject jsonObject = new JSONObject();
	
	/**
	 * 构造函数
	 * @param keys 关键字序列，可能包含多个关键字，多个关键字以，分隔
	 * @param matchRule 模糊匹配或精确匹配
	 */
	public AutoReplyKey(String keyString, String matchRule)
	{
		jsonObject.put("match", matchRule);
		this.matchRule = matchRule;
		String[] keys = keyString.split(",|，");
		
		for (String s : keys)
		{
			keyArray.add(s);
		}
		
		jsonObject.put("keys", keyArray);
	}
	
	/**
	 * 获取所有关键词
	 * @return
	 */
	public String[] getKeys()
	{
		Object[] objs = keyArray.toArray();
		String[] stringArray = Arrays.copyOf(objs, objs.length, String[].class);
		return stringArray;
	}
	
	/**
	 * 获取匹配规则：模糊匹配、精确匹配
	 * @return
	 */
	public String getMatchRule()
	{
		return this.matchRule;
	}

	@Override
	public String toString()
	{
		return jsonObject.toString();
	}
}
