package com.umapper.message.autoreplay;

/**
 * 定义了关键字及匹配规则
 * @author 
 *
 */
public class AutoReplyKey {
	
	public static final String MATCH_ACCURATE = "accurate";	   //精确匹配
	public static final String MATCH_APPROXIMATE = "approximate"; //模糊匹配
	private String matchRule;
	private String keyword;
//	private JSONArray  keyArray = new JSONArray();
//	private JSONObject jsonObject = new JSONObject();
	
	/**
	 * 构造函数
	 * @param keyword 关键字
	 * @param matchRule 模糊匹配或精确匹配
	 */
	public AutoReplyKey(String keyword, String matchRule)
	{
//		jsonObject.put("match", matchRule);
		this.matchRule = matchRule;
		this.keyword = keyword;
//		String[] keys = keyString.split(",|，");
//		
//		for (String s : keys)
//		{
//			keyArray.add(s);
//		}
//		
//		jsonObject.put("keys", keyArray);
	}
	
	/**
	 * 获取所有关键词
	 * @return
	 */
	public String getKeyword()
	{
//		Object[] objs = keyArray.toArray();
//		String[] stringArray = Arrays.copyOf(objs, objs.length, String[].class);
//		return stringArray;
		return this.keyword;
	}
	
	/**
	 * 获取匹配规则：模糊匹配、精确匹配
	 * @return
	 */
	public String getMatchRule()
	{
		return this.matchRule;
	}

//	@Override
//	public String toString()
//	{
//		return jsonObject.toString();
//	}
}
